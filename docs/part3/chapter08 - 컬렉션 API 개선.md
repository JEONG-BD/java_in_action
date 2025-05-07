# Chapter 8 컬렉션 API 개선      
## 목차
[8.1 컬렉션 팩토리](#81-컬렉션-팩토리)  
* [8.1.1 리스트 팩토리](#811-리스트-팩토리)
* [8.1.2 집합 팩토리](#812-집합-팩토리)
* [8.1.3 맵 팩토리](#813-맵-팩토리)

[8.2 리스트와 집합 처리](#82-리스트와-집합-처리)   

* [8.2.1 removeIf 메서드](#821-removeif-메서드)  
* [8.2.2 replaceAll 메서드](#822-replaceall-메서드)

[8.3 맵 처리](#83-맵-처리)
* [8.3.1 forEach 메서드](#831-foreach-메서드)
* [8.3.2 정렬 메서드](#832-정렬-메서드)
* [8.3.3 getOrDefault 메서드 ](#833-getordefault-메서드)
* [8.3.4 계산 패턴](#834-계산-패턴)
* [8.3.5 삭제 패턴 ](#835-삭제-패턴)
* [8.3.6 교체 패턴](#836-교체-패턴)
* [8.3.7 합침](#837-합침)

[8.4 개선된 ConcurrentHashMap](#84-개선된-concurrenthashmap)
* [8.4.1 리듀스와 검색](#841-리듀스와-검색)
* [8.4.2 계수](#842-계수)
* [8.4.3.집합뷰](#843-집합뷰)
***
## 8.1 컬렉션 팩토리
- 자바 9에서는 작은 컬렉션 객체를 쉽게 만들 수 있는 몇가지 방법을 제공한다. 
```java
List<String> friends = new ArrayList<>();
friends.add("Raphael");
friends.add("Olivia");
friends.add("Thibaut");

List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
friends.add("Test");
```
#### UnsupportedOperationException 발생
- 내부적으로 고정된 크기의 변환할 수 있는 배열로 구현되었기 때문에 이와 같은 일이 발생한다/ 
```java
List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
friends.add("Test"); // UnsupportedOperationException
```

### 8.1.1 리스트 팩토리 
- List.of 팩토리 메서드를 이용해서 간단하게 리스트를 구현 가능하댜ㅏㅑ. 
- 변경할 수 없는 리스트가 만들어지는데 이는 컬렉션이 의도치 않게 변하는 것을 막을 수 있다. 
### 8.1.2 집합 팩토리 
```java
new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
Stream.of("Raphael", "Olivia", "Thibaut").collect(Collectors.toSet());
```
- List.of와 비슷한 방법으로 바꿀 수 없는 집합을 만들 수 있다. 

### 8.1.3 맵 팩토리 
- 자바 9에서는 두가지 방법으로 바꿀 수 없는 맵을 초기화 할 수 있다. 
```java
Map.of("Raphael", 30,
        "Olivia", 25,
        "Thibaut", 26);

Map.ofEntries(Map.entry("Raphael", 30),
        Map.entry("Olivia", 25),
        Map.entry("Thibaut", 26));
```
***
## 8.2 리스트와 집합 처리
- 자바 8에서는 List, Set 인터페이스에 아래와 같은 메서드를 추가했다. 
- removeIf 메서드는 프레디케이트를 만족하는 요소를 제거한다. 
- replaceAll 메서드는 리스트에서 사용할 수 있는 기능으로 UarayOperator 함수를 이용해 요소를 바꾼다. 

### 8.2.1 removeIf 메서드 
```java
//error 
for (RemoveIf transaction : transactions) {
        if(Character.isDigit(transaction.getReferenceCode().charAt(0))){
            transactions.remove(transaction);
        }
    }

Iterator<RemoveIf> iterator = transactions.iterator();
while (iterator.hasNext()) {
    RemoveIf t = iterator.next();
    if (Character.isDigit(t.getReferenceCode().charAt(0))) {
        iterator.remove();
        }
    }
```
### 8.2.2 replaceAll 메서드 
```java
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
```
***
## 8.3 맵 처리 
- 자바 8에서는 Map 인터페이스에 몇까지 디폴트 메서드를 추가했다. 
### 8.3.1 forEach 메서드 
- 자바 8 Map 인터페이스는 BiConsumer 를 인수로 받는 메서드를 지원하므로 코드를 간결하게 구현 가능하다. 
```java
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
```
### 8.3.2 정렬 메서드 
- Entry.comparingByValue, Entry.comparingByKey를 이용하면 맵의 항목을 값 또는 키를 기준으로 정렬 가능하다. 
```java
 favoritesMovie.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEachOrdered(System.out::println);
```
### 8.3.3 getOrDefault 메서드 
- 기존에는 찾으려는 키가 존재하지 않으면 null이 반환되므로 NullPointerException을 방지하려면 요청결과가 null인지 확인해야 했다. 
- getOrDefault메서드를 이용하면 쉽게 문제 해결이 가능하다.  

### 8.3.4 계산 패턴 
- 맵에 키가 존재하는지 여부에 따라서 어떤 동작을 실행하고 결과를 저장해야 하는 상황에서는 아래 세가지 연산을 사용한다. 
- computeIfAbsent : 제공된 키에 해당하는 값이 없으면 키를 이용해 새 값을 계산하고 맵에 추가한다. 
- computeIfPresent : 제공된 키가 존재하면 새값을 계산하고 맵에 추가한다. 
- compute : 제공된 키로 새 값을 계산하고 맵에 저장한다.  
```java
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

```
### 8.3.5 삭제 패턴
- 자바 8에서는 키가 특정한 값과 연관되었을 때만 항목을 제거하는 오버로드 버전의 메서드를 제공한다. 
```java
favouriteMovies.remove(key, value);
```
### 8.3.6 교체 패턴 
- replaceAll : BiFunction을 적용한 결과로 각 항목의 값을 교체한다. 
- Replace : 키가 존재하면 맵의 값을 바꾼다. 키가 특정 값으로 매핑되었을 때만 값을 교체하는 오버로드 버전도 있다.
```java
 Map<String, String> favouriteMovies = new HashMap<>();
    favouriteMovies.put("Raphael", "Jack Reacher 2");
    favouriteMovies.put("Cristina", "Matrix");
    favouriteMovies.put("Olivia", "James Bond");
    favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
    System.out.println(favouriteMovies);
```
### 8.3.7 합침 
- 중복된 키가 없다면 putAll을 사용하여 합칠 수 있다. 
```java
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
```
***
## 8.4 개선된 ConcurrentHashMap
- ConcurrentHashMap 클래스는 동시성 친화적이면서도 최신 기술을 반영한 HashMap이다. 

### 8.4.1 리듀스와 검색 
- ConcurrentHashMap은 스트림에서 세가지 새로운 연산을 지원한다. 
- forEach : 각 쌍에 주어진 액션을 실행 
- reduce : 모든 쌍을 제공된 리듀스 함수를 이용해서 합침 
- search : 널이 아닌 값을 반환할 때 까지 각 쌍에 함수 적용 
- 이들 연산은 ConcurrentHashMap의 상태를 잠그지 않고 연산을 수행한다. 
- 이들 연산은 병렬성 기준값을 지정해야 한다. 맵의 크기가 주어진 기준값보다 작으면 순차적으로 연산을 실행한다. 
- 기준값을 1로 지정하면 공통 스레드풀을 이용해서 병렬성을 극대화한다.

### 8.4.2 계수 
- ConcurrentHashMap 클래스는 맵의 매핑 개수를 반환하는 mappingCount 메서드를 제공한다. 
- 기존 size 메서드 대신에 int를 반환하는 mappingCount 메서드를 사용하는 것이 좋다. 

### 8.4.3 집합뷰 
- ConcurrentHashMap 클래스는 ConcurrentHashMap을 집합 뷰로 반환하는 keySet이라는 새 메서드를 제공한다. 
- 맵을 바꾸면 집합도 바뀌고 집합을 바꾸면 맵도 영향을 받는다. 
- newKeySet이라는 메서드를 이용해서 ConcurrentHashMap으로 유지되는 집합을 만들 수 있다. 
***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

