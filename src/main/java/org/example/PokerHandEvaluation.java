package org.example;

import java.util.*;

import static java.lang.Integer.compare;
import static java.util.Collections.max;
import static org.example.Combination.*;
import static org.example.CombinationDetector.sequenceMap;

public class PokerHandEvaluation {

    private final Set<Card> cardSet1;
    private final Set<Card> cardSet2;
    private final Combination combination;

    public PokerHandEvaluation(Set<Card> cardSet1, Set<Card> cardSet2, Combination combination) {
        this.cardSet1 = cardSet1;
        this.cardSet2 = cardSet2;
        this.combination = combination;
    }

    public int evaluate() {
        if ((combination == HIGH_CARD) || (combination == STRAIGHT) || (combination == FLUSH)
                || (combination == STRAIGHT_FLUSH) || (combination == ROYAL_FLUSH)) {
            return compare(max(cardSet2).getCardValue().getPower(), max(cardSet1).getCardValue().getPower());
        }

        Map<CardValue, Integer> sequenceMap1 = sequenceMap(cardSet1);
        Map<CardValue, Integer> sequenceMap2 = sequenceMap(cardSet2);
        CardValue card;
        CardValue value1;
        CardValue value2;
        CardValue kicker1 = CardValue.TWO;
        CardValue kicker2 = kicker1;
        int compareValues;

        if (combination == TWO_PAIRS) {
            Set<CardValue> pairs = new HashSet<>();

            for (CardValue value : sequenceMap1.keySet()) {
                if (sequenceMap1.get(value) == 2) {
                    pairs.add(value);
                } else {
                    kicker1 = value;
                }
            }

            card = max(pairs, Comparator.comparing(CardValue::getPower));
            pairs.clear();

            for (CardValue value : sequenceMap2.keySet()) {
                if (sequenceMap2.get(value) == 2) {
                    pairs.add(value);
                } else {
                    kicker2 = value;
                }
            }

            compareValues = compare(card.getPower(), max(pairs, Comparator.comparing(CardValue::getPower)).getPower());

            if (compareValues != 0) {
                return compareValues;
            } else if (kicker1 != null) {
                return compare(kicker1.getPower(), kicker2.getPower());
            } else {
                return 0;
            }
        }

        if ((combination == PAIR) || (combination == THREE_OF_A_KIND) || (combination == FOUR_OF_A_KIND)) {
            Map<String, CardValue> cardAndKickerMap1 = getSequenceCardAndKicker(sequenceMap1, sequenceMap2);
            Map<String, CardValue> cardAndKickerMap2 = getSequenceCardAndKicker(sequenceMap2, sequenceMap1);
            kicker1 = cardAndKickerMap1.get("kicker");
            kicker2 = cardAndKickerMap2.get("kicker");
            value1 = cardAndKickerMap1.get("card");
            value2 = cardAndKickerMap2.get("card");
            compareValues = compare(value1.getPower(), value2.getPower());

            if (compareValues != 0) {
                return compareValues;
            } else if (kicker1 != null) {
                return compare(kicker1.getPower(), kicker2.getPower());
            } else {
                return 0;
            }
        }

        if (combination == FULL_HOUSE) {
            compareValues = compareFullHouse(sequenceMap1, sequenceMap2, 3);

            // if 3s are equal compare 2s
            if (compareValues == 0) {
                return compareFullHouse(sequenceMap1, sequenceMap2, 2);
            }

            return compareValues;
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