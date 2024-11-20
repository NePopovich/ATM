package service;

import model.Card;
import controllers.StartController;
import model.BankAccount;
import util.Constans;
import util.ScannerUtil;

public class CardService {
    public static Card currentCard;

    public static BankAccount getCard(int numberCard) {

//        if (bankAccount == null) {
//            System.out.println("Введены неверные данные");
//            return null;
//        } else {
//
//        }
        try{
            BankAccount bankAccount = AccountService.findByCardNumber(numberCard);
            Card card = findCardInAccount(bankAccount, numberCard);
            if (actionCard(card)){
                return bankAccount;
            }else {
                return null;
            }
        }catch (NullPointerException e){
            System.out.println("Введены неверные данные");
            return null;
        }
    }

    public static boolean checkPinCode(int pinCode, Card card) {
            if (card.countAttempt > 0) {
                if (card.pin == pinCode) {
                    card.countAttempt = 3;
                    return true;
                } else {
                    card.countAttempt--;
                    if (card.countAttempt == 0){
                        card.status = Constans.STATUS_BLOCKED;
                    }
                    return false;
                }
            } else {
                card.status = Constans.STATUS_BLOCKED;
                return false;
            }
    }

    public static void showBalanceFromCard() {
        int bal = AccountService.findByCardNumber(currentCard.numberCard).balance;
        if (bal != 0) {
            System.out.println(bal);
        } else {
            System.out.println("Баланс равен нулю");
        }
    }

    public static void deleteCurrentCard() {
        currentCard = null;
    }

    public static boolean actionCard(Card card){
        while (true) {
            if (card.status.equals(Constans.STATUS_ACTIVE)) {
                StartController.showPinCodeMenu();
                if (checkPinCode(Integer.parseInt(ScannerUtil.getString()), card)) {
                    System.out.println("Здравствуйте");
                    currentCard = card;
                    return true;
                } else {
                    System.out.println("Введены неверные данные");
                }
            }else {
                System.out.println("Карта заблокирована");
                return false;
            }
        }
    }

    public static Card findCardInAccount(BankAccount bankAccount, int numberCard){
        for (int i = 0; i < bankAccount.cards.length; i++) {
            if (bankAccount.cards[i].numberCard == numberCard){
                return bankAccount.cards[i];
            }
        }
        return null;
    }

}
