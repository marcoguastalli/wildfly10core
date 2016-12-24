package exception;

/**
 * This exception will be used as root for all (non-runtime) exception classes in the project.
 */
public abstract class BaseException extends Exception {

    private boolean traceException;

    public BaseException(String message) {
        this(message, true);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        traceException = traceException(cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
        traceException = traceException(cause);
    }

    public BaseException(Throwable cause, boolean traceException) {
        super(cause);
        this.traceException = traceException;
    }

    public BaseException(String message, boolean traceException) {
        super(message);
        this.traceException = traceException;
    }

    public BaseException(String message, Throwable cause, boolean traceException) {
        super(message, cause);
        this.traceException = traceException;
    }

    private static boolean traceException(Throwable cause) {
        return cause instanceof BaseException ? BaseException.class.cast(cause).isTraceException() : true;
    }

    public boolean isTraceException() {
        return traceException;
    }
}
