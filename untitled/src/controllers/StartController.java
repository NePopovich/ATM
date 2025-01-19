package controllers;

import exceptions.AccountsException;
import model.BankAccount;
import model.Transaction;
import model.User;
import service.AccountService;
import service.CardService;
import service.TransactionService;
import service.UserService;
import util.ScannerUtil;
import view.ViewRus;

public class StartController {
    public static BankAccount showStart(){
        System.out.println("Введите номер карты");
        return CardService.getCard(Integer.parseInt(ScannerUtil.getString()));
    }

    public static void showPinCodeMenu(){
        System.out.println("введите пин-код");
    }

    public static void showGetMoney(){
        System.out.println("Введите сумму которую хотите снять");
    }

    public static void showPutMoney(){
        System.out.println("Введите сумму которую хотите положить на счет");
    }

    public static void showInterface(){
        System.out.println("------------------");
        System.out.println("1 - Просмотр баланса. \n" +
                "2 - Снятие наличных. \n" +
                "3 - Положить наличные \n" +
                "4 - Просмотр транзакций \n" +
                "5 - Перевести деньги \n" +
                "6 - Действия с картами \n" +
                "0 - Выход");
        System.out.println("------------------");

        //return Integer.parseInt(ScannerUtil.getString());
    }

    public static void showInterfaceUser(){
        System.out.println("------------------");
        System.out.println("1 - Просмотр счетов. \n" +
                "2 - Выбор счета для работы. \n" +
                "3 - Просмотр данных аккаунта \n" +
                "4 - Создание нового счета \n" +
                "0 - Выход");
        System.out.println("------------------");
    }

    public static void showStartInterface(){
        System.out.println("------------------");
        System.out.println("1 - Вход по номеру карты. \n" +
                "2 - Вход по логину и паролю. \n" +
                "3 - Регистрация нового пользователя.");
        System.out.println("------------------");
    }

    public static void actionsFromCards(BankAccount bankAccount) throws AccountsException {
//        BankAccount bankAccount = showStart();
        if (bankAccount != null) {
            boolean check = true;
            while (check) {
                showInterface();
                if (bankAccount.balance < 20){
                    ViewRus.showLowMoney();
                }
                ViewRus.showOperation();
                switch (Integer.parseInt(ScannerUtil.getString())) {
                    case 1:
                        try {
                            AccountService.showBalance(bankAccount);
                        } catch (AccountsException e) {

                        }
                        break;
                    case 2:
                        try {
                            AccountService.getMoney(bankAccount);
                        } catch (AccountsException e) {

                        }
                        break;
                    case 3:
                        try{
                            AccountService.putMoney(bankAccount);
                        } catch (AccountsException e) {

                        }
                        break;
                    case 4:
                        TransactionService.showTransaction(bankAccount);
                        break;
                    case 5:
                        AccountService.transferMoney(bankAccount);
                        break;
                    case 6:
                        actionFromAccountsCards(bankAccount);
                        break;
                    case 0:
                        System.out.println("До свидания, Заберите карту");
                        CardService.deleteCurrentCard();
                        check = false;
                        break;
                    default:
                        ViewRus.showNoMotion();
                }
            }
        }else {
            System.out.println("Заберите Вашу карту");
        }
    }

    public static void actionsFromUser(User user) throws AccountsException {
        if (user != null) {
            boolean check = true;
            while (check) {
                showInterfaceUser();
                ViewRus.showOperation();
                switch (Integer.parseInt(ScannerUtil.getString())) {
                    case 1:
                        UserService.showBankAccountsFromUser(user);
                        break;
                    case 2:
                        System.out.print("Введите номер счета: ");
                        actionsFromCards(AccountService.getBankAccountFromNumber(Integer.parseInt(ScannerUtil.getString())));
                        break;
                    case 3:
                        System.out.println(user);
                        break;
                    case 4:
                        UserService.addBankAccount(user, AccountService.createAccount());
                        break;
                    case 0:
                        System.out.println("До свидания");
                        check = false;
                        break;
                    default:
                        ViewRus.showNoMotion();
                }
            }
        }else {
            System.out.println("До свидания");
        }
    }

    public static void actions() throws AccountsException {
        while (true) {
            showStartInterface();
            ViewRus.showOperation();
            switch (Integer.parseInt(ScannerUtil.getString())){
                case 1:
                    actionsFromCards(showStart());
                    break;
                case 2:
                    System.out.print("Введите Ваш логин: ");
                    User user = UserService.searchUserFromLogin(ScannerUtil.getString());
                    actionsFromUser(user);
                    break;
                case 3:
                    User newUser = UserService.createNewUser();
                    actionsFromUser(newUser);
                    break;
                default:
                    ViewRus.showNoMotion();
            }
        }
    }

    public static void actionFromAccountsCards(BankAccount bankAccount){
        if (bankAccount != null) {
            boolean check = true;
            while (check) {
                ViewRus.showCardMotion();
                ViewRus.showOperation();
                switch (Integer.parseInt(ScannerUtil.getString())) {
                    case 1:
                        AccountService.showAllCards(bankAccount);
                        break;
                    case 2:
                        CardService.showPinCodeCard();
                        break;
                    case 3:
                        ViewRus.showCardNumber();
                        CardService.getCardInAccount(bankAccount, Integer.parseInt(ScannerUtil.getString()));
                        break;
                    case 4:
                        AccountService.addNewCard(bankAccount);
                        break;
                    case 0:
                        check = false;
                        break;
                    default:
                        ViewRus.showNoMotion();
                }
            }
        }else {
            System.out.println("Продолжаем?");
        }
    }
}
