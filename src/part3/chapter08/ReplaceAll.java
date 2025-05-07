package part3.chapter08;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReplaceAll {
    public static void main(String[] args) {
        List<String> referenceCodes = Stream.of("a12", "a13", "b13").collect(Collectors.toList());


        List<String> uppercaseNewList = toUppercaseNewList(referenceCodes);
        uppercaseNewList.forEach(System.out::println);
        System.out.println(referenceCodes);
        //for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext();){
        //    String code = iterator.next();
        //    iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        //}
        //referenceCodes.forEach(System.out::println);

        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        referenceCodes.forEach(System.out::println);

    }

    private static List<String> toUppercaseNewList(List<String> referenceCodes) {
        return referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .collect(Collectors.toList());
    }


}
