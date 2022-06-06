package org.example;

public class Main {

    public static void main(String[] args) {
        PokerHand hand = new PokerHand("TH QH JH KH 9H");
        System.out.println(hand.getCombination());
    }
}