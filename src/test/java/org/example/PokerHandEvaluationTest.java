package org.example;

import org.example.hand.PokerHand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.example.combination.Combination.*;
import static org.junit.Assert.assertEquals;

public class PokerHandEvaluationTest {

    @Test
    public void sortHands() {
        PokerHand highCard =             new PokerHand("2D 3C 8S JH 4D");
        PokerHand anotherHighCard =      new PokerHand("2D 3C 9S JH 4D");
        PokerHand pair =                 new PokerHand("2D 2C 8S JH 4D");
        PokerHand anotherPair =          new PokerHand("2D 8C 8S JH 4D");
        PokerHand twoPairs =             new PokerHand("2D 2C 8S 8H 4D");
        PokerHand anotherTwoPairs =      new PokerHand("2D 2C 8S 8H TD");
        PokerHand three =                new PokerHand("2D 2C 2S JH 4D");
        PokerHand anotherThree =         new PokerHand("5D 5C 5S JH 4D");
        PokerHand straight =             new PokerHand("2D 3C 4S 5H AD");
        PokerHand anotherStraight =      new PokerHand("2D 3C 4S 5H 6D");
        PokerHand flush =                new PokerHand("2D 3D 8D JD QD");
        PokerHand anotherFlush =         new PokerHand("2D 3D 8D JD KD");
        PokerHand fullHouse =            new PokerHand("2D 2C 2S JH JD");
        PokerHand anotherFullHouse =     new PokerHand("AD AC AS JH JD");
        PokerHand four =                 new PokerHand("2D 2C 2S 2H 7D");
        PokerHand anotherFour =          new PokerHand("2D 2C 2S 2H 8D");
        PokerHand straightFlush =        new PokerHand("2D 3D 4D 5D AD");
        PokerHand anotherStraightFlush = new PokerHand("2D 3D 4D 5D 6D");
        PokerHand royalFlush =           new PokerHand("AH KH QH JH TH");

        ArrayList<PokerHand> hands = new ArrayList<>();
        ArrayList<PokerHand> handsExpected = new ArrayList<>();

        // from high to low power
        handsExpected.add(royalFlush);
        handsExpected.add(anotherStraightFlush);
        handsExpected.add(straightFlush);
        handsExpected.add(anotherFour);
        handsExpected.add(four);
        handsExpected.add(anotherFullHouse);
        handsExpected.add(fullHouse);
        handsExpected.add(anotherFlush);
        handsExpected.add(flush);
        handsExpected.add(anotherStraight);
        handsExpected.add(straight);
        handsExpected.add(anotherThree);
        handsExpected.add(three);
        handsExpected.add(anotherTwoPairs);
        handsExpected.add(twoPairs);
        handsExpected.add(anotherPair);
        handsExpected.add(pair);
        handsExpected.add(anotherHighCard);
        handsExpected.add(highCard);

        // random
        hands.add(anotherPair);
        hands.add(highCard);
        hands.add(fullHouse);
        hands.add(anotherThree);
        hands.add(royalFlush);
        hands.add(straightFlush);
        hands.add(anotherHighCard);
        hands.add(pair);
        hands.add(straight);
        hands.add(twoPairs);
        hands.add(three);
        hands.add(anotherStraightFlush);
        hands.add(four);
        hands.add(anotherStraight);
        hands.add(flush);
        hands.add(anotherFlush);
        hands.add(anotherFullHouse);
        hands.add(anotherFour);
        hands.add(anotherTwoPairs);

        // sort random
        Collections.sort(hands);
        assertEquals(handsExpected, hands);
    }

    @Test(expected = IllegalArgumentException.class)
    public void duplicateCard() {
        new PokerHand("2D 2D JH TC AH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fewCards() {
        new PokerHand("2D JH TC AH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void noCards() {
        new PokerHand("Hello, my friend");
    }

    @Test
    public void highCard() {
        assertEquals(new PokerHand("2D JH TC AH 3D").getCombination(), HIGH_CARD);
    }

    @Test
    public void pair() {
        assertEquals(new PokerHand("2D 2H TC AH 3D").getCombination(), PAIR);
    }

    @Test
    public void twoPairs() {
        assertEquals(new PokerHand("2D 2H TC TH 3D").getCombination(), TWO_PAIRS);
    }

    @Test
    public void threeOfAKind() {
        assertEquals(new PokerHand("2D 2H 2C AH 3D").getCombination(), THREE_OF_A_KIND);
    }

    @Test
    public void straight() {
        assertEquals(new PokerHand("2D 3H 4C 5H AD").getCombination(), STRAIGHT);
    }

    @Test
    public void flush() {
        assertEquals(new PokerHand("2D 3D TD JD QD").getCombination(), FLUSH);
    }

    @Test
    public void fullHouse() {
        assertEquals(new PokerHand("2D 2H 2C TH TD").getCombination(), FULL_HOUSE);
    }

    @Test
    public void fourOfAKind() {
        assertEquals(new PokerHand("2D 2H 2C 2S 3D").getCombination(), FOUR_OF_A_KIND);
    }

    @Test
    public void straightFlush() {
        assertEquals(new PokerHand("2D 3D 4D 5D AD").getCombination(), STRAIGHT_FLUSH);
    }

    @Test
    public void royalFlush() {
        assertEquals(new PokerHand("TD JD QD KD AD").getCombination(), ROYAL_FLUSH);
    }
}
