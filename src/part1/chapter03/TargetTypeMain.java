package part1.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import part1.Apple;
import part1.Color;

public class TargetTypeMain {

    public static void main(String[] args) {
        List<Apple> invertory = Arrays.asList(
            new Apple(100, Color.GREEN),
            new Apple(330, Color.RED),
            new Apple(90, Color.RED),
            new Apple(400, Color.GREEN)
        );

        List<Apple> heavierThan150 = filter(invertory, apple -> apple.getWeight() > 150);
        for (Apple apple1 : heavierThan150) {
            System.out.println(apple1);
        }
    }

    @FunctionalInterface
    static interface Predicate<T> {
    
        boolean test(T t);
        
    }
        public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)){
                System.out.println(t);
                results.add(t);
            }
        }
        return results;
    }
}
