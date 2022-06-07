package org.example;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.compare;
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

    public Combination getCombination() {
        return combination;
    }

    @Override
    public int compareTo(PokerHand o) {
        int combinationsResult = compare(o.combination.getPower(), combination.getPower());

        if (combinationsResult == 0) {
            List<Integer> oPowers = new PokerHandEvaluation(o.cardSet, combination).evaluatePowersDescToCompare();
            List<Integer> powers = new PokerHandEvaluation(cardSet, combination).evaluatePowersDescToCompare();
            int oPower, power;

            for (int i = 0; i < powers.size(); i++) {
                oPower = oPowers.get(i);
                power = powers.get(i);

                if (oPower != power) {
                    return compare(oPower, power);
                }
            }

            return 0;
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