package part1.chapter03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import part1.Apple;
import part1.Color;
public class ComparatorMain {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
            new Apple(300, Color.GREEN),
            new Apple(40, Color.RED),
            new Apple(180, Color.GREEN),
            new Apple(200, Color.RED));
 
        // 1단계 
        System.out.println("1단계 코드 전달");
        inventory.sort(new AppleComparator());
        for (Apple apple : inventory) {
            System.out.println(apple);
        }


        // 2단계 
        System.out.println("2단계 익명 클래스 사용");

        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return Integer.compare(a1.getWeight(), a2.getWeight());
            }
        });

        System.out.println("3단계 람다 사용");
        inventory.sort((a1, a2) -> Integer.compare(a1.getWeight(), a1.getWeight()));
        
        System.out.println("4단계 메서드 참조");
        inventory.sort(Comparator.comparingInt(Apple::getWeight).reversed());
       

    }

    static public class AppleComparator implements Comparator<Apple>{

        @Override
        public int compare(Apple a1, Apple a2) {
            return Integer.compare(a1.getWeight(), a2.getWeight());
        }
    }
}
