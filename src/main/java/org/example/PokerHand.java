package org.example;

public class PokerHand implements Comparable<PokerHand> {

    private final String cards;
    private final Combination combination;

    public PokerHand(String cards) {
        if ((cards == null) || (cards.isEmpty())) {
            throw new IllegalArgumentException("Empty hand error");
        }

        this.cards = cards;
        this.combination = new CombinationDetector(cards).getCombination();
    }

    public String getCards() {
        return cards;
    }

    public Combination getCombination() {
        return combination;
    }

    @Override
    public int compareTo(PokerHand o) {
        return Integer.compare(combination.getPower(), o.combination.getPower());
    }
}