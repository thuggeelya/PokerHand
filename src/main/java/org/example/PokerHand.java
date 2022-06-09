package org.example;

import org.example.card.Card;
import org.example.combination.Combination;
import org.example.combination.CombinationDetector;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.compare;
import static org.example.card.Parser.getCards;

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
        this.combination = CombinationDetector.getCombination(cardSet);
    }

    public Combination getCombination() {
        return combination;
    }

    @Override
    public int compareTo(PokerHand o) {
        int combinationsComparisonResult = compare(o.combination.getPower(), combination.getPower());

        if (combinationsComparisonResult == 0) {
            List<Integer> oPowers = Combination.getEvaluatorByCombination(combination).evaluateHandPower(o.cardSet);
            List<Integer> powers = Combination.getEvaluatorByCombination(combination).evaluateHandPower(cardSet);
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

        return combinationsComparisonResult;
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