package org.example;

import java.util.*;

import static java.lang.Integer.compare;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static org.example.Combination.*;
import static org.example.CombinationDetector.sequenceMap;

public class PokerHandEvaluation {

    private final Set<Card> cardSet;
    private final Combination combination;
    private final List<Integer> powerValues = new ArrayList<>(5);

    public PokerHandEvaluation(Set<Card> cardSet, Combination combination) {
        this.cardSet = cardSet;
        this.combination = combination;
    }

    /**
     * @return Collection of power-numbers according to the hand comparison rules (card by card).
     */
    public List<Integer> evaluatePowersDescToCompare() {
        if ((combination == HIGH_CARD) || (combination == STRAIGHT) || (combination == FLUSH)
                || (combination == STRAIGHT_FLUSH) || (combination == ROYAL_FLUSH)) {
            return evaluatePowersByHighCard();
        }

        Map<CardValue, Integer> sequenceMap = sequenceMap(cardSet);

        if (combination == TWO_PAIRS) {
            return evaluatePowersByTwoPairsThenKicker(sequenceMap);
        }

        if ((combination == PAIR) || (combination == THREE_OF_A_KIND) || (combination == FOUR_OF_A_KIND)) {
            return evaluatePowersByLayoutThenKicker(sequenceMap);
        }

        if (combination == FULL_HOUSE) {
            return evaluatePowersByFullHouse(sequenceMap);
        }

        return Collections.emptyList();
    }

    private List<Integer> evaluatePowersByHighCard() {
        for (Card card : cardSet) {
            powerValues.add(card.getPower());
        }

        Collections.sort(powerValues);
        return powerValues;
    }

    private List<Integer> evaluatePowersByTwoPairsThenKicker(Map<CardValue, Integer> sequenceMap) {
        Set<CardValue> pairValues = new HashSet<>();
        CardValue kicker = CardValue.TWO;

        for (CardValue value : sequenceMap.keySet()) {
            if (sequenceMap.get(value) == 2) {
                pairValues.add(value);
            } else {
                kicker = value;
            }
        }

        powerValues.add(max(pairValues, Comparator.comparing(CardValue::getPower)).getPower());
        powerValues.add(min(pairValues, Comparator.comparing(CardValue::getPower)).getPower());
        powerValues.add(kicker.getPower());
        return powerValues;
    }

    private List<Integer> evaluatePowersByLayoutThenKicker(Map<CardValue, Integer> sequenceMap) {
        CardValue kicker = CardValue.TWO;

        for (CardValue value : sequenceMap.keySet()) {
            if (sequenceMap.get(value) > 1) {
                powerValues.add(value.getPower());
            } else {
                kicker = (value.getPower() > kicker.getPower()) ? value : kicker;
            }
        }

        powerValues.add(kicker.getPower());
        return powerValues;
    }

    private List<Integer> evaluatePowersByFullHouse(Map<CardValue, Integer> sequenceMap) {
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



    private int compareFullHouse(Map<CardValue, Integer> sequenceMap1, Map<CardValue, Integer> sequenceMap2, int frequency) {
        CardValue value1 = CardValue.TWO;
        CardValue value2 = CardValue.TWO;

        for (CardValue value : sequenceMap1.keySet()) {
            if (sequenceMap1.get(value) == frequency) {
                value1 = value;
                break;
            }
        }

        for (CardValue value : sequenceMap2.keySet()) {
            if (sequenceMap2.get(value) == frequency) {
                value2 = value;
                break;
            }
        }

        return compare(value1.getPower(), value2.getPower());
    }

    private Map<String, CardValue> getSequenceCardAndKicker(Map<CardValue, Integer> sequenceMap1, Map<CardValue, Integer> sequenceMap2) {
        Map<String, CardValue> cardAndKickerMap = new HashMap<>();
        CardValue kicker = CardValue.TWO;

        for (CardValue value : sequenceMap1.keySet()) {
            if (sequenceMap1.get(value) > 1) {
                cardAndKickerMap.put("card", value);
            } else {
                if (!sequenceMap2.containsKey(value)) {
                    kicker = (value.getPower() > kicker.getPower()) ? value : kicker;
                }
            }
        }

        cardAndKickerMap.put("kicker", kicker);
        return cardAndKickerMap;
    }
}