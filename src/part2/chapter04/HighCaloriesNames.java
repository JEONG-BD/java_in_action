package part2.chapter04;

import static java.util.stream.Collectors.toList;
import static part2.Dish.menu;

import java.util.List;

public class HighCaloriesNames {

    public static void main(String[] args) {
        List<String> names = menu.stream()
                                .peek(System.out::println)
                                .filter(d -> d.getCalories() > 300)
                                .peek(System.out::println)
                                .map(d -> d.getName())
                                .collect(toList());
        
        for (String string : names) {
            System.out.println("name : " + string);
        }

        long namesCount = menu.stream()
                                .peek(System.out::println)
                                .filter(d -> d.getCalories() > 300)
                                .peek(System.out::println)
                                .map(d -> d.getName())
                                .count();
        System.out.println(namesCount);
    }


}
