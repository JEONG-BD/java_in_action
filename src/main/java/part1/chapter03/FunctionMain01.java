package part1.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FunctionMain01 {
    public static void main(String[] args) {
        List<String> listOfString = List.of("Kim", "Jeong", "Park", "Lee");
        
        
        List<String> filterResult = filter(listOfString, s -> s.length() > 3);
        System.out.println(filterResult);
        foreach(listOfString, (s)-> System.out.println(s.length()));
        
        List<String> mapResult = map(listOfString, (s) -> s.toUpperCase());
        System.out.println(mapResult);
    }

    @FunctionalInterface
    static interface Predicate<T> {
        boolean test(T t);
    }

    @FunctionalInterface
    static interface  Comsumer<T> {
        void accept(T t);
    }

    @FunctionalInterface 
    static interface Function<T, R> {
        R apply(T t);
        
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)){
                System.out.println(t);
                results.add(t);
            }
        }
        return results;
    }

    public static <T> void  foreach(List<T> list, Consumer<T> c){
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
