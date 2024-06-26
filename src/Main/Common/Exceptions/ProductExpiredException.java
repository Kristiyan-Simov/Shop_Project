package Main.Common.Exceptions;

public class ProductExpiredException extends RuntimeException{
    public ProductExpiredException(String message){
        super(message);
    }
}
