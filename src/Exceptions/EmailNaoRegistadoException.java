package Exceptions;

public class EmailNaoRegistadoException extends Exception{
    public EmailNaoRegistadoException() {
    }

    public EmailNaoRegistadoException(String message) {
        super(message);
    }

    public String getMessage() {
        return "O email introduzido não se encontra registado!\n";
    }
}
