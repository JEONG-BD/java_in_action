package main.part1.chapter02;

import main.part1.Apple;

public class AppleSimpleFormatter implements AppleFormatter{

    @Override
    public String accept(Apple a) {
        return "An apple of " + a.getWeight() + "g";
    }

}
