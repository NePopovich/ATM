package model;

import util.Constans;

public class Card {
    public int numberCard;
    public int pin;
    public String status;
    public int countAttempt;


    public Card(int numberCard, int pin) {
        this.numberCard = numberCard;
        this.pin = pin;
        this.status = Constans.STATUS_ACTIVE;
        this.countAttempt = 3;
    }
}
