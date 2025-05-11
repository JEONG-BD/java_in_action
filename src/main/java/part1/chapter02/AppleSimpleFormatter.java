package part1.chapter02;

import part1.Apple;

public class AppleSimpleFormatter implements AppleFormatter{

    @Override
    public String accept(Apple a) {
        return "An apple of " + a.getWeight() + "g";
    }

}
