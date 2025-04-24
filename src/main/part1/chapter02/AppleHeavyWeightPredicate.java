package main.part1.chapter02;

import main.part1.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        
        return apple.getWeight() > 150;
    }

}
