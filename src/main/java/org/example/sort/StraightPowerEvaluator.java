package org.example.sort;

import org.example.card.Card;

import java.util.*;

public class StraightPowerEvaluator extends PokerHandPowerEvaluator {

    @Override
    public List<Integer> evaluateHandPower(Set<Card> cardSet) {
        List<Integer> powerValues = new ArrayList<>(5);

        for (Card card : cardSet) {
            powerValues.add(card.getPower());
        }

        if ((Collections.max(powerValues) == 14) && !(powerValues.contains(13))) {
            powerValues.set(powerValues.indexOf(14), 1);
        }

        powerValues.sort(Comparator.reverseOrder());
        return powerValues;
    }
}