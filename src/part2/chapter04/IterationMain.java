package part2.chapter04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

import part2.Dish;

public class IterationMain {
 
    public static void main(String[] args) {
        
        List<String> highCaloricDishes = new ArrayList<>(); 

        Iterator<Dish> iterator = Dish.menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if(dish.getCalories() > 300){
                highCaloricDishes.add(dish.getName());
            }
        }

        Dish.menu.stream()
        .filter( d ->  d.getCalories() > 300)
        .map(d -> d.getName()).collect(toList());
        
    }
}
