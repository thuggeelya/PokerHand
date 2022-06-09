package org.example.card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {

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
        if (card.length() != 2) {
            throw new IllegalArgumentException("Incorrect card input");
        }

        return new Card(CardValue.getByChar(card.charAt(0)), CardSuit.getByChar(card.charAt(1)));
    }
}