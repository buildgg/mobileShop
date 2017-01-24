package ua.home.mobileshop.service.impl;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;
import ua.home.mobileshop.service.SocialService;

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
    private final SocialService socialService;
    private final Properties applicationProperties = new Properties();
    private final BasicDataSource dataSource;
    private ServiceManager(ServletContext context) {
        loadApplicationPropertie();
        dataSource = getDataSource();
        orderService = new OrderServiceImpl(dataSource);
        socialService = new FacebookSocialService(this);
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
        LOGGER.info("Driver = " + getApplicationProperty("db.driver") +
        "User name = " + getApplicationProperty("db.username")+
        "URL = " + getApplicationProperty("db.url")+
        "pas = " + getApplicationProperty("db.password"));
        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
        dataSource.setUrl(getApplicationProperty("db.url"));
        dataSource.setUsername(getApplicationProperty("db.username"));
        dataSource.setPassword(getApplicationProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));


        return dataSource;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public SocialService getSocialService() {
        return socialService;
    }

    public String getApplicationProperty(String key){
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
