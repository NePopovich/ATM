package controllers;

import model.BankAccount;
import service.AccountService;
import service.CardService;
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

    public static void actions(){
        while (true) {
            BankAccount bankAccount = showStart();
            if (bankAccount != null) {
                boolean check = true;
                while (check) {
                    showInterface();
                    System.out.print("Выберите опцию: ");
                    switch (Integer.parseInt(ScannerUtil.getString())) {
                        case 1:
                            AccountService.showBalance(bankAccount);
                            break;
                        case 2:
                            AccountService.getMoney(bankAccount);
                            break;
                        case 3:
                            AccountService.putMoney(bankAccount);
                            break;
                        case 0:
                            System.out.println("До свидания, Заберите карту");
                            CardService.deleteCurrentCard();
                            showStart();
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
    }
}
