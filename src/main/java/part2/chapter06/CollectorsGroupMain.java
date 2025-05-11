package part2.chapter06;
import part2.Dish;
import part2.Type;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;
import static part2.Dish.menu;

public class CollectorsGroupMain {
    public static void main(String[] args) {
        Map<Type, List<Dish>> dishesByType = groupingByType1();

        System.out.println(dishesByType);

        groupingByTypeAndCalorices();

        //칼로리가 넘는 요리만 필터링 
        Map<Type, List<String>> caloricDishesType = groupingTypeAndFilter();
        System.out.println("caloricDishesType = " + caloricDishesType);

        Map<Type, List<String>> caloricDishesType2 = groupingByAndFilterByCaloric();
        System.out.println("caloricDishesType2 = " + caloricDishesType2);

        Map<Type, Set<String>> collect = groupingByAndFlatMap();
        System.out.println("collect = " + collect);

        Map<Type, Map<CaloricLevel, List<Dish>>> typeMapMap = groupingByInnerGroupingBy();
        Map<Type, Dish> mostCaloricByType = groupingByAndCollectAndThen();

        System.out.println("mostCaloricByType = " + mostCaloricByType);
    }

    private static Map<Type, List<String>> groupingByAndFilterByCaloric() {
        Map<Type, List<String>> caloricDishesType2 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500,
                                mapping(Dish::getName, toList())
                        )
                ));
        return caloricDishesType2;
    }

    private static Map<Type, Dish> groupingByAndCollectAndThen() {
        Map<Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)
                ));
        return mostCaloricByType;
    }

    private static Map<Type, Map<CaloricLevel, List<Dish>>> groupingByInnerGroupingBy() {
        return menu.stream()
                .collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400)
                                        return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700)
                                        return CaloricLevel.NORMAL;
                                    else
                                        return CaloricLevel.FAT;
                                })));
    }

    private static Map<Type, Set<String>> groupingByAndFlatMap() {
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Type, Set<String>> collect = menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        return collect;
    }

    private static Map<Type, List<String>> groupingTypeAndFilter() {
        Map<Type, List<String>> caloricDishesType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        return caloricDishesType;
    }

    private static void groupingByTypeAndCalorices() {
        menu.stream()
                .collect(
                    groupingBy(dish -> {
                        if (dish.getCalories() < 400)
                            return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700)
                            return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT;
                    }));
    }

    private static Map<Type, List<Dish>> groupingByType1() {
        Map<Type, List<Dish>> dishesByType = menu.stream()
                .collect(groupingBy(Dish::getType));
        return dishesByType;
    }

    public static enum CaloricLevel {
        DIET,
        NORMAL,
        FAT
    }
}
