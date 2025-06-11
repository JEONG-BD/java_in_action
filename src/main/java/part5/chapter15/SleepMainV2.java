package part5.chapter15;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SleepMainV2 {
    public static void main(String[] args) throws InterruptedException {

        long startTime1 = System.currentTimeMillis();
        work1();
        Thread.sleep(10000);
        work2();
        long endTime1 = System.currentTimeMillis();
        System.out.println("시간: (" + (endTime1 - startTime1) + "ms)");


        long startTime2 = System.currentTimeMillis();
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        work1();

        es.schedule(SleepMainV2::work2, 10, TimeUnit.SECONDS);
        es.shutdown();
        long endTime2 = System.currentTimeMillis();
        System.out.println("시간: (" + (endTime2 - startTime2) + "ms)");
    }

    public static void work1() {
        System.out.println("Hello from work1");
    }

    public static void work2() {
        System.out.println("Hello from work2");
    }
}
