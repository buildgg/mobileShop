package ua.home.mobileshop.exeption;

/**
 * Created by vov on 24.01.2017.
 */
public class ResourceNotFoundException extends IllegalArgumentException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
