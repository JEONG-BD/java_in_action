package part2.chapter04;

import part2.Dish;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class HighCaloriesNames {

    public static void main(String[] args) {
        List<String> names = Dish.menu.stream()
                                .peek(System.out::println)
                                .filter(d -> d.getCalories() > 300)
                                .peek(System.out::println)
                                .map(d -> d.getName())
                                .collect(toList());
        
        for (String string : names) {
            System.out.println("name : " + string);
        }

        long namesCount = Dish.menu.stream()
                                .peek(System.out::println)
                                .filter(d -> d.getCalories() > 300)
                                .peek(System.out::println)
                                .map(d -> d.getName())
                                .count();
        System.out.println(namesCount);
    }


}
