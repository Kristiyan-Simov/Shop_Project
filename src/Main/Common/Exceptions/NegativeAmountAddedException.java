package Main.Common.Exceptions;

public class NegativeAmountAddedException extends RuntimeException{
    public NegativeAmountAddedException(String message){
        super(message);
    }
}
