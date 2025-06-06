# Chapter 11 Null 대신 Optional 클래스 
## 목차
[11.1 값이 없는 상황에서 어떻게 처리할까?](#111-값이-없는-상황에서-어떻게-처리할까)
* [11.1.1 보수적인 자세로 NullPointerException 줄이기](#111-값이-없는-상황에서-어떻게-처리할까) 
* [11.1.2 Null 때문에 발생하는 문제](#1112-null-때문에-발생하는-문제) 
* [11.1.3 다른 언어는 Null 대신에 무얼 사용하나?](#1113-다른-언어는-null-대신에-무얼-사용하나) 

[11.2 Optional 클래스 소개](#112-optional-클래스-소개)  
[11.3 Optional 적용 패턴](#113-optional-적용-패턴) 
* [11.3.1 Optional 객체 만들기](#1131-optional-객체-만들기) 
* [11.3.2 맵으로 Optional의 값을 추출하고 변환하기](#1132-맵으로-optional의-값을-추출하고-변환하기) 
* [11.3.3 flatMap으로 Optional객체 연결](#1133-flatmap으로-optional-객체-연결) 
* [11.3.4 Optional 스트림 조작](#1134-optional-스트림-조작) 
* [11.3.5 디폴트 액션과 Optional 언랩](#1135-디폴트-액션과-optional-언랩) 
* [11.3.6 두 Optional 합치기](#1136-두-optional-합치기) 
* [11.3.7 필터로 특정값 거르기](#1137-필터로-특정값-거르기)

[11.4 Optional을 사용한 실용 예제](#114-optional을-사용한-실용-예제)
* [11.4.1 잠재적으로 Null이 될 수 있는 대상을 Optional로 감싸기](#1141-잠제적으로-null이-될-수-있는-대상을-optional로-감싸기) 
* [11.4.2 예외와 Optional 클래스](#1142-예외와-optional-클래스) 
* [11.4.3 기본형 Optional를 사용하지 말아야 하는 이유](#1143)
*** 
## 11.1 값이 없는 상황에서 어떻게 처리할까?
### 11.1.1 보수적인 자세로 NullPointerException 줄이기
- 예기치 않은 NullPointerException을 피하려면 대부분의 프로그래머는 필요한 곳에서 다양한 Null확인 코드를 추가해서 Null 문제를 해결할 것이다. 
```java
public static String getCarInsuranceNameNullChcek(Person person){
    if (person != null){
        Car car = person.getCar();
        if (car != null){
            Insurance insurance = car.getInsurance();
            if (insurance != null){
                return insurance.getName();
            }
        }
    }
    return "Unknown";
}
```
- 위 메서드에서는 모든 변수가 null 인지 의심하므로 변수를 접근할 때마다 중첩된 if가 추가되면서 코드 들여쓰기 수준이 증가한다. 
- 반복 패턴(recurring pattern)코드를 깊은 의심(deep doubt)라고 한다. 
- 코드의 구조가 엉망이 되고 가독성이 떨어진다. 
```java
public static String getCarInsuranceNameNullCheckV2(Person person){
    if (person == null) {
        return "Unknown";
    }
    Car car = person.getCar();
    if (car == null) {
        return "Unknown";
    }
    Insurance insurance = car.getInsurance();
    if (insurance == null){
        return "Unknown";
    }
    return insurance.getName();
}
```
- 다른 방법으로 중첩 if 블록을 없애지만, 메서드에 네 개의 출구가 생겼고 이로 인해서 유지보수하기가 어려워진다. 

### 11.1.2 Null 때문에 발생하는 문제
- NullpointerException은 자바에서 가장 흔히 발생하는 에러이다. 
- 중첩된 null 확인 코드를 추가해야 하므로 null 때문에 가독성이 떨어진다. 
- null은 아무 의미도 표한하지 않는다. 
- 자바 철학에 위배된다? 
- 형식 시스템에 구멍은 만든다
  - null은 무형식이며 정보를 포함하고 있지 않는다. 
  - 모든 참조형식에 null을 할당할 수 있고 이런식으로 할당된 null이 시스템의 다른 부분으로 퍼졌을 때 애초에 null이 어떤 의미로 사용되었는지 알 수 없다. 
### 11.1.3 다른 언어는 Null 대신에 무얼 사용하나?
- 그루비 같은 언어에서는 내비게이션 연산자를 도입 
- 하스켈, 스칼라 등의 함수형 언어는 선택형 값을 저장할 수 있는 형식을 제공한다. 
- 자바 8은 선택형 값 개념의 영향을 받아서 java.util.Optional<T>라는 새로운 클래스를 제공한다. 
***
## 11.2 Optional 클래스 소개
- 자바 8은 하스켈과 스칼라의 영향을 받아서 java.util.Optional<T>라는 새로운 클래스를 제공한다. 
- Optional은 선택형값을 캡슐화하는 클래스다. 
- 값이 있으면 <u>Optional 클래스는 값을 감싸고 값이 없으면 Optional.empty 메서드로 Optional을 반환한다.</u>
- null을 참조하려면 NullPointerException이 발생하지만 Optional.empty()는 Optional객체이므로 이를 다양한 방식으로 활용이 가능하다. 
- Optional 클래스를 사용하면서 모델의 의미 semantic가 더 명확해졌음을 확인 가능하다. 
- Optional 을 이용하면 값이 없는 상황이 데이터의 문제인지 알고리즘의 버그인지 명확하게 구분이 가능하다. 
*** 
## 11.3 Optional 적용 패턴
### 11.3.1 Optional 객체 만들기
#### 빈 Optional 
- 정적 팩토리 메서드 Optional.empty로 빈 Optional 객체를 얻을 수 있다. 
```java
Optional<Car> optCar = Optional.empty();
```
#### null이 아닌 값으로 Optional 만들기 
- 정적 팩토리 메서드 Optional.of로 null이 아닌 값을 포함하는 Optional을 만들 수 있다. 
```java
Optional<Car> optCar = Optional.of(Car);
```
#### null값으로 Optional 만들기 
- 정적 팩토리 메서드 Optional.ofNullable로 null 값을 저장할 수 있는 Optional을 만들 수 있다.

```java
Optional<Car> optCar = Optional.ofNullable(car);
```
### 11.3.2 맵으로 Optional의 값을 추출하고 변환하기
- 보통 객체의 정보를 추출할 때 Optional을 사용할 때가 많다.
### 11.3.3 flatMap으로 Optional 객체 연결
```java
Optional<PersonV2> optPerson = Optional.of(person);

optPerson.map(PersonV2::getCar)
        .map(CarV2::getInsurance)
        .map(InsuranceV2::getName);
```
- 위 코드를 컴파일 되지 않는다. map 연산의 결과가 중첩 Optional<Optional<Object>>이기 때문이다. 
- 중첩 Optional객체 구조를 반환하기 위해서는 flatMap를 사용한다. 
- flatMap은 함수를 인수로 받아서 다른 스트림을 반환하는 메서드다. 
- 즉 함수를 적용해서 생성된 모든 스트림이 하나의 스트림으로 병합되어 평준화된다. 
#### Optional로 자동차의 보험회사 이름 찾기 
```java
return person.flatMap(PersonV2::getCar)
        .flatMap(CarV2::getInsurance)
        .map(InsuranceV2::getName)
        .orElse("UnKnown");
```
#### Optional을 활용한 Person/Car/Insurance 참조 체인 
- Person을 Optional로 감싼 다음 flatMap(Person::getCar)을 호출하면 Optional내부의 Person에 Function을 적용한다. 
- getCar 메서드는 Optional<Car>를 반환하므로 중첩 Optional이 생성된다. 
- flapMap 연산으로 Optional 을 평준화 한다. 
- 평준화 과정이란 두 Optional을 합치는 기능을 수행하면서 둘중 하나라도 null이면 빈 Optional을 생성한다. 
 
### 11.3.4 Optional 스트림 조작
- 자바 9에서는 Optional 을 포함하는 스트림을 쉽게 처리할 수 있도록 Optional에 stream 메서드를 추가했다.
```java
public static Set<String> getCarInsuranceNames(List<PersonV2> persons){
      return persons.stream()
              .map(PersonV2::getCar)
              .map(optCar -> optCar.flatMap(CarV2::getInsurance))
              .map(optIns -> optIns.map(InsuranceV2::getName))
              .flatMap(Optional::stream)
              .collect(Collectors.toSet());
}    
public static Set<String> getCarInsuranceNamesV2(List<PersonV2> persons){
      Stream<Optional<String>> result = persons.stream()
              .map(PersonV2::getCar)
              .map(optCar -> optCar.flatMap(CarV2::getInsurance))
              .map(optIns -> optIns.map(InsuranceV2::getName));

      Set<String> collect = result.filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toSet());
      return collect;
  }
```
### 11.3.5 디폴트 액션과 Optional 언랩
- get()은 값을 읽는 가장 간단한 메서드면서 동시에 가장 <u>안전하지 않은 메서드다.</u>
- 메서드 get은 래핑된 값이 있으면 해당 값을 반환하고 값이 없으면 NoSuchElementException을 발생시킨다. 
- 반드시 Optional에 값이 있다고 가정할 수 있는 상황이 아니라면 get메서드 사용을 지양해야 한다. 
- orElse를 사용하면 Optional이 값을 포함하지 않을 때 기본 값을 제공항 수 있다. 
- orElseGet은 orElse메서드에 대응하는 게으른 버전의 메서드로, Optional에 값이 없을때만 Supplier이 실행된다. 
- orElseThrow는 Optional값이 비었을 때 예외를 발생시키며, 발생시킬 예외를 선택가능하다. 
- ifPresent 를 이용하면 값이 존재할 때 인수로 넘겨준 동작을 실행할 수 있다. 
- ifPresentOrElse 는 Optional 이 비을 때 실행할 수 있는 Runnable을 인수로 받는다. 

### 11.3.6 두 Optional 합치기
```java

public Optional<InsuranceV2> nullSafeFindCheapstInsurance(Optional<PersonV2> person, Optional<CarV2> car){

        if(person.isPresent() && car.isPresent()){
            return Optional.of(findCheapesInsurance(person.get(), car.get()));
        }else {
            return Optional.empty();
        }
}

public Optional<InsuranceV2> nullSafeFindCheapstInsuranceV2(Optional<PersonV2> person, Optional<CarV2> car){

        return person.flatMap(p -> car.map(c -> findCheapesInsurance(p, c)));
}
```
### 11.3.7 필터로 특정값 거르기
- filter 메서드는 프레디케이트를 인수로 받는다. 
- Optional 객체가 값을 가지며 프레디케이트와 일치하면 filter메서드는 그 값을 반환하고 그렇지 않으면 빈 Optional객체를 반환한다. 
```java
  public String getCarInsuranceNameAge(Optional<PersonV2> person, int minAge){
      return person.filter(p-> p.getAge() > minAge)
              .flatMap(PersonV2::getCar)
              .flatMap(CarV2::getInsurance)
              .map(InsuranceV2::getName)
              .orElse("Unknown");
  }
```
***
## 11.4 Optional을 사용한 실용 예제
- Optional 클래스를 효과적으로 이용하려면 잠재적으로 존재하지 않는 값의 처리 방법을 바꿔야  한다. 
- 코드 구현을 바꾸는 것이 아니라 네이티브 자바 API와 상호작용 하는 방식도 바꿔야 한다.
### 11.4.1 잠제적으로 Null이 될 수 있는 대상을 Optional로 감싸기
- 기존의 자바 API에서는 null 을 반환하면서 요청한 값이 없거나 어떤 문제로 계산에 실패했음을 알린다. 
- Map의 get 메서드는 요청한 키에 대응하는 값을 찾지 못했을 때 null을 반환하는데 Optional을 반환하는 것이 더 바람직하다.

```java
import java.util.Optional;

// Before 
Object value = map.get("key");
// After 
Optional<Object> value = Optional.ofNullable(map.get("key"));
```
### 11.4.2 예외와 Optional 클래스
- 자바 API는 어떤 이유에서 값을 제공할 수 없을 때 null을 반환하는 대신 예외를 발생시킬 때도 있다. 
- 전형적인 예가 문자열을 정수로 반환하는 정적메서드 Integer.parseInt(String)이다. 
```java
public static Optional<Integer> stringToInt(String s){
    try {
        return Optional.of(Integer.parseInt(s));
    }catch (NumberFormatException e){
        return Optional.empty();
    }
}
```
### 11.4.3 기본형 Optional를 사용하지 말아야 하는 이유
- 스트림처럼 Optional도 기본형으로 특화된 OptionalInt, OptionalLong, OptionalDouble등의 클레스를 제공한다. 
- 기본형 특화 Optional은 Optional 클래스의 유용한 메서드 map, flatMap, filter 등을 지원하지 않는다. 

***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

