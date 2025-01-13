package view;

public class ViewRus {
    public static final String MESSAGE_TRANSACTION_ADD_MONEY = "Зачисление денег";
    public static final String MESSAGE_TRANSACTION_TRANSFER_MONEY = "Перечисление денег";
    public static final String MESSAGE_TRANSACTION_GET_MONEY = "Снятие денег";

    public static void showOperation(){
        System.out.print("Выберите действие: ");
    }

    public static void showTransferOperation(){
        System.out.println("------------------ \n" +
                "1 -  Перевод по номеру счета\n" +
                "2 -  Перевод по номеру карты\n" +
                "------------------");
    }

    public static void showBankAccountNumb(){
        System.out.print("Введите номер счета: ");
    }

    public static void showCountMoney(){
        System.out.print("Введите сумму: ");
    }
    public static void showTransferResult(){
        System.out.println("Деньги переведены!");
    }

    public static void showNoBankAccount(){
        System.out.println("Такого счета не существует!");
    }

    public static void showCardNumber(){
        System.out.print("Введите номер карты: ");
    }

    public static void showNoMotion(){
        System.out.println("Такого действия нет!");
    }

    public static void showBadMoney(){
        System.out.println("Введена некорректная сумма");
    }

    public static void showLowMoney(){
        System.out.println("!!!Баланс счета меньше 20!!!\n" +
                "------------------");
    }
}
