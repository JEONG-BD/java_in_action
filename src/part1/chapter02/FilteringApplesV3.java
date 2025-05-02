package part1.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import part1.Apple;
import part1.Color;

public class FilteringApplesV3 {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        
            
            System.out.println(inventory);
            inventory.sort(new Comparator<Apple>() {
                public int compare(Apple a1, Apple a2){
                    return Integer.compare(a1.getWeight(), a2.getWeight());
                }
            });

            System.out.println(inventory);
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



}
