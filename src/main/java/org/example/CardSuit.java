package org.example;

public enum CardSuit {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    @Override
    public String toString() {
        return this.name().charAt(0) + "";
    }
}