package ua.home.mobileshop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.service.impl.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
