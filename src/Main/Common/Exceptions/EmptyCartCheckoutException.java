package Main.Common.Exceptions;

public class EmptyCartCheckoutException extends RuntimeException{
    public EmptyCartCheckoutException(String message){
        super(message);
    }
}
