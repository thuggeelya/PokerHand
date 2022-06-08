package org.example.combination;

import org.example.card.Card;
import org.example.card.CardSuit;
import org.example.card.CardValue;

import java.util.*;

import static org.example.combination.Combination.*;

public class CombinationDetector {

    private final Set<Card> cardSet;

    public CombinationDetector(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }

    public Combination getCombination() {
        Collection<Integer> sequences = getSequenceMap(cardSet).values();
        boolean pair = sequences.contains(2);
        boolean twoPairs = pair && sequences.size() == 3;
        boolean threeOfAKind = sequences.contains(3);
        boolean fullHouse = pair && threeOfAKind;
        boolean fourOfAKind = sequences.contains(4);
        boolean straight = isStraight();
        boolean flush = isFlush();
        boolean straightFlush = straight && flush;
        boolean royalFlush = straightFlush && cardSet.stream().noneMatch(c -> c.getPower() < 10);

        if (fourOfAKind) {
            return FOUR_OF_A_KIND;
        } else if (fullHouse) {
            return FULL_HOUSE;
        } else if (twoPairs) {
            return TWO_PAIRS;
        } else if (threeOfAKind) {
            return THREE_OF_A_KIND;
        } else if (pair) {
            return PAIR;
        } else if (royalFlush) {
            return ROYAL_FLUSH;
        } else if (straightFlush) {
            return STRAIGHT_FLUSH;
        } else if (flush) {
            return FLUSH;
        } else if (straight) {
            return STRAIGHT;
        } else {
            return HIGH_CARD;
        }
    }

    private boolean isStraight() {
        List<Integer> powers = new ArrayList<>();
        cardSet.forEach(c -> powers.add(c.getPower()));
        Collections.sort(powers);
        int acePower = CardValue.ACE.getPower();

        if ((Collections.max(powers) == acePower) && (powers.get(3) != acePower - 1)) {
            powers.set(4, 1);
            Collections.sort(powers);
        }

        for (int i = 0; i < 4; i++) {
            if (powers.get(i) != powers.get(i + 1) - 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isFlush() {
        List<Card> suits = new ArrayList<>(cardSet);
        CardSuit suit = suits.get(0).getCardSuit();
        return suits.stream().allMatch(card -> card.getCardSuit() == suit);
    }

    public static Map<CardValue, Integer> getSequenceMap(Set<Card> cardSet) {
        Map<CardValue, MutableInt> sequenceMap = new HashMap<>();
        Map<CardValue, Integer> resultMap = new HashMap<>();

        for (Card card : cardSet) {
            CardValue cardValue = card.getCardValue();
            MutableInt currentNumber = sequenceMap.get(cardValue);

            if (currentNumber == null) {
                MutableInt defaultInt = new MutableInt();
                currentNumber = sequenceMap.putIfAbsent(cardValue, defaultInt);
                currentNumber = (currentNumber == null) ? defaultInt : currentNumber;
            }

            currentNumber.increment();
        }

        for (Map.Entry<CardValue, MutableInt> mapEntry : sequenceMap.entrySet()) {
            resultMap.put(mapEntry.getKey(), mapEntry.getValue().get());
        }

        return resultMap;
    }
}