package ua.home.mobileshop.service.impl;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by vov on 05.01.2017.
 */
public class ServiceManager {
 private final static Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);

    private final OrderService orderService;
    private final ProductService productService;
    private final Properties applicationProperties = new Properties();
    private final BasicDataSource dataSource;
    private ServiceManager(ServletContext context) {
        loadApplicationPropertie();
        dataSource = getDataSource();
        orderService = new OrderServiceImpl(dataSource);

        productService = new ProductServiceImpl(dataSource);

    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    private BasicDataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setRollbackOnReturn(true);
        LOGGER.info("Driver = " + getApplicatopnProperty("db.driver") +
        "User name = " + getApplicatopnProperty("db.username")+
        "URL = " + getApplicatopnProperty("db.url")+
        "pas = " + getApplicatopnProperty("db.password"));
        dataSource.setDriverClassName(getApplicatopnProperty("db.driver"));
        dataSource.setUrl(getApplicatopnProperty("db.url"));
        dataSource.setUsername(getApplicatopnProperty("db.username"));
        dataSource.setPassword(getApplicatopnProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicatopnProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicatopnProperty("db.pool.maxSize")));


        return dataSource;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }
    public String getApplicatopnProperty(String key){
        return applicationProperties.getProperty(key);
    }

    public void close(){
        try {
            dataSource.close();
        } catch (SQLException e) {
            LOGGER.error("Close dataSource failed: " + e.getMessage(), e);
        }
    }
    private void loadApplicationPropertie(){
        try(InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties");) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }


}
