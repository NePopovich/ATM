package service;

import controllers.StartController;
import model.BankAccount;
import model.Card;
import util.ScannerUtil;

public class CardService {

    public static BankAccount getCard(Card card){
        BankAccount bankAccount = AccountService.findByCardNumber(card.numberCard);
        if (bankAccount == null){
            System.out.println("Такой карты не существует");
            return null;
        }else {
            while (true) {
                StartController.showPinCodeMenu();
                if (checkPinCode(Integer.parseInt(ScannerUtil.getString()), bankAccount, card.numberCard)) {
                    System.out.println("Здравствуйте");
                    return bankAccount;
                } else {
                    System.out.println("Введен неверный пин-код");
                }
            }
        }
    }

    public static boolean checkPinCode(int pinCode, BankAccount bankAccount, int numbCard){
        for (int i = 0; i < bankAccount.cards.length; i++) {
            if (bankAccount.cards[i].numberCard == numbCard){
                if (bankAccount.cards[i].pin == pinCode){
                    return true;
                }
            }
        }
        return false;
    }

}
