package main.part1.chapter02;
import static main.part1.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import main.part1.Apple;
import main.part1.Color;

public class FilteringApplesV2 {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        
            
            List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
            List<Apple> result2 = filter(inventory, apple -> GREEN.equals(apple.getColor()));
            List<Apple> result3 = filter(inventory, apple -> apple.getWeight() > 150 );

            System.out.println(result2);            
            System.out.println(result3);

        }

 
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        
        return result;
    }

    public static <T> List<T> filter(List<T> inventory, Predicate<T> predicate){
        List<T> result = new ArrayList<>();

        for (T apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        
        for (Apple apple : inventory) {
           String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

}
