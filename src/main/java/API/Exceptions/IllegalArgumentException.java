package API.Exceptions;

/**
 * Excepção que é lançada quando um argumento é inválido.
 */
public class IllegalArgumentException extends RuntimeException{
    public IllegalArgumentException(String message) {
        super(message);
    }
}
