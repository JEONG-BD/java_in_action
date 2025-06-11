package part5.chapter15;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceEx01 {
    public static void main(String[] args) throws InterruptedException {
        nonExecutorSercivce();
        int nThread = 3;
        ExecutorService es = Executors.newFixedThreadPool(3);

        es.submit(() -> System.out.println("작업 1: " + Thread.currentThread().getName()));
        es.submit(() -> System.out.println("작업 2: " + Thread.currentThread().getName()));
        es.submit(() -> System.out.println("작업 3: " + Thread.currentThread().getName()));

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("모든 작업 완료");

    }

    private static void nonExecutorSercivce() throws InterruptedException {
        Runnable t1 = () -> {System.out.println(Thread.currentThread().getName());};
        Runnable t2 = () -> {System.out.println(Thread.currentThread().getName());};
        Runnable t3 = () -> {System.out.println(Thread.currentThread().getName());};

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
