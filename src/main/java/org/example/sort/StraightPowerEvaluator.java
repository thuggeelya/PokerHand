package org.example.sort;

import org.example.card.Card;
import org.example.card.CardValue;

import java.util.*;

public class StraightPowerEvaluator extends PokerHandPowerEvaluator {

    @Override
    public List<Integer> evaluateHandPower(Set<Card> cardSet) {
        List<Integer> powerValues = new ArrayList<>(5);
        int acePower = CardValue.ACE.getPower();

        for (Card card : cardSet) {
            powerValues.add(card.getPower());
        }

        if ((Collections.max(powerValues) == acePower) && !(powerValues.contains(acePower - 1))) {
            powerValues.set(powerValues.indexOf(acePower), 1);
        }

        powerValues.sort(Comparator.reverseOrder());
        return powerValues;
    }
}