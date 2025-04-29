package part2.chapter05;

import static java.util.stream.Collectors.toList;
import static part2.Dish.menu;

import java.util.List;

import part2.Dish;

public class StreamMain1 {
    public static void main(String[] args) {

        
        for (Dish dish : menu) {
            System.out.println(dish);
        }
        System.out.println("==== filter ====");
        
        menu.stream()
            .filter(d -> d.getCalories() < 320)
            .forEach(System.out::println);;
        
        System.out.println("==== takeWhile ====");
        menu.stream()
            .takeWhile(d -> d.getCalories() < 320)
            .forEach(System.out::println);

        System.out.println("==== dropWhile ====");
        menu.stream()
            .dropWhile(d -> d.getCalories() < 320)
            .forEach(System.out::println);
    
        
        List<Dish> limitDishes = 
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        
        List<Dish> skipDishes = 
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
                                
    }
}
