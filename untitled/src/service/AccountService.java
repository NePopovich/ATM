package service;

import controllers.StartController;
import dataBase.AccountsBase;
import exceptions.AccountsException;
import model.BankAccount;
import model.Card;
import util.ScannerUtil;

public class AccountService {

    public static BankAccount findByCardNumber(int cardNumb) {
        for (int i = 0; i < AccountsBase.bankAccounts.length; i++) {
            if (AccountsBase.bankAccounts[i] != null) {
                for (int j = 0; j < AccountsBase.bankAccounts[i].cards.length; j++) {
                    if (AccountsBase.bankAccounts[i].cards[j].numberCard == cardNumb) {
                        return AccountsBase.bankAccounts[i];
                    }
                }
            }
        }
        return null;
    }

    public static BankAccount getMoney(BankAccount bankAccount) throws AccountsException{
        StartController.showGetMoney();
        int money = Integer.parseInt(ScannerUtil.getString());
        if (money > 0 && money % 1 == 0) {
            if (bankAccount.balance > money) {
                bankAccount.balance -= money;
                System.out.println("Заберите ваши деньги: " + money + "р.");
            } else {
                throw new AccountsException("На счету недостаточно средств!");
//                System.out.println("На счету недостаточно средств!");
            }
        } else {
            throw new AccountsException("Введено некорректное число");
//            System.out.println("Введено некорректное число");
        }
        return bankAccount;
    }

    public static BankAccount putMoney(BankAccount bankAccount) throws AccountsException {
        StartController.showPutMoney();
        int money = Integer.parseInt(ScannerUtil.getString());
        if (money > 0 && money % 1 == 0) {
            bankAccount.balance += money;
            System.out.println("На счет зачислено: " + money + "р.");
        } else {
            throw new AccountsException("Введена некорректная сумма");
//            System.out.println("Введена некорректная сумма");
        }
        return bankAccount;
    }

    public static void showBalance(BankAccount bankAccount) throws AccountsException {
        if (bankAccount.balance != 0) {
            System.out.println("Баланс счета: " + bankAccount.balance);
        } else {
            throw new AccountsException("Баланс счета равен нулю");
//            System.out.println("Баланс счета равен нулю");
        }
    }

    public static int createAccount(){
        for (int i = 0; i < AccountsBase.bankAccounts.length; i++) {
            if (AccountsBase.bankAccounts[i] == null){
                AccountsBase.bankAccounts[i] = new BankAccount((int) (Math.random() * 900) + 100, 0,
                        new Card[]{new Card(159000 + ((int) (Math.random() * 900) + 100), 2589), null, null});
                return AccountsBase.bankAccounts[i].numberAccount;
            }
        }
        return 0;
    }

    protected static BankAccount getBankAccountFromNumber(int numberBankAccount){
        for (int i = 0; i < AccountsBase.bankAccounts.length; i++) {
            if (AccountsBase.bankAccounts[i] != null){
                if (AccountsBase.bankAccounts[i].numberAccount == numberBankAccount){
                    return AccountsBase.bankAccounts[i];
                }
            }
        }
        return  null;
    }
}
