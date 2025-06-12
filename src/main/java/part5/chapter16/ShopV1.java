package part5.chapter16;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ShopV1 {

    private final String name;
    private final Random random;

    public ShopV1(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    // 가격을 반환한다.
    public double getPrices(String product){
        System.out.println("1. [getPrices method]");

        return calculatePrice(product);
    }

    public Future<Double> getAsyncPrices(String product){
        System.out.println("1. [getPrices method]");
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public double calculatePrice(String product) {
        System.out.println("2. [calculatePrice method]");
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay(){
        try {
            System.out.println("3. [delay method Start]");
            Thread.sleep(8000L);
            System.out.println("4. [delay method End]");
        }catch (InterruptedException ie){
            throw new RuntimeException(ie);
        }
    }


}
