package part1.chapter02;
import static part1.Color.*;

import part1.Apple;;
public class AppleGreenColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }


}
