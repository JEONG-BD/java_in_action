package part5.chapter16;

import java.util.Random;

public class ShopV1 {

    private final String name;
    private final Random random;

    public ShopV1(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    // 가격을 반환한다.
    public double getPrices(String product){
        return calculatePrice(product);
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay(){
        try {
            Thread.sleep(1000L);
        }catch (InterruptedException ie){
            throw new RuntimeException(ie);
        }
    }


}
