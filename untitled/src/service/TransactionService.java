package service;

import model.BankAccount;

public class TransactionService {

    public static void showTransaction(BankAccount bankAccount){
        System.out.println(bankAccount.bankTransactions);
    }
}