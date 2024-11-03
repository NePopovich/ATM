package model;

public class BankAccount {
    public int numberAccount;
    public int balance;
    public Card[] cards;

    public BankAccount(int numberAccount, int balance, Card[] cards) {
        this.numberAccount = numberAccount;
        this.balance = balance;
        this.cards = cards;
    }
}
