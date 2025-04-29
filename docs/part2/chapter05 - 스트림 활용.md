# Chapter 5 스트림 활용    
## 목차
[5.1 필터링](#51-필터링)  
[5.2 스트림 슬라이싱](#52-스트림-슬라이싱)  
* [5.2.1 프레디케이트를 이용한 슬라이싱](#521-프레디케이트를-이용한-슬라이싱)
* [5.2.2 스트림 축소](#522-스트림-축소)
* [5.2.3 요소 건너뛰기](#523-요소-건너-뛰기)
[5.3 매핑](#53-메핑) 
* [5.3.1 스트림의 각 요소에 함수 적용하기](#531-스트림의-각-요소에-함수-적용하기)
* [5.3.2 스트림 평면화](#532-스트림-평면화)

[5.4 검색과 매핑](#54-검색과-매핑)  
* [5.4.1 프레디케이트가 적어도 한 요소와 일치하는지 확인](#541-프레디케이트가-적어도-한-요소와-일치하는지-확인)
* [5.4.2 프레디케이트가 모든 요소와 일치하는지 검사](#542-프레디케이트가-모든-요소와-일치하는지-검사)
* [5.4.3 요소 검색](#543-요소-검색)
* [5.4.3 첫번째 요소 찾기](#544-첫번째-요소-찾기)

[5.5 리듀싱](#55-리듀싱)  
* [5.5.1 요소의 합](#551-요소의-합) 
* [5.5.2 최댓값과 최솟값](#552-최댓값과-최솟값) 

[5.7 숫자형 스트림](#57-숫자형-스트림)  
[5.8 스트림 만들기](#58-스트림-만들기)  
[5.9 마치며](#59-마치며)


***
## 5.1 필터링  
### 5.1.1 프레디케이트 필터링 
- 스트림 인터페이스는 filter 메서드를 지원한다. 
- filter 메서드는 프레디케이트를 인수로 받아서 프레티케이트와 일치하는 모든 요소를 포함하는 스트림을 반환한다. 
```java
List<Dish> vegetarianMenu = menu.stream()
                            .filter(Dish::isVegetarian)
                            .collect(toList());

```
### 5.1.2 고유 요소 필터링 
- 스트림은 고유 요소로 이루어진 스트림을 반환하는 distinct 메서드도 지원한다. 
```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
    .filter(i -> i % 2 == 0)
    .distinct()
    .forEach(System.out::println);

```
## 5.2 스트림 슬라이싱
### 5.2.1 프레디케이트를 이용한 슬라이싱 
- 자바 9는 스트림의 요소를 효과적을 선택할 수 있도록 takeWhile, dropWhile 두 가지 새로운 메서드를 지원한다.

|메서드|설명|
|---|---|
|takeWhile|처음부터 조건 true 인 것만 가져오다가 false 만나면 바로 멈춤|
|dropWhile|	처음부터 조건 true 인 것들을 버리고 false 만나면 그 이후 전부 가져옴|
```java
menu.stream()
    .takeWhile(d -> d.getCalories() < 320)
    .forEach(System.out::println);

menu.stream()
    .dropWhile(d -> d.getCalories() < 320)
    .forEach(System.out::println);
```
### 5.2.2 스트림 축소 
- 스트림은 주어진 값 이하의 크기를 갖는 새로운 스트림을 반환하는 limit(n)메서드를 지원한다.
```java
List<Dish> dishes = 
    menu.stream()
        .filter(d -> d.getCalories() > 300)
        .limit(3)
        .collect(toList());
                        
``` 
### 5.2.3 요소 건너 뛰기 
- 스트림은 처음 n개 요소를 제외한 스트림을 반환하는 skip(n) 메서드를 지원한다. 
```java
List<Dish> skipDishes = 
    menu.stream()
        .filter(d -> d.getCalories() > 300)
        .skip(2)
        .collect(toList());
```             
***
## 5.3 매핑  
### 5.3.1 스트림의 각 요소에 함수 적용하기
- 스트림은 함수를 인수로 받는 map 메서드를 지원한다. 인수로 제공된 함수는 각 요소에 적용되며 함수를 적용한 결과가 새로운 요소로 매핑된다. 
- 메서드 체이닝을 지원한다. 
```java
List<String> dishNames = 
    menu.stream()
        .map(d -> d.getName())
        .collect(toList());

List<Integer> dishNameLength = 
    menu.stream()
        .map(d -> d.getName())
        .map(String::length)
        .collect(toList());
```

### 5.3.2 스트림 평면화 
```java
list.stream()
    .map(s -> s.split(""))
    .map(Arrays::stream)
    .distinct()
    .peek(System.out::println)
    .collect(toList());

List<String> result =list.stream()
                        .map(s -> s.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());


```
- flatMap은 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑한다. 
- flaMap 메서드는 스트림의 각 값을 다른 스트림으로 만든 후 모든 스트림을 하나의 스트림으로 연결하는 기능을 수행한다. 
***
## 5.4 검색과 매핑
### 5.4.1 프레디케이트가 적어도 한 요소와 일치하는지 확인 
- 프레디케이트가 주어진 스트림에서 적어도 한 요소와 일치하는지 확인할 때 anyMatch메서드를 이용한다. 
```java
    boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        
    boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
``` 
### 5.4.2 프레디케이트가 모든 요소와 일치하는지 검사 
```java
    boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        
    boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
``` 
### 5.4.3 요소 검색 
- findAny 메서드는 현재 스트림에서 임의의 요소를 반환한다. 다른 스트림 연산과 연결해서 사용할 수 있다 .
#### Optional 
- Optional<T>(java.util.Optional) 클래스는 값의 존재나 부재 여부를 표현하는 컨테이너 클래스다. 
- Optional을 이용해서 null 확인 관련 버그는 10장에서 다룬다.

|메서드|설명|
|---|---|
|`isPresent()`| `Optional`이 값을 포함하면 `true`, 그렇지 않으면 `false`를 반환|
|`ifPresent(Consumer<T>)`| 값이 존재하면 주어진 `Consumer<T>` 블록 실행 |
|`get()`| 값이 존재하면 그 값을 반환, 없으면 `NoSuchElementException` 예외 발생|
|`orElse(T other)`| 값이 있으면 그 값을 반환하고, 없으면 지정한 기본값(`other`)을 반환|
 
### 5.4.4 첫번째 요소 찾기 
- 리스트 또는 정렬된 연속 데이터로부터 생성된 스트림 처럼 일부 스트림에는 논리적인 아이템 순서가 정해져 있을 수 있다. 
```java
    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5); 
    Optional<Integer> firstSquareDivisibleByThree  = 
    someNumbers.stream()
        .map(n -> n * n)
        .filter(n -> n % 3 == 0)
        .findFirst();
```
***
## 5.5 리듀싱
- 같은 스트림의 요소를 조합해서 더 복잡한 질의를수행하기 위해서는 결과가 나올 때 까지 스트림의 모든 요소를 반복적으로 처리 해야 한다. 
- 이러한 질의를 리듀싱 연산(모든 스트림의 요소를 처리해서 값으로 도출)이라고 한다. 
- 함수형 프로그래밍 언어 용어로는 이 과정이 마치 작은 조각이 될 때  까지 접는 것과 비슷하다는 의미로 폴드라고 부른다. 

### 5.5.1 요소의 합 
- 자바 8에서는 Integer 클래스에 두 숫자를 더하는 정적 sum 메서드를 제공한다. 
```java
int sum = numbers.stream().reduce(0, (a, b) -> a +b);
int sum = numbers.stream().reduce(0, Integer::sum);
```
#### 초깃값 없음
- 초깃값을 받지 않도록 오버로드된 reduce도 있으나 이 reduce는 Optional객체를 반환한다. 
```java

Optional<Integer> sum3 = numbers.stream().reduce(Integer::sum);

```
### 5.5.2 최댓값과 최솟값 
```java
Optional<Integer> optMax = numbers.stream().reduce(Integer::max);
System.out.println(optMax.get());
Optional<Integer> optMin = numbers.stream().reduce(Integer::min);
System.out.println(optMin.get());

```
### 5.5.3 
***
## 5.6 실전 연습
***
## 5.7 숫자형 스트림
***
## 5.8 스트림 만들기
***
## 5.9 마치며

***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

