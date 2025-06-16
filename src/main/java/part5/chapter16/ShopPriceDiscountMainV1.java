package part5.chapter16;

import java.util.List;
import java.util.function.Supplier;

public class ShopPriceDiscountMainV1 {
    private static ShopPriceDiscount discountPrice = new ShopPriceDiscount();

    public static void main(String[] args) {
        execute("completable" , () -> discountPrice.findPriceCompletableFuture("BestPrice"));
        execute("original discount" , () -> discountPrice.findPricesDiscountSequential("BestPrice"));
        execute("completable discount" , () -> discountPrice.findPricesDiscountSequential("BestPrice"));

    }

    private static void execute(String msg, Supplier<List<String>> list){
        long start = System.nanoTime();
        System.out.println(list.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
