package part4.chapter12;

import java.time.Instant;

public class InstantMainV1 {
    public static void main(String[] args) {
        instantCreateOfEpochSecond();
    }

    private static void instantCreateOfEpochSecond(){
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);

        System.out.println("instant = " + instant);

    }
}
