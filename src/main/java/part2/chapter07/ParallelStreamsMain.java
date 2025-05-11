package part2.chapter07;

import org.openjdk.jmh.annotations.BenchmarkMode;

import java.util.stream.Stream;

public class ParallelStreamsMain {


    public static void main(String[] args) {
        long sum = sequentialSum(100);
        System.out.println("sum = " + sum);
        long parallelSumsum = sequentialParallelSum(100);
        System.out.println("parallelSumsum = " + parallelSumsum);
    }

    public static long sequentialSum(long n){
        long start = System.currentTimeMillis();
        long sum = Stream.iterate(1L, i-> i +1)
                .limit(n)
                //.peek(i -> System.out.println("Thread: " + Thread.currentThread().getName() + " processing: " + i))
                .reduce(0L, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (end - start) + " ms");
        return sum;
    }
    public static long sequentialParallelSum(long n){
        // return Stream.iterate(1L, i-> i +1)
        //         .limit(n)
        //         .parallel()
        //         .peek(i -> System.out.println("Thread: " + Thread.currentThread().getName() + " processing: " + i))
        //         .reduce(0L, Long::sum);
        long start = System.currentTimeMillis();

        long sum = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                //..peek(i -> System.out.println("Thread: " + Thread.currentThread().getName() + " processing: " + i))
                .reduce(0L, Long::sum);

        long end = System.currentTimeMillis();
    
        System.out.println("Elapsed Time: " + (end - start) + " ms");
        return sum;
    }

    public static long sequentialSumOld(long n){
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }
}
