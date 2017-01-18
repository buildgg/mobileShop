package ua.home.mobileshop.temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by vov on 05.01.2017.
 */
public class TestProperties {
    public static void main(String[] args) throws IOException {
        /*Properties prop = new Properties();
        prop.load(TestProperties.class.getClassLoader().getResourceAsStream("application.properties"));
        Boolean isProdaction = Boolean.valueOf(prop.getProperty("application.prodaction"));
       // System.out.println("PROPERTIES = " + isProdaction);*/

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);


        Integer[] stockArr = new Integer[list.size()];
        stockArr = list.toArray(stockArr);
        System.out.println(Arrays.toString(stockArr));


    }
}
