package part2.chapter05;

import java.util.stream.Stream;

public class InfiniteMain {
    public static void main(String[] args) {
        
        Stream.iterate(0, n -> n + 2)
        .limit(10)
        .forEach(System.out::println);


        // Stream.iterate(0, n -> n + 4)
        // .filter(n -> n < 100)
        // .forEach(System.out::println);
        Stream.iterate(0, n -> n + 4)
        .takeWhile(n -> n < 100)
        .forEach(System.out::println);
        

    }
}
