package org.example.sort;

import org.example.card.Card;
import org.example.card.CardValue;
import org.example.combination.CombinationDetector;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class PokerHandPowerEvaluator {

    /**
     * <p><b>The rules apply when comparing hands with the same combination:</b></p>
     * <ol>1. Five-card, no-combination - are compared by the highest card. If they are equal, then goes deeper comparison.</ol>
     * <ol>2. Pairs, three of a kind, four of a kind - at card value of the cards included in the hand. If they are equal - according to the kicker.</ol>
     * <ol>3. Full House - at card value of threes. If they are equal - by pair rank (power).</ol>
     * <ol>4. Two pairs - seniors are compared. If they are equal, the lower pairs are matched. If both are equal - according to the kicker.</ol>
     *
     * @return A list of numbers which are representing the powers
     * according to the Texas hold 'em hand comparison rules.
     */
    public abstract List<Integer> evaluateHandPower(Set<Card> cardSet);

    protected final Map<CardValue, Integer> getSequenceMap(Set<Card> cardSet) {
        return CombinationDetector.getSequenceMap(cardSet);
    }
}