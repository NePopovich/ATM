package service;

import controllers.StartController;
import dataBase.AccountsBase;
import exceptions.AccountsException;
import model.BankAccount;
import model.Card;
import model.Transaction;
import util.ScannerUtil;
import view.ViewRus;

import java.util.Arrays;
import java.util.Date;

public class AccountService {

    public static BankAccount findByCardNumber(int cardNumb) {
//        if (AccountsBase.bankAccounts.isEmpty()){
//            return null;
//        }else {
            for (int i = 0; i < AccountsBase.bankAccounts.size(); i++) {
                if (AccountsBase.bankAccounts.get(i) != null) {
                    for (int j = 0; j < AccountsBase.bankAccounts.get(i).cards.length; j++) {
                        if (AccountsBase.bankAccounts.get(i).cards[j] != null){
                            if (AccountsBase.bankAccounts.get(i).cards[j].numberCard == cardNumb) {
                                CardService.currentCard = AccountsBase.bankAccounts.get(i).cards[j];
                                return AccountsBase.bankAccounts.get(i);
                            }
                        }
                    }
                }
            }
            return null;
//        }
    }

    public static BankAccount getMoney(BankAccount bankAccount) throws AccountsException{
        StartController.showGetMoney();
        int money = Integer.parseInt(ScannerUtil.getString());
        if (money > 0 && money % 1 == 0) {
            if (bankAccount.balance > money) {
                bankAccount.balance -= money;
                System.out.println("Заберите ваши деньги: " + money + "р.");
                bankAccount.bankTransactions.add(new Transaction(ViewRus.MESSAGE_TRANSACTION_GET_MONEY, money, new Date()));
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
            bankAccount.bankTransactions.add(new Transaction(ViewRus.MESSAGE_TRANSACTION_ADD_MONEY, money, new Date()));
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
//        for (int i = 0; i < AccountsBase.bankAccounts.length; i++) {
//            if (AccountsBase.bankAccounts[i] == null){
                AccountsBase.bankAccounts.add(new BankAccount((int) (Math.random() * 900) + 100, 0,
                        new Card[]{CardService.createNewCard(), null, null}));
                return AccountsBase.bankAccounts.getLast().numberAccount;
//            }
//        }
//        return 0;
    }

    public static BankAccount getBankAccountFromNumber(int numberBankAccount){
        for (int i = 0; i < AccountsBase.bankAccounts.size(); i++) {
                if (AccountsBase.bankAccounts.get(i).numberAccount == numberBankAccount){
                    CardService.currentCard = AccountsBase.bankAccounts.get(i).cards[0];
                    return AccountsBase.bankAccounts.get(i);
                }

        }
        return  null;
    }

    public static void addNewCard(BankAccount bankAccount){
        if (bankAccount.cards != null){
            for (int i = 0; i < bankAccount.cards.length; i++) {
                if (bankAccount.cards[i] == null){
                    bankAccount.cards[i] = CardService.createNewCard();
                    System.out.println("Новая карта создана");
                    break;
                }
            }
        }else {
            bankAccount.cards = new Card[]{CardService.createNewCard(), null, null};
        }
    }

    public static void transferMoney(BankAccount bankAccount) throws AccountsException {
        ViewRus.showTransferOperation();
        ViewRus.showOperation();
        switch (Integer.parseInt(ScannerUtil.getString())){
            case (1):
                ViewRus.showBankAccountNumb();
                transferMotion(bankAccount, getBankAccountFromNumber(Integer.parseInt(ScannerUtil.getString())));
                break;
            case (2):
                ViewRus.showCardNumber();
                transferMotion(bankAccount, findByCardNumber(Integer.parseInt(ScannerUtil.getString())));
                break;
            default:
                ViewRus.showNoMotion();
        }
    }

    private static void transferMotion(BankAccount bankAccount, BankAccount bankAccountToTransfer) throws AccountsException {
        if (bankAccountToTransfer != null) {
            ViewRus.showCountMoney();
            int money = Integer.parseInt(ScannerUtil.getString());
            if (money > 0 && bankAccount.balance > money) {
                bankAccount.balance -= money;
                bankAccountToTransfer.balance += money;
                bankAccount.bankTransactions.add(new Transaction(ViewRus.MESSAGE_TRANSACTION_TRANSFER_MONEY, money, new Date()));
                bankAccountToTransfer.bankTransactions.add(new Transaction(ViewRus.MESSAGE_TRANSACTION_ADD_MONEY, money, new Date()));
                ViewRus.showTransferResult();
            }else {
               ViewRus.showBadMoney();
            }
        }else {
            ViewRus.showNoBankAccount();
        }
    }

    public static void showAllCards(BankAccount bankAccount){
        Arrays.stream(bankAccount.cards).forEach(System.out::println);
    }
}
