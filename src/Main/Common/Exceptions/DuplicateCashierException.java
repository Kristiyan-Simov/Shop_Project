package Main.Common.Exceptions;

public class DuplicateCashierException extends RuntimeException{
    public DuplicateCashierException(String message){
        super(message);
    }
}
