package part1.chapter02;
import static part1.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import part1.Apple;
import part1.Color;
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        

            List<Apple> greenResult = fillterApples(inventory, new AppleGreenColorPredicate());
            List<Apple> redResult = fillterApples(inventory, new AppleRedColorPredicate());

            for (Apple apple : greenResult) {
                System.out.println(apple);
            }

            for (Apple apple : redResult) {
                System.out.println(apple);
            }
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

    public static List<Apple> fillterApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        
        return result;
    }

}
