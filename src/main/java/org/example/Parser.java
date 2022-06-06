package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    public static Set<Card> getCards(String cards) {
        String[] cardArray = cards.trim().split(" ");

        if (cardArray.length != 5) {
            throw new IllegalArgumentException("Cards number is incorrect");
        }

        Set<Card> cardSet = new HashSet<>();
        Arrays.stream(cardArray).forEach(s -> cardSet.add(getCard(s)));

        if (cardSet.size() != 5) {
            throw new IllegalArgumentException("Cards number is incorrect");
        }

        return cardSet;
    }

    private static Card getCard(String card) {
        CardValue cardValue;
        CardSuit cardSuit;

        if (card.length() != 2) {
            throw new IllegalArgumentException("Incorrect card input");
        }

        char value = card.charAt(0);
        char suit = card.charAt(1);

        if (value == 2) {
            cardValue = CardValue.TWO;
        } else if (value == 3) {
            cardValue = CardValue.THREE;
        } else if (value == 4) {
            cardValue = CardValue.FOUR;
        } else if (value == 5) {
            cardValue = CardValue.FIVE;
        } else if (value == 6) {
            cardValue = CardValue.SIX;
        } else if (value == 7) {
            cardValue = CardValue.SEVEN;
        } else if (value == 8) {
            cardValue = CardValue.EIGHT;
        } else if (value == 9) {
            cardValue = CardValue.NINE;
        } else if (value == 'T') {
            cardValue = CardValue.TEN;
        } else if (value == 'J') {
            cardValue = CardValue.JACK;
        } else if (value == 'Q') {
            cardValue = CardValue.QUEEN;
        } else if (value == 'K') {
            cardValue = CardValue.KING;
        } else if (value == 'A') {
            cardValue = CardValue.ACE;
        } else {
            throw new IllegalArgumentException("Incorrect card value");
        }

        if (suit == 'S') {
            cardSuit = CardSuit.SPADES;
        } else if (suit == 'H') {
            cardSuit = CardSuit.HEARTS;
        } else if (suit == 'D') {
            cardSuit = CardSuit.DIAMONDS;
        } else if (suit == 'C') {
            cardSuit = CardSuit.CLUBS;
        } else {
            throw new IllegalArgumentException("Incorrect card suit");
        }

        return new Card(cardValue, cardSuit);
    }
}