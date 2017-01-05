package ua.home.mobileshop.temp;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by vov on 05.01.2017.
 */
public class TestProperties {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(TestProperties.class.getClassLoader().getResourceAsStream("application.properties"));
        Boolean isProdaction = Boolean.valueOf(prop.getProperty("application.prodaction"));
        System.out.println("PROPERTIES = " + isProdaction);
    }
}
