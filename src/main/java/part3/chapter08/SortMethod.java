package part3.chapter08;

import java.util.*;

public class SortMethod {
    public static void main(String[] args) {
        Map<String, String> favoritesMovie = Map.ofEntries(
                Map.entry("Raphael", "Star wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));

        comparingByKey(favoritesMovie);
        getOrDefault(favoritesMovie);
        notComputeIfAbsent();
        computeIfAbsent();
        remove();
        replaceAll();
        putAll();
        putAllMerge();
        merge();
    }

    private static void merge() {
        Map<String, Long> moviesToCount = new HashMap<>();
        String movieName = "JamesBond";
        Long count = moviesToCount.get(movieName);
        if (count == null){
            moviesToCount.put(movieName, 1L);
        } else {
          moviesToCount.put(movieName, count + 1L);
        }

        moviesToCount.merge(movieName, 1L, (key, cnt) -> cnt + 1 );

    }

    private static void putAll() {

        Map<String, String> family = Map.ofEntries(
                Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond"),
                Map.entry("Raphael", "Star Wars"));

        Map<String, String> friends = new HashMap<>(family);
        friends.putAll(family);
        System.out.println(friends);
    }

    private static void putAllMerge() {

        Map<String, String> family = Map.ofEntries(
                Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond"),
                Map.entry("Raphael", "Star Wars"));

        Map<String, String> friends = Map.ofEntries(
                Map.entry("Kim", "Star Wars"),
                Map.entry("Lee", "James Bond"),
                Map.entry("Cristina", "Star Wars"));

        Map<String, String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + "&" + movie2));
        System.out.println(everyone);
    }
     private static void replaceAll() {
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Jack Reacher 2");
        favouriteMovies.put("Cristina", "Matrix");
        favouriteMovies.put("Olivia", "James Bond");
        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies);
    }

    private static void remove() {
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Jack Reacher 2");
        favouriteMovies.put("Cristina", "Matrix");
        favouriteMovies.put("Olivia", "James Bond");
        String key = "Raphael";
        String value = "Jack Reacher 2";

        System.out.println("--> Removing an unwanted entry the cumbersome way");
        boolean result = remove(favouriteMovies, key, value);
        System.out.printf("%s [%b]%n", favouriteMovies, result);

        favouriteMovies.put("Raphael", "Jack Reacher 2");

        System.out.println("--> Removing an unwanted the easy way");
        favouriteMovies.remove(key, value);
        System.out.printf("%s [%b]%n", favouriteMovies, result);
    }

    private static <K, V> boolean remove(Map<K, V> favouriteMovies, K key, V value) {
        if (favouriteMovies.containsKey(key) && Objects.equals(favouriteMovies.get(key), value)) {
            favouriteMovies.remove(key);
            return true;
        }
        return false;
    }
    private static void notComputeIfAbsent() {
        Map<String, List<String>> friendMovies = new HashMap<>();
        String friend = "Raphael";
        List<String> movie = friendMovies.get(friend);
        if (movie == null){
            movie = new ArrayList<>();
            friendMovies.put(friend, movie);
        }
        movie.add("Star wars");
        System.out.println("movie = " + movie);
    }

    private static void computeIfAbsent() {
        Map<String, List<String>> friendMovies = new HashMap<>();
        String friend = "Raphael";
        List<String> movie = friendMovies.get(friend);
        friendMovies.computeIfAbsent(friend, name -> new ArrayList<>())
                        .add("Star wars");
        System.out.println("movie = " + friendMovies);
    }

    private static void getOrDefault(Map<String, String> favoritesMovie) {
        System.out.println(favoritesMovie.getOrDefault("Olivia", "Matrix"));
        System.out.println(favoritesMovie.getOrDefault("Default", "Matrix"));
    }

    private static void comparingByKey(Map<String, String> favoritesMovie) {
        favoritesMovie.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }


}
