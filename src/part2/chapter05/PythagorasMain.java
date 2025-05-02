package part2.chapter05;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagorasMain {
    public static void main(String[] args) {

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100)
        .boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
        )
        .filter(t -> t[2] % 1 == 0); // 정수인지 확인

    pythagoreanTriples2.forEach(t -> 
        System.out.printf("(%.0f, %.0f, %.0f)%n", t[0], t[1], t[2])
    );

    }
}
