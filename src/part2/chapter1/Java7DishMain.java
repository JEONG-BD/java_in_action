package part2.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import java.util.List;
import part2.Dish;
import part2.Type;
public class Java7DishMain {

 
    public static List<Dish> menu;

    static {
        menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 400, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
        );
    }
    public static void main(String[] args) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        
        for (Dish dish : menu) {
            if (dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }    
        }

        for (Dish dish : lowCaloricDishes) {
            System.out.println(dish);
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish dish1, Dish dish2){
                return Integer.compare(dish1.getCalories(), dish2.getCalories());
            }    
        });
        
        // 자바 8 
        List<String> lowCaloricDishesName = menu.stream()
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish:: getCalories))
        .map(Dish::getName)
        .collect(toList());
        
        menu.stream()
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish:: getCalories))
        .map(Dish::getName)
        .forEach(System.out::println);
 

        menu.parallelStream()
        .filter(d -> d.getCalories() < 400)
        .sorted(comparing(Dish:: getCalories))
        .map(Dish::getName)
        .forEach(System.out::println);
 

    }
}
