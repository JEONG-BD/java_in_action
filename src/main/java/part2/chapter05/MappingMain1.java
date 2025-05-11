package part2.chapter05;

import part2.Dish;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MappingMain1 {
    public static void main(String[] args) {
        List<String> dishNames = Dish.menu.stream()
                                    .map(d -> d.getName())
                                    .collect(toList());

        List<Integer> dishNameLength = Dish.menu.stream()
                                    .map(d -> d.getName())
                                    .map(String::length)
                                    .collect(toList());

        List<String> list = List.of("Hello" ,"World");

        List<String[]> test =list.stream()
                            .map(s -> s.split(""))
                            .peek(System.out::println)
                            .collect(toList());
        
        String[] arrayOfWorks = {"GoodBye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWorks);
        
        list.stream()
            .map(s -> s.split(""))
            .map(Arrays::stream)
            .distinct()
            .peek(System.out::println)
            .collect(toList());

        List<String> result =list.stream()
                                .map(s -> s.split(""))
                                .flatMap(Arrays::stream)
                                .distinct()
                                .collect(toList());

        for (String string : result) {
            System.out.println(string);
        }

    }
}
