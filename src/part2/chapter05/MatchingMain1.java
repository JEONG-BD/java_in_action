package part2.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import part2.Dish;

public class MatchingMain1 {

    public static void main(String[] args) {
        boolean hasVegetarian = Dish.menu.stream()
            .anyMatch(Dish::isVegetarian);
        System.out.println(hasVegetarian);

        boolean isHealthy = Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
        
        boolean isHealthy2 = Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        
        Optional<Dish> optDish = 
            Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        
        System.out.println(optDish);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5); 
        Optional<Integer> firstSquareDivisibleByThree  = 
        someNumbers.stream()
            .map(n -> n * n)
            .filter(n -> n % 3 == 0)
            .findFirst();
        System.out.println(firstSquareDivisibleByThree.get());
                                                                        
    }
}
