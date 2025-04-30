package part2.chapter05;

import static part1.Color.valueOf;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        
        stream.map(String::toUpperCase).forEach(System.out::println);
    
        String homwValue = System.getProperty("home");
        Stream<String> homeValueStream = homwValue == null? Stream.empty() : Stream.of(homwValue);
        
        Stream<String> homeValue2 = Stream.ofNullable(System.getProperty("home"));
        int[] numbers = {2, 3, 5, 7, 11, 13};

        int sum = Arrays.stream(numbers).sum();

        // try (Stream<String> lines = Files.lines(Paths.get(FILE), Charset.defaultCharset())) {
        //     long uniqueWords = lines
        //         .flatMap(line -> Arrays.stream(line.split(" ")))
        //         .distinct()
        //         .count();
        //     System.out.println("고유한 단어 : " + uniqueWords);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        
    }
}
