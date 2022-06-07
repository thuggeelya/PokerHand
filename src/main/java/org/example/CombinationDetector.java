package org.example;

import java.util.*;

public class CombinationDetector {

    private final Set<Card> cardSet;

    public CombinationDetector(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }

    public Combination getCombination() {
        Collection<Integer> sequences = sequenceMap(cardSet).values();
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
            return Combination.FOUR_OF_A_KIND;
        } else if (fullHouse) {
            return Combination.FULL_HOUSE;
        } else if (twoPairs) {
            return Combination.TWO_PAIRS;
        } else if (threeOfAKind) {
            return Combination.THREE_OF_A_KIND;
        } else if (pair) {
            return Combination.PAIR;
        } else if (royalFlush) {
            return Combination.ROYAL_FLUSH;
        } else if (straightFlush) {
            return Combination.STRAIGHT_FLUSH;
        } else if (flush) {
            return Combination.FLUSH;
        } else if (straight) {
            return Combination.STRAIGHT;
        } else {
            return Combination.HIGH_CARD;
        }
    }

    private boolean isStraight() {
        List<Integer> powers = new ArrayList<>();
        cardSet.forEach(c -> powers.add(c.getPower()));
        Collections.sort(powers);

        if ((Collections.max(powers) == 14) && (powers.get(3) != 13)) {
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
        return suits.stream().allMatch(card -> card.getCardSuit() == suits.get(0).getCardSuit());
    }

    public static Map<CardValue, Integer> sequenceMap(Set<Card> cardSet) {
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