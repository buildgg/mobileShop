package ua.home.mobileshop.exeption;

/**
 * Created by vov on 18.01.2017.
 */
public class ValidationException extends IllegalArgumentException {
    private static final long serialVersionUID = -6843925636139273536L;

    public ValidationException(String s) {
        super(s);
    }
}
