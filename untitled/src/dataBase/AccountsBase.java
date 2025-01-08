package dataBase;

import model.BankAccount;
import model.Card;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AccountsBase {
    public static ArrayList<BankAccount> bankAccounts; // = new ArrayList<>(Arrays.asList(new BankAccount(007, 10, new Card[]{new Card(159753, 2589), null, null})));
    public static ArrayList<User>  users; // = new ArrayList<>(Arrays.asList(new User("Aleksey Larkov", "lark", "Al1234", new int[]{007, 0, 0, 0, 0})));
    static {
        bankAccounts = new ArrayList<>(Arrays.asList(new BankAccount(007, 10, new Card[]{new Card(159753, 2589), null, null})));
        users = new ArrayList<>(Arrays.asList(new User("Aleksey Larkov", "lark", "Al1234", new int[]{007, 0, 0, 0, 0})));
    }
}
