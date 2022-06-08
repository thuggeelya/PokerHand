package org.example.card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.example.card.CardSuit.*;
import static org.example.card.CardValue.*;

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
        CardValue cardValue;
        CardSuit cardSuit;

        if (card.length() != 2) {
            throw new IllegalArgumentException("Incorrect card input");
        }

        char value = card.charAt(0);
        char suit = card.charAt(1);

        if (value == '2') {
            cardValue = TWO;
        } else if (value == '3') {
            cardValue = THREE;
        } else if (value == '4') {
            cardValue = FOUR;
        } else if (value == '5') {
            cardValue = FIVE;
        } else if (value == '6') {
            cardValue = SIX;
        } else if (value == '7') {
            cardValue = SEVEN;
        } else if (value == '8') {
            cardValue = EIGHT;
        } else if (value == '9') {
            cardValue = NINE;
        } else if (value == 'T') {
            cardValue = TEN;
        } else if (value == 'J') {
            cardValue = JACK;
        } else if (value == 'Q') {
            cardValue = QUEEN;
        } else if (value == 'K') {
            cardValue = KING;
        } else if (value == 'A') {
            cardValue = ACE;
        } else {
            throw new IllegalArgumentException("Incorrect card value");
        }

        if (suit == 'S') {
            cardSuit = SPADES;
        } else if (suit == 'H') {
            cardSuit = HEARTS;
        } else if (suit == 'D') {
            cardSuit = DIAMONDS;
        } else if (suit == 'C') {
            cardSuit = CLUBS;
        } else {
            throw new IllegalArgumentException("Incorrect card suit");
        }

        return new Card(cardValue, cardSuit);
    }
}