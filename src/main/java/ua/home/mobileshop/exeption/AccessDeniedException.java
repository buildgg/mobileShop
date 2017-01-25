package ua.home.mobileshop.exeption;

/**
 * Created by vov on 24.01.2017.
 */
public class AccessDeniedException extends IllegalArgumentException {
    public AccessDeniedException(String s) {
        super(s);
    }
}
