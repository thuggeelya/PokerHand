package org.example.card;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final CardValue cardValue;
    private final CardSuit cardSuit;

    public Card(CardValue cardValue, CardSuit cardSuit) {
        if ((cardValue == null) || (cardSuit == null)) {
            throw new IllegalArgumentException("Incorrect card");
        }

        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public int getPower() {
        return cardValue.getPower();
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, cardSuit);
    }

    @Override
    public String toString() {
        return cardValue.toString() + cardSuit.toString();
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(getPower(), o.getPower());
    }
}