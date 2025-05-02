package part2.chapter05;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import part2.Dish;

public class FilterMain1 {
    public static void main(String[] args) {
        List<Dish> vegetarianMenu =
            Dish.menu.stream()
            .filter(Dish::isVegetarian)
            .collect(toList());
        for (Dish dish : vegetarianMenu) {
            System.out.println(dish);
        }

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);
    }

}
