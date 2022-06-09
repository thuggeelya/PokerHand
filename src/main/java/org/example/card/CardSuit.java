package org.example.card;

import java.util.EnumMap;
import java.util.Map;

public enum CardSuit {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    private static final Map<CardSuit, Character> CARD_SUIT_ENUM_MAP = new EnumMap<>(CardSuit.class);

    static {
        for (CardSuit suit : CardSuit.values()) {
            CARD_SUIT_ENUM_MAP.put(suit, suit.toString().charAt(0));
        }
    }

    static CardSuit getByChar(char c) {
        for (CardSuit suit : CARD_SUIT_ENUM_MAP.keySet()) {
            if (c == CARD_SUIT_ENUM_MAP.get(suit)) {
                return suit;
            }
        }

        throw new IllegalArgumentException("Incorrect card suit");
    }

    @Override
    public String toString() {
        return name().charAt(0) + "";
    }
}