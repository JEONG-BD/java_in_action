package part5.chapter16;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static part5.chapter16.Util.delay;

public class ShopV1 {

    private final String name;
    private final Random random;

    public String getName() {
        return name;
    }

    public Random getRandom() {
        return random;
    }

    public ShopV1(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    // 가격을 반환한다.
    public double getPrices(String product){
        //System.out.println("1. [getPrices method]");
        return calculatePrice(product);
    }



    public String getPriceWithDiscount(String product) {
        double price = calculatePrice(product); Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public Future<Double> getAsyncPrices(String product){
        //System.out.println("1. [getPrices method]");
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public Future<Double> getAsyncPricesCompleteExceptionally(String product){
        //System.out.println("1. [getPrices method]");
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getAsyncPricesCompleteV2(String product){
        //System.out.println("1. [getPrices method]");
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double calculatePrice(String product) {
        //System.out.println("2. [calculatePrice method]");
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    //public static void delay(){
    //    try {
    //        //System.out.println("3. [delay method Start]");
    //        Thread.sleep(1000L);
    //        //System.out.println("4. [delay method End]");
    //    }catch (InterruptedException ie){
    //        throw new RuntimeException(ie);
    //    }
    //}


}
