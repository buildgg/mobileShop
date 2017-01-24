package ua.home.mobileshop.model;

/**
 * Created by vov on 20.01.2017.
 */
public class SocialAccount {
    private final String name;
    private final String email;

    public SocialAccount(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
