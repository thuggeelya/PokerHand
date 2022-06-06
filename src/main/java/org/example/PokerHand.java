package org.example;

import java.util.Objects;
import java.util.Set;

import static org.example.Parser.getCards;

public class PokerHand implements Comparable<PokerHand> {

    private final String cards;
    private final Set<Card> cardSet;
    private final Combination combination;

    public PokerHand(String cards) {
        if ((cards == null) || (cards.isEmpty())) {
            throw new IllegalArgumentException("Empty hand error");
        }

        this.cards = cards;
        this.cardSet = getCards(cards);
        this.combination = new CombinationDetector(cardSet).getCombination();
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    public Combination getCombination() {
        return combination;
    }

    @Override
    public int compareTo(PokerHand o) {
        int combinationsResult = Integer.compare(o.combination.getPower(), combination.getPower());

        if ((combinationsResult == 0) && (combination != Combination.HIGH_CARD)) {
            return new PokerHandEvaluation(o.cardSet, cardSet, combination).evaluate();
        }

        return combinationsResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return cardSet.equals(pokerHand.cardSet) && combination == pokerHand.combination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSet, combination);
    }

    @Override
    public String toString() {
        return "PokerHand{" + cards.trim() + '}';
    }
}