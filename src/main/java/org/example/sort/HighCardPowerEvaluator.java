package org.example.sort;

import org.example.card.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Class is used five-card combination power evaluation.
 */
public class HighCardPowerEvaluator extends PokerHandPowerEvaluator {

    @Override
    public List<Integer> evaluateHandPower(Set<Card> cardSet) {
        List<Integer> powerValues = new ArrayList<>(5);

        for (Card card : cardSet) {
            powerValues.add(card.getPower());
        }

        powerValues.sort(Comparator.reverseOrder());
        return powerValues;
    }
}