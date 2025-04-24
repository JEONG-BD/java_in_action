package main.part1.chapter02;
import static main.part1.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.part1.Apple;
import main.part1.Color;
public class FilteringApplesV1 {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        

            List<Apple> greenResult = filterApplesPredicate(inventory, new AppleGreenColorPredicate());
            List<Apple> redResult = filterApplesPredicate(inventory, new AppleRedColorPredicate());
            
            for (Apple apple : greenResult) {
                System.out.println(apple);
            }

            for (Apple apple : redResult) {
                System.out.println(apple);
            }

            prettyPrintApple(inventory, new AppleFancyFormatter());
            prettyPrintApple(inventory, new AppleSimpleFormatter());

            // anonymous class 
            List<Apple> redApples = filterApplesPredicate(inventory, new ApplePredicate() {
                public boolean test(Apple apple){
                    return RED.equals(apple.getColor());
                }
            });
            System.out.println(redApples);
        }

    public static List<Apple> filterGreenApples(List<Apple> invertory){
        
        List<Apple> result = new ArrayList<>();
        for (Apple apple : invertory) {
            if(GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterRedApples(List<Apple> invertory){
        
        List<Apple> result = new ArrayList<>();
        for (Apple apple : invertory) {
            if(RED.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    // value parameter 
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result; 
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > 150){
                result.add(apple);
            }
        }
        
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if((flag && apple.getColor().equals(color)) || 
                (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        
        return result;
    }

    public static List<Apple> filterApplesPredicate(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
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
