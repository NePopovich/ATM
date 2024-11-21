package model;

import util.Constans;

import java.util.Arrays;

public class User {
    public String name;
    public String login;
    public String password;
    public int countAttempt;
    public String status;
    private int[] numberBankAccounts;
//    public BankAccount[] bankAccounts;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.status = Constans.STATUS_ACTIVE;
        this.countAttempt = 3;
    }

    public User(String name, String login, String password, int[] numberBankAccounts) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.numberBankAccounts = numberBankAccounts;
        this.status = Constans.STATUS_ACTIVE;
        this.countAttempt = 3;
    }

    public int[] getNumberBankAccounts() {
        return numberBankAccounts;
    }

    public void setNumberBankAccounts(int[] numberBankAccounts) {
        this.numberBankAccounts = numberBankAccounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
