package org.example;

public class Main {

    public static void main(String[] args) {
        PokerHand hand = new PokerHand(args[0]);
        System.out.println(hand + " " + hand.getCombination());
    }
}