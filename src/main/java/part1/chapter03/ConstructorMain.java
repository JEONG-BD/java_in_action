package part1.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import part1.Color;
import part1.Apple;
public class ConstructorMain {
    public static void main(String[] args) {
        Supplier<Apple> c1 = new Supplier<Apple>() {
            @Override
            public Apple get() {
                return new Apple(10, Color.GREEN);  
            }

        };
        Apple apple = c1.get();
        System.out.println(apple); }
        
        //Supplier<Apple> c1 =() -> new Apple();
        Supplier<Apple> c2 = Apple::new;

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        //List<Apple> apples = map(weights, Apple::new);
        
        // @FunctionalInterface
        // static interface Supplier<T> {
        //     T get();
        // }

        // @FunctionalInterface
        // static interface Function<T, R> {
        //     R apply(T t);
        
    
    static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}

//     public static class Apple {
//         private Integer weight = 0; 
//         private Color color;
        
//         public Apple() {
//         }

//         public Apple(int weight, Color color){
//             this.weight = weight;
//             this.color = color;
//         }

//         public Color getColor(){
//             return this.color; 
//         }


//         public Integer getWeight() {
//             return weight;
//         }

//         public void setWeight(int weight) {
//             this.weight = weight;
//         }

//         public void setColor(Color color) {
//             this.color = color;
//         }


//         @Override
//         public String toString() {
//             return "Apple [weight=" + weight + ", color=" + color + "]";
//         }


//     }
// }

    

    

    
    



