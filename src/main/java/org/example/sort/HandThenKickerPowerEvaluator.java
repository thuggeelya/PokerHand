package org.example.sort;

import org.example.card.Card;
import org.example.card.CardValue;

import java.util.*;

/**
 * Class is used for non-five-card combination power evaluation if kickers are present.
 */
public class HandThenKickerPowerEvaluator extends PokerHandPowerEvaluator {

    @Override
    public List<Integer> evaluateHandPower(Set<Card> cardSet) {
        // min capacity - 2 (four of a kind + kicker)
        // max capacity - 4 (pair + 3 kickers)
        List<Integer> powerValues = new ArrayList<>(4);
        Map<CardValue, Integer> sequenceMap = getSequenceMap(cardSet);
        Set<CardValue> pairValues = new HashSet<>();
        List<Integer> kickers = new ArrayList<>();

        for (CardValue value : sequenceMap.keySet()) {
            if (sequenceMap.get(value) > 1) {
                pairValues.add(value);
            } else {
                kickers.add(value.getPower());
            }
        }

        powerValues.add(Collections.max(pairValues, Comparator.comparing(CardValue::getPower)).getPower());

        // if two pairs
        if (pairValues.size() != 1) {
            powerValues.add(Collections.min(pairValues, Comparator.comparing(CardValue::getPower)).getPower());
        }

        kickers.sort(Comparator.reverseOrder());
        powerValues.addAll(kickers);
        return powerValues;
    }
}