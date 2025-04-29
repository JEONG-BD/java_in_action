package part2.chapter05;

import java.util.List;
import java.util.Optional;

public class ReduceMain {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 5, 3, 9);
        int sum = numbers.stream().reduce(0, (a, b) -> a +b)    ;
        System.out.println("sum : " + sum);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        
        Optional<Integer> sum3 = numbers.stream().reduce(Integer::sum);
        Optional<Integer> optMax = numbers.stream().reduce(Integer::max);
        System.out.println(optMax.get());
        Optional<Integer> optMin = numbers.stream().reduce(Integer::min);
        System.out.println(optMin.get());
        
    }
}
