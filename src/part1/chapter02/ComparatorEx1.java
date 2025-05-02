package part1.chapter02;

import java.util.Arrays;
import java.util.List;

import part1.Apple;
import part1.Color;

public class ComparatorEx1 {
    public static void main(String[] args) {
        // List<Apple> inventory = 
        // List.of(
        //     new Apple(100, Color.GREEN),
        //     new Apple(400, Color.RED),
        //     new Apple(400, Color.RED));
        
        List<Apple>inventory = Arrays.asList(
            new Apple(810, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));  
        
        // inventory.sort(new Comparator<Apple>() {
        //     public int compare(Apple a1, Apple a2){
        //         return Integer.compare(a1.getWeight(), a2.getWeight()); 
        //     }
        // });

        inventory.sort((a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        for (Apple apple : inventory) {
            System.out.println(apple);
        }
    }
}
