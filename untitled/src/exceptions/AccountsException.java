package exceptions;

public class AccountsException extends Exception{
    public AccountsException(String message) {
        System.out.println(message);
//        super(message);
    }
}
