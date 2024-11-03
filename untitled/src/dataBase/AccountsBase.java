package dataBase;

import model.BankAccount;
import model.Card;

public class AccountsBase {
    public static BankAccount[] bankAccounts;
    static {
        bankAccounts = new BankAccount[]{
                new BankAccount(007, 10, new Card[]{new Card(159753, 2589)})
        };
    }
}
