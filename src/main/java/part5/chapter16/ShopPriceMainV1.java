package part5.chapter16;

import java.util.List;
import java.util.function.Supplier;

public class ShopPriceMainV1 {
    private static ShopPriceV1 shopPriceMainV1 = new ShopPriceV1();

    public static void main(String[] args) {
        execute("original" , () -> shopPriceMainV1.findPrice("BestPrice"));
        execute("parallel" , () -> shopPriceMainV1.findPriceParallel("BestPrice"));
        execute("completable executor" , () -> shopPriceMainV1.findPriceCompletableFutureExecutor("BestPrice"));

    }

    private static void execute(String msg, Supplier<List<String>> list){
        long start = System.nanoTime();
        System.out.println(list.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");

    }
}
