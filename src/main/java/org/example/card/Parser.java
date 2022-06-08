package org.example.card;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    private static final EnumMap<CardValue, Character> CARD_VALUE_ENUM_MAP = new EnumMap<>(CardValue.class);
    private static final EnumMap<CardSuit, Character> CARD_SUIT_ENUM_MAP = new EnumMap<>(CardSuit.class);

    static {
        for (CardValue c : CardValue.values()) {
            int power = c.getPower();
            char chPower = Character.forDigit(power, 10);
            CARD_VALUE_ENUM_MAP.put(c, (power < 10) ? chPower : c.name().charAt(0));
        }

        for (CardSuit c : CardSuit.values()) {
            CARD_SUIT_ENUM_MAP.put(c, c.name().charAt(0));
        }
    }

    public static Set<Card> getCards(String cards) {
        String[] cardArray = cards.trim().split(" ");

        if (cardArray.length != 5) {
            throw new IllegalArgumentException("Cards quantity is incorrect");
        }

        Set<Card> cardSet = new HashSet<>();
        Arrays.stream(cardArray).forEach(s -> cardSet.add(getCard(s)));

        if (cardSet.size() != 5) {
            throw new IllegalArgumentException("Card duplicates appeared");
        }

        return cardSet;
    }

    private static Card getCard(String card) {
        CardValue cardValue = null;
        CardSuit cardSuit = null;

        if (card.length() != 2) {
            throw new IllegalArgumentException("Incorrect card input");
        }

        for (CardValue c : CARD_VALUE_ENUM_MAP.keySet()) {
            cardValue = (card.charAt(0) == CARD_VALUE_ENUM_MAP.get(c)) ? c : cardValue;
        }

        for (CardSuit c : CARD_SUIT_ENUM_MAP.keySet()) {
            cardSuit = (card.charAt(1) == CARD_SUIT_ENUM_MAP.get(c)) ? c : cardSuit;
        }

        return new Card(cardValue, cardSuit);
    }
}