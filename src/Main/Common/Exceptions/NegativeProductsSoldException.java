package Main.Common.Exceptions;

public class NegativeProductsSoldException extends RuntimeException{
    public NegativeProductsSoldException(String message){
        super(message);
    }
}
