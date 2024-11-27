package model;

import java.util.Date;

public class Transaction {
    String type;
    int summa;
    Date date;

    public Transaction(String type, int summa, Date date) {
        this.type = type;
        this.summa = summa;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", summa=" + summa +
                ", date='" + date + '\'' +
                '}';
    }
}
