package ua.home.mobileshop.exeption;

/**
 * Created by vov on 18.01.2017.
 */
public class ValidationException extends IllegalArgumentException {
    public ValidationException(String s) {
        super(s);
    }
}
