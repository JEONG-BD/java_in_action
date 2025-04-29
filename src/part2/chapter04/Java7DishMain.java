package part2.chapter04;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import java.util.List;
import part2.Dish;
import static part2.Dish.menu;

public class Java7DishMain {

 
  
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
