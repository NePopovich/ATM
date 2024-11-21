package dataBase;

import model.BankAccount;
import model.Card;
import model.User;

public class AccountsBase {
    public static BankAccount[] bankAccounts;
    public static User[] users;
    static {
        bankAccounts = new BankAccount[]{
                new BankAccount(007, 10, new Card[]{new Card(159753, 2589), null, null}),
                null, null, null, null
        };
        users = new User[]{new User("Aleksey Larkov", "lark", "Al1234", new int[]{007, 0, 0, 0, 0}), null, null, null, null};
    }
}
