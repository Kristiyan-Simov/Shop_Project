package Main.Common.Exceptions;

public class InsufficientProductsException extends RuntimeException{
    public InsufficientProductsException(String message){
        super(message);
    }
}
