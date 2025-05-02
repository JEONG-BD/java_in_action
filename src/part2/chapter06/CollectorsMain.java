package part2.chapter06;
import static java.util.stream.Collectors.*;
import static part2.Dish.menu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import part2.Dish;

public class CollectorsMain {
    public static void main(String[] args) {

        long howManyDishhmenu1 = getHowManyDishhmenu1();
        long howManyDishhmenu2 = getHowManyDishhmenu2();
        
        System.out.println("howManyDishhmenu1 = " + howManyDishhmenu1);
        System.out.println("howManyDishhmenu2 = " + howManyDishhmenu2);

        //최대값 최소값
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = getMaxCalorie1(dishComparator);

        int total = totalCalorie();

        IntSummaryStatistics menuStatistics = getStatistics();
        System.out.println(menuStatistics.getMax());
        System.out.println(menuStatistics.getMin());
        System.out.println(menuStatistics.getAverage());
        System.out.println(menuStatistics.getCount());

        String joiningNames = joiningNames1();
        System.out.println("joiningNames = " + joiningNames);

        String joiningNames2 = getJoiningNames2();
        System.out.println("joiningNames2 = " + joiningNames2);


        // reducing
        Integer reducingTotal = getRedcingTotal();
        System.out.println("reducingTotal = " + reducingTotal);
        Optional<Dish> maxDish = getReducingMax();
        System.out.println(maxDish.get());


        Stream<Integer> arrStream = Arrays.asList(1, 2, 3, 4, 5).stream();

        reducingArrStream1(arrStream);

//        List<Integer> result = IntStream.range(0, 1000).parallel().boxed().reduce(
//                new ArrayList<>(),
//                (list, i) -> {
//                    list.add(i);
//                    return list;
//                },
//                (l1, l2) -> {
//                    l1.addAll(l2);
//                    return l1;
//                }
//        );
//        System.out.println(result.size());


        Integer total2 = menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("total2 = " + total2);
        int total3 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("total3 = " + total3);
    }

    private static void reducingArrStream1(Stream<Integer> arrStream) {
        List<Integer> errList = arrStream.reduce(
                new ArrayList<Integer>(), // 초기값
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }

    private static Optional<Dish> getReducingMax() {
        Optional<Dish> maxDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        return maxDish;
    }

    private static Integer getRedcingTotal() {
        Integer reducingTotal = menu.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        return reducingTotal;
    }

    private static String getJoiningNames2() {
        String joiningNames2 = menu.stream()
                .map(Dish::getName).collect(joining(", "));
        return joiningNames2;
    }

    private static String joiningNames1() {
        String joiningNames = menu.stream()
                .map(Dish::getName).collect(joining());
        return joiningNames;
    }

    private static IntSummaryStatistics getStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static Integer totalCalorie() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static Optional<Dish> getMaxCalorie1(Comparator<Dish> dishComparator) {
        return menu.stream().collect(maxBy(dishComparator));
    }

    private static long getHowManyDishhmenu2() {
        return menu.stream().count();
    }

    private static long getHowManyDishhmenu1() {
        return menu.stream().collect(Collectors.counting());
    }

}
