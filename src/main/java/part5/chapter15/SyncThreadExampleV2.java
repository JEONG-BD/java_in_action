package part5.chapter15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SyncThreadExampleV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        int x = 1337;

        Future<Integer> y = es.submit(() -> f(x));
        Future<Integer> z = es.submit(() -> g(x));

        System.out.println(y.get() + z.get());

        es.shutdown();
    }
    private static class Result {
        private int left;
        private int right;
    }

    private static int f(int x) {
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return x * 2;
    }

    private static int g(int x) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return x + 10;
    }
}
