package Main.Common.Exceptions;

public class NegativeSalaryException extends RuntimeException{
    public NegativeSalaryException(String message){
        super(message);
    }
}
