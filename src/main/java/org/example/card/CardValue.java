package org.example.card;

import java.util.EnumMap;
import java.util.Map;

public enum CardValue {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

    private final int power;
    private static final Map<CardValue, Character> CARD_VALUE_ENUM_MAP = new EnumMap<>(CardValue.class);

    static {
        for (CardValue value : CardValue.values()) {
            CARD_VALUE_ENUM_MAP.put(value, value.toString().charAt(0));
        }
    }

    CardValue(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    static CardValue getByChar(char c) {
        for (CardValue value : CARD_VALUE_ENUM_MAP.keySet()) {
            if (c == CARD_VALUE_ENUM_MAP.get(value)) {
                return value;
            }
        }

        throw new IllegalArgumentException("Incorrect card value");
    }

    @Override
    public String toString() {
        return (power < TEN.power) ? power + "" : name().charAt(0) + "";
    }
}