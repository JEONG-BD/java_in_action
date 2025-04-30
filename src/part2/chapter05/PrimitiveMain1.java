package part2.chapter05;

import static part2.Dish.menu;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import part2.Dish;

public class PrimitiveMain1 {
    public static void main(String[] args) {
        int calories = menu.stream()
                        .mapToInt(Dish::getCalories)
                        .sum();
        
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        stream.forEach(System.out::println); 
        
        OptionalInt maxCalories = menu.stream()
                                    .mapToInt(Dish::getCalories)
                                    .max();
        System.out.println(maxCalories.getAsInt());

        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                                        .filter(n -> n % 2 == 0);
    
        IntStream evenNumbers2 = IntStream.range(1, 100)
                                        .filter(n -> n % 2 == 0);

        evenNumbers.forEach(System.out::println);
        evenNumbers2.forEach(System.out::println);
    
    }
}
