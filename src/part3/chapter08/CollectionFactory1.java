package part3.chapter08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionFactory1 {
    public static void main(String[] args) {
        List<String> byConstructorList = createByConstructor();
        List<String> listAsList = createListAsList();
        //listAsList.add("Test");

        createSetByAsList();
        createMapOf();
        createMapOfEntry();
        
        // removeIf 메서드 


    }

    private static Map<String, Integer>  createMapOf() {
        return Map.of("Raphael", 30,
                        "Olivia", 25,
                        "Thibaut", 26);
    }

    private static Map<String, Integer>  createMapOfEntry() {
        return Map.ofEntries(Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26));
    }

    private static Set<String> createSetByAsList() {
        return new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
    }

    private static Set<String> createSetByStream() {
        return Stream.of("Raphael", "Olivia", "Thibaut").collect(Collectors.toSet());
    }

    private static List<String> createByConstructor() {
        List<String> friends = new ArrayList<>();
        friends.add("Raphael");
        friends.add("Olivia");
        friends.add("Thibaut");
        return friends;
    }

    private static List<String> createListAsList() {
        return Arrays.asList("Raphael", "Olivia", "Thibaut");
    }
}
