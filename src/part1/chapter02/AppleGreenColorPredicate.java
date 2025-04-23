package part1.chapter02;
import  static part1.chapter02.Color.*;;
public class AppleGreenColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }


}
