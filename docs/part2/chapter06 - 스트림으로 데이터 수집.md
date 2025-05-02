# Chapter 6 스트림으로 데이터 수집     
## 목차
[6.1 컬렌터란 무엇인가?](#61-컬렌터란-무엇인가)  

* [6.1.1 고급 리듀싱 기능을 수행하는 컬렉터](#611-고급-리듀싱-기능을-수행하는-컬렉터)
* [6.1.2 미리 정의된 컬렉터](#612-미리-정의된-컬렉터)

[6.2 리듀싱과 요약](#62-리듀싱과-요약)  
* [6.2.1 스트림에서 최댓값과 최솟값 검색](#621-스트림에서-최댓값과-최솟값-검색)
* [6.2.2 요약 연산](#622-요약-연산)
* [6.2.3 문자열-연결](#623-문자열-연결-)
* [6.2.4 범용 리듀싱 요약 연산](#624-범용-리듀싱-요약-연산) 

[6.3 그룹화](#63-그룹화)  
* [6.3.1 그룹화된 요소 조작](#631-그룹화된-요소-조작)
* [6.3.2 다수준으로 그룹화](#632-다수준-그룹화)

[6.4 분할](#64-분할)  
* [6.4.1 분할의 장점](#641-분할의-장점) 

[6.5 Collector 인터페이스](#65-collector-인터페이스)
* [6.5.1 Collector 메서드 살펴보기](#651-collector-인터페이스의-메서드-살펴보기)
* [6.5.2 응용하기](#652-응용하기)

***
## 6.1 컬렉터란 무엇인가?  
- 함수형 프로그래밍에서는 무엇을 원하는지 직접 명시할 수 있어서 어떤 방법으로 이를 얻을지는 신경 쓸 필요가 없다. 
### 6.1.1 고급 리듀싱 기능을 수행하는 컬렉터 
- 함수형 API는 높은 수준의 조합성, 재사용성이 장점이다. 
- collect로 결과를 수집하는 과정을 간단하면서 유연한 방식으로 정의할 수 있다. 
### 6.1.2 미리 정의된 컬렉터 
- Collectors에서 제공하는 메서드의 기능은 크게 세가지로 구분된다. 
- 스트림 요소를 하나의 값으로 리듀스 하고 요약 
- 요소 그룹화 
- 요소 분할 
***
## 6.2 리듀싱과 요약
- 컬렉터로 스트림의 항목을 컬렉션으로 재구성할 수 있다.
```java
long howManyDishhmenu1 = menu.stream().collect(Collectors.counting());
long howManyDishhmenu2 = menu.stream().count();
```
### 6.2.1 스트림에서 최댓값과 최솟값 검색 
```java
Optional<Dish> mostCalorieDish = menu.stream()
        .collect(maxBy(dishComparator));
```
- 스트림에 있는 객체의 숫자 필드의 합계, 평균등을 반환하는 연산에도 리듀싱 기능이 자주 사용된다. 
- 이러한 연산을 요약 연산이라고 부른다. 

### 6.2.2 요약 연산 
- Collectors 클래스는 Collectors.summingInt라는 요약 팩토리 메서드를 제공한다. 
- summintInt는 객체를 int로 매핑하는 함수를 인수로 받는다. 
- summingLong, summingDouble는 같은 방식으로 동작하며 리턴 형식만 다를 뿐이다. 
- 스트림의 요소 수, 최댓값, 최솟값, 합계, 평균을 이용할 때는 summarizingInt를 사용할 수 있다. 
```java
IntSummaryStatistics menuStatistics = menu.stream()
        .collect(summarizingInt(Dish::getCalories));

```
### 6.2.3 문자열 연결 
- joining 팩토리 메소드를 이용하면 스트림 각 객체에서 toString 메서드를 호출해서 추출 문자열을 하나의 문자열로 반환 가능하다. 
```java
String joiningName = menu.stream()
        .map(Dish::getName).collect(joining());
```
- joining 메서드는 내부적으로 StringBuilder를 이용해서 문자열을 하나로 만든다. 
### 6.2.4 범용 리듀싱 요약 연산 
- 위의 메서드들을 Collectors.reducing으로도 구현할 수 있다. 
```java
Integer reducingTotal = menu.stream()
        .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
```
- 첫번째 인수는 리듀싱 연산의 시작값이거나 스트임에 인수가 없을 때는 반환값이다. 
- 두번째 인수는 요리를 칼로리 정수로 변환할때 사용한 변환 함수이다. 
- 세번째 인수는 같은 종류의 항목을 하나의 값으로 더하는 BinaryOperator다. 
#### collect 와 reduce 
- reduce 메서드를 잘못 사용하면 실용성 문제도 발생한다. 
```java

List<Integer> result = IntStream.range(0, 1000).parallel().boxed().reduce(
        new ArrayList<>(),
        (list, i) -> {
            list.add(i);
            return list;
        },
        (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        }
);
System.out.println(result.size());
```
- 여러 스레드가 동시에 같은 데이터 구조체를 고치면 리스트 자체가 망가지므로 리듀싱 연산을 병렬로 수행할 수 없다. 
- <U>가변 컨테이너 관련 작업이면서 병렬성을 확보하려면 collect메서드로 리듀싱 연산을 구현하는 것이 바람직하다</U>. 
#### 컬렉션 프레임워크 유연성 : 같은 연산도 다양한 방식으로 수행할 수 있다. 
```java
Integer total = menu.stream()
            .collect(reducing(0, Dish::getCalories, Integer::sum));
int total = menu.stream().mapToInt(Dish::getCalories).sum();
```
***
## 6.3 그룹화
- 명령형으로 그룹화를 구현하는 것 보다 자바 8의 함수형을 이용하면 가독성 있는 한줄의 코드로 그룹화 구현 가능하다. 
```java
Map<Type, List<Dish>> dishesByType = menu.stream()
        .collect(groupingBy(Dish::getType));
```
- groupingBy 메서드를 이용해서 스트림이 그룹화 되는데 이를 분류함수라고 한다. 
- 그룹화의 연산 결과로 그룹화가 반환되는 키와 각 키에 대응하는 스트림의 항목 리스트를 값으로 갖는 맵이 반환된다.
```java
menu.stream()
        .collect(
            groupingBy(dish -> {
                if (dish.getCalories() < 400)
                    return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700)
                    return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
            }));
```
```java
Map<Type, List<String>> caloricDishesType = menu.stream()
        .filter(dish -> dish.getCalories() > 500)
        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
```
- filtering 메서드는 Collectors 클래스의 프레디케이트를 인수로 받는다.
- 프레디케이트로 각 그룹의 요소와 필터링된 요소를 재그룹화 한다. 

```java
Map<Type, List<String>> caloricDishesType2 = menu.stream()
        .collect(groupingBy(Dish::getType,
                filtering(dish -> dish.getCalories() > 500,
                        mapping(Dish::getName, toList())
                )
        ));
```
### 6.3.1 그룹화된 요소 조작 
```java
Map<Type, List<String>> caloricDishesType2 = menu.stream()
            .collect(groupingBy(Dish::getType,
                    filtering(dish -> dish.getCalories() > 500,
                            mapping(Dish::getName, toList())
                    )
            ));
```
### 6.3.2 다수준 그룹화 
- 두 인수를 받는 Collectors.groupingBy를 이용해서 항목을 다수준으로 그룹화할 수 있다. 
```java
menu.stream()
    .collect(
        groupingBy(Dish::getType,
                groupingBy(dish -> {
                        if (dish.getCalories() <= 400)
                            return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700)
                            return CaloricLevel.NORMAL;
                        else 
                            return CaloricLevel.FAT;
                })));
```
- n수준 그룹화의 결과는 n 수준 트리 구조로 표현되는 n 수준 맵이 된다. 

### 6.3.3 서브 그룹으로 데이터 수집 
- groupingBy로 넘겨주는 컬렉터의 형식은 제한이 없다.
#### 컬렉터 결과를 다른 형식에 적용하기 
- 그룹화 연산에서 맵의 모든 값을 Optional로 감쌀 필요가 없으므로 Optional을 삭제할 수 있다. 
- Collectors.collectingAndThen으로 컬렉터가 반환한 결과를 다른 형식으로 활용이 가능하다. 
```java
Map<Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)
                ));
```
## 6.4 분할
- 분할 함수라 불리는 프레디케이트를 분류 함수로 사용하는 특수한 그룹화 기능이다. 
- 분할 함수는 불리언을 반환하므로 맵의 키 형식은 Boolean이다. 
- 그룹화 맵은 최대 두 개의 그룹으로 분류된다. 
```java
Map<Boolean, List<String>> partitionMenu = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian,
                Collectors.mapping(Dish::getName, Collectors.toList())));

List<String> vegetarianDishes = menu.stream()
        .filter(Dish::isVegetarian)
        .map(Dish::getName)
        .collect(toList());
```
### 6.4.1 분할의 장점 
- 분할 함수가 반환하는 참 거짓 두가지 요소의 스트림 리스트를 모두 유지한다는 것이 분할의 장점이다. 
***
## 6.5 Collector 인터페이스
- Collector 인터페이스는 리듀싱 연산을 어떻게 구현할지 제공하는 메서드 집합으로 구성된다. 
### 6.5.1 Collector 인터페이스의 메서드 살펴보기 
#### supplier 메서드 : 새로운 결과 컨테이너 만들기 
- supplier 메서드는 빈결과로 이루어진 Supplier를 반환한다. 
#### accumulator : 결과 컨테이너에 요소 추가하기 
- accmulator 메서드는 리듀싱 연산을 수행하는 함수를 반환한다. 
- 스트림에서 n번째 요소를 탐색할 때 누적자와 n번째 요소를 함수에 적용한다. 
#### finisher 메서드 : 최종 변환값을 결과 컨테이너로 적용하기 
- finisher 메서드는 스트림 탐색을 끝내고 누적자 객체를 최종 결과를 반환하면서 누적 과정을 끝낼 때 호출함 함수를 반환해야 한다. 
#### combiner 메서드 : 두 결과를 컨테이너 병합 
- combiner는 스트림의 서로 다른 서브파트를 병렬로 처리할 때 누적자가 이 결과를 어떻게 처리할지 정의한다. 
#### Characteristics 메서드 
- Characteristics 메서드는 컬렉터의 연산을 정의 하는 Characteristics 형식의 불변 집합을 반환한다. 
- Characteristics는 스트림을 병렬로 리듀스 할 것인지, 병렬로 리듀스 한다면 어떤 최적화를 선택해야 할지 힌트를 제공한다.
- 
  | 플래그               | 설명                                                                                                                                                         |
  |-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | `UNORDERED`       | 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않는다                                                                                                                   |
  | `CONCURRENT`      | 다중 스레드에서 accumulator 함수를 동시에 호출 가능하다<br/> 이 컬렉터는 스트림의 병렬 리듀싱을 수행할 수 있다. <br/>단, `UNORDERED`가 함께 설정되지 않았다면 데이터 소스가 정렬되지 않은 경우(예: 집합처럼 순서가 없는 경우)에만 병렬 리듀싱이 가능하다. |
  | `IDENTITY_FINISH` | `finisher` 메서드가 단순히 identity 함수만 적용한다면 이를 생략할 수 있다. <br/>따라서 리듀싱 결과로 누적자 객체를 바로 사용할 수 있으며, 누적자 A를 결과 R로 안전하게 형변환할 수 있다.                                         |
### 6.5.2 응용하기 
```java
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {


    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of((Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
```
#### 컬렉션 구현을 만드지 않고도 커스텀 수집 수행하기 
```java
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
```

***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

