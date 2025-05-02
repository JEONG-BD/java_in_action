package part2.chapter06;
import static java.util.stream.Collectors.*;
import part2.Dish;

import java.util.List;
import java.util.Map;

import static part2.Dish.menu;
public class PartitionByMain {
    public static void main(String[] args) {
        Map<Boolean, List<String>> partitionMenu = getPartitionMenu();

        List<String> vegetarianDishes = filterMenu();

        System.out.println("vegetarianDishes = " + vegetarianDishes);
        System.out.println("partitionMenu = " + partitionMenu);
    }

    private static List<String> filterMenu() {
        List<String> vegetarianDishes = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .collect(toList());
        return vegetarianDishes;
    }

    private static Map<Boolean, List<String>> getPartitionMenu() {
        Map<Boolean, List<String>> partitionMenu = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        mapping(Dish::getName, toList())));
        return partitionMenu;
    }
}
