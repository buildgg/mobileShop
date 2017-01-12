package ua.home.mobileshop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.entity.Category;
import ua.home.mobileshop.entity.Producer;
import ua.home.mobileshop.service.ProductService;
import ua.home.mobileshop.service.impl.ServiceManager;
import ua.home.mobileshop.util.ConstantsView;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);
    private ServiceManager serviceManager;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
            List<Category> categoryList = serviceManager.getProductService().getAllCategories();
            sce.getServletContext().setAttribute(ConstantsView.ATTRIBUTE_CATEGORIES, categoryList);

            List<Producer> producerList = serviceManager.getProductService().getAllProducers();
            sce.getServletContext().setAttribute(ConstantsView.ATTRIBUTE_PRODUCERS, producerList);

            LOGGER.info(":::: Web application initialized");
        }catch (RuntimeException e){
            LOGGER.error(" Web application init failed ", e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info(":::: Web application destroyed");
    }
}
