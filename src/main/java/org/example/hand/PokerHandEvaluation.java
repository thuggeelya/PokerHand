package org.example.hand;

import org.example.card.Card;
import org.example.combination.Combination;
import org.example.sort.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.example.combination.Combination.*;

public class PokerHandEvaluation {

    private final Set<Card> cardSet;
    private final Combination combination;

    private static final Map<Combination, PokerHandPowerEvaluator> HAND_POWER_EVALUATOR_MAP = new HashMap<>();

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

    public PokerHandEvaluation(Set<Card> cardSet, Combination combination) {
        this.cardSet = cardSet;
        this.combination = combination;
    }

    public List<Integer> evaluateHandPower() {
        return HAND_POWER_EVALUATOR_MAP.get(combination).evaluateHandPower(cardSet);
    }
}