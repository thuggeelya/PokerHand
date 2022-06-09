package org.example;

public class Main {

    public static void main(String[] args) {
        PokerHand hand1 = new PokerHand(args[0]);
        PokerHand hand2 = new PokerHand(args[1]);
        System.out.println(hand1 + " " + hand1.getCombination());
        System.out.println(hand2 + " " + hand2.getCombination());
    }
}