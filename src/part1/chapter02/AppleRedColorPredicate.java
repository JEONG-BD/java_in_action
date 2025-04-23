package part1.chapter02;
import  static part1.chapter02.Color.*;;
public class AppleRedColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor());
    }


}
