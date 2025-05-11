package part2.chapter07;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.stream.LongStream;


public class ParallelStreamSideEffect {
    public static void main(String[] args) {
        long sum = sideEffectSum(10);
        System.out.println("sum = " + sum);
        long parallelSum = sideEffectParallelSum(10);
        System.out.println("parallelSum = " + parallelSum);
    }

    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


    public static class Accumulator {
        private long total = 0;
        public void add(long value){
            total += value;
        }
    }
}
