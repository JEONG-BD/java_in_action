package part5.chapter15;

import java.util.concurrent.Future;

public class SyncThreadExampleV1 {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();
        Thread t1 = new Thread(() -> { result.left = f(x); } );
        Thread t2 = new Thread(() -> { result.right = g(x); });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(result.left + result.right);
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
