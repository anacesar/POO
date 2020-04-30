package Exceptions;

public class EmailJaExisteException extends Exception {
    public EmailJaExisteException() {
    }

    public EmailJaExisteException(String message) {
        super(message);
    }

    public String getMessage() {
        return "O email introduzido já existe!\n";
    }
}
