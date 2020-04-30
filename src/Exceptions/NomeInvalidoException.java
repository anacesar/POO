package Exceptions;

public class NomeInvalidoException extends Exception{
    public NomeInvalidoException() {
    }

    public NomeInvalidoException(String message) {
        super(message);
    }

    public String getMessage() {
        return "Introduziu um nome inválido!\n";
    }
}
