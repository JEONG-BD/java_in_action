package part2.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class PartitionPrimeNumbers {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> booleanListMap = partitionPrime(100);
        booleanListMap.forEach((isPrime, list) -> {
            System.out.println(isPrime ? "소수: " : "소수 아님: ");
            System.out.println(list);
        });
    }

    
    public static Map<Boolean, List<Integer>> partitionPrime(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, (int)Math.sqrt(candidate))
                .noneMatch(i -> candidate % i == 0);
    }
    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt(candidate);
        //return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate % i == 0);
        return primes.stream().takeWhile(i -> i <= candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithInlineCollector(int n) {
        return Stream.iterate(2, i -> i + 1).limit(n)
                .collect(() -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());}},
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
                        },
                        (map1, map2) -> {map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));});
    }


    public static class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<>() {{
                put(true, new ArrayList<>());
                put(false, new ArrayList<Integer>());
            }};
        }
        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc,
                    Integer candidate) -> {
                acc.get(isPrime(acc.get(true),
                        candidate)).add(candidate);
            };
        }
        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1,
                    Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }
        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return i -> i;
        }
        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
