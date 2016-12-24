package exception;

/**
 * This exception will be used as root for all runtime exception classes in the project.
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

}
