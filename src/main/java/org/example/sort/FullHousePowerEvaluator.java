package org.example.sort;

import org.example.card.Card;
import org.example.card.CardValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FullHousePowerEvaluator extends PokerHandPowerEvaluator {

    @Override
    public List<Integer> evaluateHandPower(Set<Card> cardSet) {
        List<Integer> powerValues = new ArrayList<>(2);
        Map<CardValue, Integer> sequenceMap = getSequenceMap(cardSet);
        powerValues.add(getPowerByFrequency(sequenceMap, 3)); // first compare 3s
        powerValues.add(getPowerByFrequency(sequenceMap, 2)); // then  compare 2s
        return powerValues;
    }

    private int getPowerByFrequency(Map<CardValue, Integer> sequenceMap, int frequency) {
        for (CardValue value : sequenceMap.keySet()) {
            if (sequenceMap.get(value) == frequency) {
                return value.getPower();
            }
        }

        return 0;
    }
}