package service;

import dataBase.AccountsBase;
import model.BankAccount;
import model.User;
import util.Constans;
import util.ScannerUtil;

public class UserService {

    public static User createNewUser(){
        System.out.print("Введите Ваше имя: ");
        String name = ScannerUtil.getString();
        System.out.print("Введите Ваш логин: ");
        String login = ScannerUtil.getString();
        System.out.print("Введите Ваш пароль: ");
        String password = ScannerUtil.getString();
        User newUser = new User(name, login, password);
        AccountsBase.users.add(newUser);
        return newUser;
    }

    public static User searchUserFromLogin(String login){
        for (int i = 0; i < AccountsBase.users.size(); i++) {
                if (AccountsBase.users.get(i).login.equals(login)){
                    if (checkPassword(AccountsBase.users.get(i))){
                        return AccountsBase.users.get(i);
                    }
                }
        }
        return null;
    }

    public static boolean checkPassword(User user){
        boolean check = true;
        while (check){
            if (user.countAttempt > 0 || user.status.equals(Constans.STATUS_BLOCKED)){
                System.out.print("Введите пароль: ");
                if (ScannerUtil.getString().equals(user.password)){
                    user.countAttempt = 3;
                    return true;
                }else {
                    System.out.println("Введены неверные данные!");
                    user.countAttempt--;
                }
            }else {
                System.out.println("Аккаунт заблокирован");
                user.status = Constans.STATUS_BLOCKED;
                check = false;
            }
        }
        return false;
    }

    public static void addBankAccount(User user, int numberOfBankAccount){
        if (numberOfBankAccount!=0){
            if (user.getNumberBankAccounts() != null) {
                for (int i = 0; i < user.getNumberBankAccounts().length; i++) {
                    if (user.getNumberBankAccounts()[i] == 0) {
                        user.getNumberBankAccounts()[i] = numberOfBankAccount;
                        System.out.println("Аккаунт создан");
                        break;
                    }
                }
            }else {
                user.setNumberBankAccounts(new int[5]);
                user.getNumberBankAccounts()[0] = numberOfBankAccount;
            }
        }else {
            System.out.println("Аккаунт не создан");
        }
    }

    public static void showBankAccountsFromUser(User user){
        if (user.getNumberBankAccounts() != null){
            for (int i = 0; i < user.getNumberBankAccounts().length; i++) {
                if (user.getNumberBankAccounts()[i] != 0){
                    System.out.println(AccountService.getBankAccountFromNumber(user.getNumberBankAccounts()[i]));
                }
            }
        }else {
            System.out.println("Банковские счета отсутствуют!");
        }
    }

}
