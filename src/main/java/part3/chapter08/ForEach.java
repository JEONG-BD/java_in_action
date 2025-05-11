package part3.chapter08;

import java.util.Map;
import java.util.Set;

public class ForEach {
    public static void main(String[] args) {
        Map<String, Integer> ageOfFriends = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26));

        mapEntryForEach(ageOfFriends);

        BiConsumerForEach(ageOfFriends);


    }

    public static void BiConsumerForEach(Map<String, Integer> ageOfFriends) {
        ageOfFriends.forEach((friendName, age) -> System.out.println(friendName + " is" + " age " + age ));
    }

    public static void mapEntryForEach(Map<String, Integer> ageOfFriends) {
        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend  + " is " + "age " + age +" years old");
        }
    }
}
