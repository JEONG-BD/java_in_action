package part5.chapter16;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopPriceV1 {
    public final List<ShopV1> shops = Arrays.asList(
            new ShopV1("BestPrice"),
            new ShopV1("JPASaveBig"),
            new ShopV1("JavaShopPrice"),
            new ShopV1("RabbitMqShopPrice"));

    public final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public  List<String> findPrice(String prodduct){
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(),
                        shop.getPrices(prodduct)))
                .collect(Collectors.toList());
    }



    public List<String> findPricesDiscountSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPriceWithDiscount(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }



    public  List<String> findPriceParallel(String prodduct){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(),
                        shop.getPrices(prodduct)))
                .collect(Collectors.toList());
    }



    public  List<String> findPriceCompletableFutureExecutor(String prodduct){

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(),
                                shop.getPrices(prodduct), executor))
                )
                .collect(Collectors.toList());


        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;

    }
}
