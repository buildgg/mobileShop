package ua.home.mobileshop.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vov on 17.01.2017.
 */
public class SearchForm {
    private String query;
    private List<Integer> categories;
    private List<Integer> producers;

    public SearchForm(String query, String[] categories, String[] producers) {
        this.query = query;
        this.categories = convert(categories);
        this.producers = convert(producers);
    }
    private List<Integer> convert(String[] args){
       if (args == null){
           return Collections.emptyList();
       } else {
        List<Integer> list = new ArrayList<>();
        for(String s : args){
            list.add(Integer.parseInt(s));
        }
        return list;
    }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getProducers() {
        return producers;
    }

    public void setProducers(List<Integer> producers) {
        this.producers = producers;
    }

    public boolean isCategoriesEmpty(){
        return categories.isEmpty();
    }
    public boolean isProducersEmpty(){
        return producers.isEmpty();
    }
}
