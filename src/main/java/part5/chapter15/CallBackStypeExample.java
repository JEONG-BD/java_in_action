package part5.chapter15;

import java.util.function.IntConsumer;

public class CallBackStypeExample {
    public static void main(String[] args) {
        int x = 1337;
        Result result = new Result();

        f(x, (int y) -> {
            result.left = y;
            System.out.println(result.left + result.right);
        });

        System.out.println(result.left + result.right);

        g(x, (int z) -> {
            result.right = z;
            System.out.println(result.left + result.right);
        });

        System.out.println(result.left + result.right);
    }


    private static class Result {
        private int left;
        private int right;
    }

    private static void f(int x, IntConsumer dealWithResult) {
        int result = x * 2;
        dealWithResult.accept(result);
    }

    private static void g(int x, IntConsumer dealWithResult) {
        int result = x + 10;
        dealWithResult.accept(result);
    }
}
