package ua.home.mobileshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.entity.Account;
import ua.home.mobileshop.entity.Order;
import ua.home.mobileshop.entity.OrderItem;
import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.exeption.AccessDeniedException;
import ua.home.mobileshop.exeption.InternalServerErrorException;
import ua.home.mobileshop.exeption.ResourceNotFoundException;
import ua.home.mobileshop.form.ProductForm;
import ua.home.mobileshop.jdbc.JDBCUtils;
import ua.home.mobileshop.jdbc.ResultSetHandler;
import ua.home.mobileshop.jdbc.ResultSetHandlerFactory;
import ua.home.mobileshop.model.CurrentAccount;
import ua.home.mobileshop.model.ShoppingCart;
import ua.home.mobileshop.model.ShoppingCartItem;
import ua.home.mobileshop.model.SocialAccount;
import ua.home.mobileshop.service.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final ResultSetHandler<Product> productResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Account> accountResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);

    public static final ResultSetHandler<Order> orderResultSet =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<OrderItem>> orderItemListResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Integer> orderCountResultSet = ResultSetHandlerFactory.getCountResultSetHandler();

    public static final ResultSetHandler<List<Order>> orderResultListByAccount =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);



    private final DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShopingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try (Connection c = dataSource.getConnection()) {

            Product product = JDBCUtils.select(c, "select p.*, c.name as category, pr.name as producer " +
                            "from product p, producer pr, category c " +
                            "where c.id=p.id_category and pr.id=p.id_producer and p.id = ?",
                    productResultSetHandler, productForm.getIdProduct());
            if (product == null) {
                throw new InternalServerErrorException("Product not found " + productForm.getIdProduct());
            } else {
                shoppingCart.addProduct(product, productForm.getCount());
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(form.getIdProduct(), form.getCount());
    }

    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String string) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String data[] = item.split("-");
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                addProductToShopingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }

    @Override
    public CurrentAccount authentificate(SocialAccount socialAccount) {
        try (Connection c = dataSource.getConnection()) {
            Account account = JDBCUtils.select(c, "select * from account where email=?", accountResultSetHandler, socialAccount.getEmail());
            if (account == null) {
                account = new Account(socialAccount.getName(), socialAccount.getEmail());
                account = JDBCUtils.insert(c, "insert into account values (nextval('account_seq'),?,?)", accountResultSetHandler, account.getName(), account.getEmail());
                c.commit();
            }
            return account;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()){
            throw new InternalServerErrorException("shopping cart is empty or null");
        }
        try(Connection c = dataSource.getConnection()){
            Order order = JDBCUtils.insert(c, "insert into \"order\" values(nextval('order_seq'),?,?)",orderResultSet,
                    currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            JDBCUtils.insertBatch(c, "insert into order_item values (nextval('order_item_seq'), ?, ?, ?)",
                    toOrderItemParameterList(order.getId(), shoppingCart.getItems()));
            c.commit();
            return order.getId();

        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql request " + e.getMessage(), e);
        }
    };
    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items){
        List<Object[]> parametersList = new ArrayList<>();
        for (ShoppingCartItem s : items){
            parametersList.add(new Object[]{idOrder, s.getProduct().getId(), s.getCount()});
        }
        return parametersList;
    }

    @Override
    public Order findOrderById(long id, CurrentAccount currentAccount) {
       try(Connection c = dataSource.getConnection()) {
           Order order = JDBCUtils.select(c, "select * from \"order\" where id=?", orderResultSet,id);
           if (order == null){
               throw  new ResourceNotFoundException("Order not found by id = " + id);
           }
           if(!order.getIdAccount().equals(currentAccount.getId())){
               throw new AccessDeniedException("Account with id = " + currentAccount.getId() + " is not" +
                       "owner for order with id = " + id);
           }
           List<OrderItem> list = JDBCUtils.select(c,
                   "select o.id as oid, o.id_order as id_order, o.id_product, o.count, p.*, c.name as category, pr.name as producer from order_item o, product p, category c, producer pr "
                           + "where pr.id=p.id_producer and c.id=p.id_category and o.id_product=p.id and o.id_order=?",
                   orderItemListResultSetHandler, id);
           order.setItems(list);
           return order;
       } catch (SQLException e) {
           throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
       }

    }

    @Override
    public List<Order> getMyOrders(CurrentAccount currentAccount, int page, int limit) {

        try (Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "select o.* " +
                            "from \"order\" o " +
                            "where id_account = ? order by o.created desc limit ? offset ?",
                    orderResultListByAccount, currentAccount.getId(), limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }
        @Override
    public int getCountMyOrders(CurrentAccount currentAccount) {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select count(*) from \"order\" where id_account = ?", orderCountResultSet, currentAccount.getId());
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }
}
