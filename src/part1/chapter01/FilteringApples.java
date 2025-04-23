package part1.chapter01;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static part1.chapter01.Color.*;
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple>inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));        

            List<Apple> greenResult = filterGreemApples(inventory);
            for (Apple apple : greenResult) {
                System.out.println("apple = " + apple);
            }
            System.out.println("=====");
            List<Apple> redResult = filterRedApples(inventory);
            for (Apple apple : redResult) {
                System.out.println("apple = " + apple);
            }

        }

    public static List<Apple> filterGreemApples(List<Apple> invertory){
        
        List<Apple> result = new ArrayList<>();
        for (Apple apple : invertory) {
            if(GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterRedApples(List<Apple> invertory){
        
        List<Apple> result = new ArrayList<>();
        for (Apple apple : invertory) {
            if(RED.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
}
