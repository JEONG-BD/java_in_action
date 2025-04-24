package main.part1.chapter03;

import static main.part1.Color.GREEN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import main.part1.Apple;
import main.part1.Color;

public class LambdaV1 {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(90, Color.RED),
            new Apple(120, Color.RED));        
    
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return Integer.compare(a1.getWeight(), a2.getWeight());
            }
        };

        inventory.sort(byWeight);
        for (Apple apple : inventory) {
            System.out.println(apple);
        }

        // Comparator<Apple> byWeight2  = (a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight());
        
        // for (Apple apple : inventory) {
        //     System.out.println(apple);
        // }

        // filter(inventory, new Predicate<Apple>() {
        //     public boolean test(Apple apple){
        //         return GREEN.equals(apple.getColor());
        //     }
        // });


        filter(inventory, (Apple apple) -> GREEN.equals(apple.getColor()));
        //filter(inventory, (Apple apple) -> GREEN.equals(Apple::getColor));
    }

    static List<Apple> filter(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result; 
    } 
}
