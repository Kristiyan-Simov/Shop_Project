package Main.Common.Exceptions;

public class ExpirationDateBeforeTodayException extends RuntimeException{
    public ExpirationDateBeforeTodayException(String message){
        super(message);
    }
}
