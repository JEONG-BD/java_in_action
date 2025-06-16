package part5.chapter16;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ShopMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ShopV1 shopV1 = new ShopV1("Test");
        long startTime = System.currentTimeMillis();
        //double resultValue = shopV1.getPrices("test");
        //Future<Double> result = shopV1.getAsyncPrices("test");
        Future<Double> result = shopV1.getAsyncPricesCompleteExceptionally("test");

        doSomethingElse();
        try {
            Double resultValue = result.get();
            System.out.printf("Price us % .2fn", resultValue);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();

        //System.out.printf("Price us % .2fn", resultValue);

    }

    public static void doSomethingElse() {
        System.out.println("5. [doSomethingElse method]");
    }
}
