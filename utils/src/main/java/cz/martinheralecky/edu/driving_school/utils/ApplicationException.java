package cz.martinheralecky.edu.driving_school.utils;

/**
 * The internal application exception.
 */
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
