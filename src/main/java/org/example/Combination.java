package org.example;

public enum Combination {
    HIGH_CARD(1), PAIR(2),
    TWO_PAIRS(3), THREE_OF_A_KIND(4),
    STREET_FLASH(5), STRAIGHT(6),
    FLUSH(7), FULL_HOUSE(8),
    FOUR_OF_A_KIND(9),
    STRAIGHT_FLUSH(10), ROYAL_FLUSH(11);

    private final int power;

    Combination(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}