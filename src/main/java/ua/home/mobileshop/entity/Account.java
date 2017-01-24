package ua.home.mobileshop.entity;

import ua.home.mobileshop.model.CurrentAccount;

/**
 * Created by vov on 12.01.2017.
 */
public class Account extends AbstractEntity<Integer> implements CurrentAccount{
    private String name;
    private String email;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String getDescription() {
        return name + "(" + email + ")";
    }
}
