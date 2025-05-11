package part2.chapter06;

import java.util.ArrayList;
import java.util.List;

public class CustomExample {
    public static void main(String[] args) {
        List<MenuItem> menu = List.of(
                new MenuItem("김치찌개", 9000),
                new MenuItem("삼겹살", 12000),
                new MenuItem("된장찌개", 8500),
                new MenuItem("갈비찜", 15000)
        );

        List<MenuItem> expensiveMenu = menu.stream()
                .peek(item -> System.out.println("원본: " + item))
                .filter(item -> item.getPrice() >= 10000)
                .peek(item -> System.out.println("필터 통과: " + item.getName()))
                .collect(
                        ArrayList::new,
                        (list, item) -> {
                            System.out.println("리스트에 추가: " + item.getName());
                            list.add(item);
                        },
                        List::addAll
                );

        System.out.println("최종 결과:");
        expensiveMenu.forEach(System.out::println);
    }

    public static class MenuItem {
        private String name;
        private int price;

        public MenuItem(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
}
