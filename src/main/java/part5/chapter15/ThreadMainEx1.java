package part5.chapter15;


import java.util.Arrays;
import java.util.Random;

public class ThreadMainEx1 {
    public static void main(String[] args) {
        // 1. 100만 개의 랜덤 숫자를 stats 배열에 저장
        long[] stats = new long[1_000_000];
        Random random = new Random();

        for (int i = 0; i < stats.length; i++) {
            stats[i] = random.nextInt(100);
        }


        long startTime1 = System.currentTimeMillis();
        long sumSequential = Arrays.stream(stats).sum();
        long endTime1 = System.currentTimeMillis();

        System.out.println("단일 스레드 합계: " + sumSequential + " (" + (endTime1 - startTime1) + "ms)");

        long startTime2 = System.currentTimeMillis();
        long sumParallel = Arrays.stream(stats).parallel().sum();
        long endTime2 = System.currentTimeMillis();

        System.out.println("병렬 스트림 합계: " + sumParallel + " (" + (endTime2 - startTime2) + "ms)");

        int activeThreads = Thread.activeCount();
        System.out.println("스레드 수: " + activeThreads);
    }
}

