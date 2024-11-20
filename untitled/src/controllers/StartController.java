package controllers;

import exceptions.AccountsException;
import model.BankAccount;
import model.User;
import service.AccountService;
import service.CardService;
import service.UserService;
import util.ScannerUtil;

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
                "0 - Выход");
        System.out.println("------------------");

        //return Integer.parseInt(ScannerUtil.getString());
    }

    public static void showInterfaceUser(){
        System.out.println("------------------");
        System.out.println("1 - Просмотр счетов. \n" +
                "2 - Выбор счета для работы. \n" +
                "3 - Просмотр данных аккаунта \n" +
                "0 - Выход");
        System.out.println("------------------");
    }

    public static void showStartInterface(){
        System.out.println("------------------");
        System.out.println("1 - Вход по номеру карты. \n" +
                "2 - Вход по логину и паролю.");
        System.out.println("------------------");
    }

    public static void actionsFromCards(){
        BankAccount bankAccount = showStart();
        if (bankAccount != null) {
            boolean check = true;
            while (check) {
                showInterface();
                System.out.print("Выберите опцию: ");
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
                    case 0:
                        System.out.println("До свидания, Заберите карту");
                        CardService.deleteCurrentCard();
                        check = false;
                        break;
                    default:
                        System.out.println("Такого действия нет!");
                }
            }
        }else {
            System.out.println("Заберите Вашу карту");
        }
    }

    public static void actionsFromUser(){
        System.out.print("Введите Ваш логин: ");
        User user = UserService.searchUserFromLogin(ScannerUtil.getString());
        if (user != null) {
            boolean check = true;
            while (check) {
                showInterfaceUser();
                System.out.print("Выберите опцию: ");
                switch (Integer.parseInt(ScannerUtil.getString())) {
                    case 1:
                        UserService.showBankAccountsFromUser(user);
                        break;
                    case 2:
                        //todo
                        break;
                    case 3:
                        //todo
                        break;
                    case 0:
                        System.out.println("До свидания");
                        check = false;
                        break;
                    default:
                        System.out.println("Такого действия нет!");
                }
            }
        }else {
            System.out.println("До свидания");
        }
    }

    public static void actions(){
        while (true) {
            showStartInterface();
            System.out.print("Действие: ");
            switch (Integer.parseInt(ScannerUtil.getString())){
                case 1:
                    actionsFromCards();
                    break;
                case 2:
                    actionsFromUser();
                    break;
                default:
                    System.out.println("Такого действия нет!");
            }
        }
    }
}
