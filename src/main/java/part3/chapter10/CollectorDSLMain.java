package part3.chapter10;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

public class CollectorDSLMain {
    public static void main(String[] args) {

        List<Car> cars = Arrays.asList(new Car(Color.GREEN, "brand1"),
                new Car(Color.RED, "brand1"),
                new Car(Color.YELLOW, "brand2"),
                new Car(Color.RED, "brand1"),
                new Car(Color.RED, "brand2"));

        Map<String, Map<Color, List<Car>>> collect = cars.stream()
                .collect(groupingBy(Car::getBrand,
                groupingBy(Car::getColor)));

        collect.forEach((brand, colorMap) -> System.out.println(brand + " => " + colorMap));

    }
}
