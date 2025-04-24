package main.part1.chapter02;
import static main.part1.Color.*;

import main.part1.Apple;;
public class AppleGreenColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }


}
