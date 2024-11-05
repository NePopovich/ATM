package service;

import controllers.StartController;
import dataBase.AccountsBase;
import model.BankAccount;
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

    public static void getMoney(BankAccount bankAccount) {
        StartController.showGetMoney();
        int money = Integer.parseInt(ScannerUtil.getString());
        if (money > 0 && money % 1 == 0) {
            if (bankAccount.balance > money) {
                bankAccount.balance -= money;
                System.out.println("Заберите ваши деньги: " + money + "р.");
            } else {
                System.out.println("На счету недостаточно средств!");
            }
        } else {
            System.out.println("Введено некорректное число");
        }

    }

    public static void putMoney(BankAccount bankAccount) {
        StartController.showPutMoney();
        int money = Integer.parseInt(ScannerUtil.getString());
        if (money > 0 && money % 1 == 0) {
            bankAccount.balance += money;
            System.out.println("На счет зачислено: " + money + "р.");
        } else {
            System.out.println("Введена некорректная сумма");
        }

    }

    public static void showBalance(BankAccount bankAccount) {
        if (bankAccount.balance != 0) {
            System.out.println("Баланс счета: " + bankAccount.balance);
        } else {
            System.out.println("Баланс счета равен нулю");
        }
    }
}
