package org.example.combination;

import org.example.sort.*;

import java.util.EnumMap;
import java.util.Map;

public enum Combination {
    HIGH_CARD(1), PAIR(2),
    TWO_PAIRS(3), THREE_OF_A_KIND(4), STRAIGHT(5),
    FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

    private final int power;
    private static final Map<Combination, PokerHandPowerEvaluator> HAND_POWER_EVALUATOR_MAP = new EnumMap<>(Combination.class);

    static {
        HAND_POWER_EVALUATOR_MAP.put(HIGH_CARD,       new HighCardPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(FLUSH,           new HighCardPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(ROYAL_FLUSH,     new HighCardPowerEvaluator());

        HAND_POWER_EVALUATOR_MAP.put(STRAIGHT,        new StraightPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(STRAIGHT_FLUSH,  new StraightPowerEvaluator());

        HAND_POWER_EVALUATOR_MAP.put(TWO_PAIRS,       new HandThenKickerPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(PAIR,            new HandThenKickerPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(THREE_OF_A_KIND, new HandThenKickerPowerEvaluator());
        HAND_POWER_EVALUATOR_MAP.put(FOUR_OF_A_KIND,  new HandThenKickerPowerEvaluator());

        HAND_POWER_EVALUATOR_MAP.put(FULL_HOUSE,      new FullHousePowerEvaluator());
    }

    Combination(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public static PokerHandPowerEvaluator getEvaluatorByCombination(Combination combination) {
        return HAND_POWER_EVALUATOR_MAP.get(combination);
    }
}