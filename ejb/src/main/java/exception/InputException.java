package exception;

public class InputException extends java.lang.Exception {

    public InputException(String inputField) {
        super("Invalid value for the input field " + inputField);
    }
}
