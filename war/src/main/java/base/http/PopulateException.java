package base.http;


import exception.BaseException;

/**
 * This exception is thrown if there is a problem extracting information from the request (missing information, wrong
 * information, ......).
 */
public class PopulateException extends BaseException {

    public PopulateException(Throwable th) {
        super(th);
    }

    public PopulateException(String message, Throwable th) {
        super(message, th);
    }

    public PopulateException(String message) {
        super(message);
    }

}

