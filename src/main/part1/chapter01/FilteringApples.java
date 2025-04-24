package main.part1.chapter01;
import static main.part1.Color.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.part1.Apple;
import main.part1.Color;
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        

            List<Apple> greenResult = filterGreenApples(inventory);
            System.out.println("1 Green");
            for (Apple apple : greenResult) {
                System.out.println("apple = " + apple);
            }
            
            System.out.println("2 Red");
            List<Apple> redResult = filterRedApples(inventory);
            for (Apple apple : redResult) {
                System.out.println("apple = " + apple);
            }
            System.out.println("3 Color");

            List<Apple> colorResult = filterApplesByColor(inventory, GREEN);
            
            for (Apple apple : colorResult) {
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

}
