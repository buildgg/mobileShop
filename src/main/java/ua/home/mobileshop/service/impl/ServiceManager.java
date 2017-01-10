package ua.home.mobileshop.service.impl;

import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vov on 05.01.2017.
 */
public class ServiceManager {

    private final OrderService orderService;
    private final ProductService productService;
    private final Properties applicationProperties = new Properties();
    private ServiceManager(ServletContext context) {
        orderService = new OrderServiceImpl();
        productService = new ProductServiceImpl();
        loadApplicationPropertie();
    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
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
    private void loadApplicationPropertie(){
        try(InputStream in = ServiceManager.class.getResourceAsStream("application.properties");) {
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
