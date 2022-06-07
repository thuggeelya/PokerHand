package org.example.card;

public enum CardSuit {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    @Override
    public String toString() {
        return this.name().charAt(0) + "";
    }
}