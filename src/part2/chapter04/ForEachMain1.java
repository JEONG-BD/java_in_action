package part2.chapter04;

import static java.util.stream.Collectors.toList;
import static part2.Dish.menu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import part2.Dish;

public class ForEachMain1 {


    public static void main(String[] args) {
        // before 
        List<String> names = new ArrayList<>();
        for (Dish dish : menu) {
            names.add(dish.getName());
        }

        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            names.add(dish.getName());
        }

        menu.stream()
            .map(Dish::getName)
            .collect(toList());
    }
}
