package ua.home.mobileshop.service;

import javax.servlet.ServletContext;

/**
 * Created by vov on 05.01.2017.
 */
public class ServiceManager {
   private ServiceManager(ServletContext context){
   }

    public static ServiceManager getInstance(ServletContext context){
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null){
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }
}
