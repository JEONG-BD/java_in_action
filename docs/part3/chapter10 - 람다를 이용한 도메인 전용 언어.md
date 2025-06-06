# Chapter 10 람다를 이용한 도메인 전용 언어     
## 목차
[10.1 도메인 전용 언어](#101-도메인-전용-언어)
  * [10.1.1 DSL의 장점과 단점](#1011-dsl의-장점과-단점)
  * [10.1.2 JVM에서 이용할 수 있는 다른 DSL 해결책](#1012-jvm에서-이용할-수-있는-다른-dsl-해결책)

[10.2 최신 자바 API의 작은 DSL](#102-최신-자바-api의-작은-dsl)
  * [10.2.1 스트림 API는 컬렉션을 조작하는 DSL](#1021-스트림-api는-컬렉션을-조작하는-dsl)
  * [10.2.2 데이터를 수집하는 DSL인 Collectors](#1022-데이터를-수집하는-dsl인-collectors)


[10.3 자바로 DSL을 만드는 패턴과 기법](#103-자바로-dsl을-만드는-패턴과-기법)
  * [10.3.1 메서드 체인](#1031-메서드-체인)
  * [10.3.2 중첩된 함수 이용](#1032-중첩된-함수-이용)
  * [10.3.3 람다 표현식을 이용한 함수 시퀀싱](#1033-람다-표현식을-이용한-함수-시퀀싱)
  * [10.3.4 조합하기](#1034-조합하기)
  * [10.3.5 DSL에 메서드 참조 사용하기](#1035-dsl에-메서드-참조-사용하기)

[10.4 실생활의 자바 8 DSL](#104-실생활의-자바-8-dsl)
  * [10.4.1 jOOQ](#1041-jooq)
  * [10.4.2 큐컴버](#1042-큐컴버)
  * [10.4.3 스프링 통합](#1043-스프링-통합)
*** 
## 10.1 도메인 전용 언어
- DSL(domain-specific-languages)은 특정 비즈니스 도메인의 문제를 해결할려고 만든 언어이다. 
- DSL은 범용 프로그래밍 언어가 아니다 동작과 용어는 특정 도메인에 국한 되므로 다른 문제를 걱정할 필요없이 자신의 앞에 놓인 문제를 어떻게 해결할 수 있으지에만 집중 가능하다. 
- DSL을 이용하면 사용자가 특정 도메인의 복잡성을 잘 다룰 수 있다. 
- DSL의 필요성은 의사 소통과 가독성에 있다. 
### 10.1.1 DSL의 장점과 단점
#### DSL의 장점 
- 간결함 
- 가독성 
- 유지보수 
- 높은 수준의 추상화 
- 집중 
- 관심사 분리 
#### DSL의 단점 
- DSL 설계의 어려움 
- 개발 비용 
- 추가 우회 계층 
- 새로 배워야 하는 언어 
- 호스팅 언어의 한계 

### 10.1.2 JVM에서 이용할 수 있는 다른 DSL 해결책
- DSL의 카테고리를 구분하는 가장 흔한 방법은 마틴 파울러가 소개한 방법으로 DSL을 내/외부로 나누는 것이다. 
#### 내부 DSL 
- 내부 DSL 이란 자바로 구현한 DSL을 의미한다. 
- 역사적으로 자바를 이용한 표현력 있는 DSL을 만드는 것에 한계가 존재 했지만 람다 표현식으로 이 문제가 어느 정도 해결되었다. 
- 
```java
List<String> numbers = Arrays.asList("one", "two", "three"); 
numbers.forEach( new Consumer<String>() {
    @Override
    public void accept( String s ) {
        System.out.println(s);
    } 
});

numbers.forEach(s -> System.out.println(s));
```
- 기존 자바 언어를 이용하면 외부 DSL 에 비해 새로운 패턴과 기술을 배워 DSL을 구현하는 노력이 현저하게 줄어든다. 
- 순수 자바로 DSL을 구현하면 나머지 코드와 함께 DSL 컴파일이 가능하므로, 외부 DSL을 만드는 도구를 사용할 필요도 없고 외부 도구를 배울 필요도 없으므로 추가로 비용이 들지 않는다. 
- 한 개의 언어로 한 개의 도메인 또는 여러 도메인을 대응하지 못 해 추가로 DSL을 개발해야 하는 상황에서 쉽게 합칠 수 있다. 
#### 다중 DSL 
- JVM에서 실행되는 언어는 100개가 넘는다. 
- DSL은 기반 프로그래밍 언어의 영향을 받으므로 간결한 DSL을 만드는데 새로운 언어의 특성들이 아주 중요하다. 
- 새로운 언어를 배우거나, 해당 기술을 소지하고 있어야 한다. 
- 두 개 이상의 언어가 혼재하므로 여러 컴파일러로 소스를 빌드하도록 빌드 과정을 개선해야 한다. 
- JVM에서 실행되는 언어가 자바와 호환성이 완벽하지 않을 때가 있고 따라서 성능 손실의 가능성이 있다. 
#### 외부 DSL 
- 프로젝트 DSL을 추가하는 세번째 옵션은 외부 DSL을 구현하는 것이다. 
- 외부 DSL을 개발하는 가장 큰 장점은 외부 DSL이 제공하는 무한한 유연성이다. 

## 10.2 최신 자바 API의 작은 DSL
- 자바의 새로운 기능의 장점을 적용한 첫 API는 네이티브 자바 API 자신이다. 

### 10.2.1 스트림 API는 컬렉션을 조작하는 DSL
- Stream 인터페이스는 네이티브 자바 API에 작은 내부 DSL을 적용한 종은 예로 컬렉션의 항목을 필터, 정렬, 변환하는 작지만 강력한 DSL로 볼 수 있다. 

### 10.2.2 데이터를 수집하는 DSL인 Collectors
- Collector 인터페이스는 데이터 수집을 수행하는 DSL로 간주할 수 있다.

*** 
## 10.3 자바로 DSL을 만드는 패턴과 기법
- DSL은 특정 도메인 모델에 적용할 친화적이고 가독성 높은 API를 제공한다. 
- 
### 10.3.1 메서드 체인
- DSL에서 가장 흔한 방식으로 아래 방법을 이용해서 한 개의 메서드 호출 체인으로 거래 주문을 정의하는 것이 가능하다. 
```java
public static void methodChaining() {
    Order order = forCustomer("BigBank")
            .buy(80).stock("IBM").on("NYSE").at(125.00)
            .sell(50).stock("GOOGLE").on("NASDAQ").at(375.00)
            .end();

    System.out.println("Method chaining:");
    System.out.println(order);
}
```
### 10.3.2 중첩된 함수 이용
```java
public static void nestedFunction() {
    Order order = order("BigBank",
            buy(80,
                    stock("IBM", on("NYSE")),
                    at(125.00)),
            sell(50,
                    stock("GOOGLE", on("NASDAQ")),
                    at(375.00))
    );

    System.out.println("Nested function:");
    System.out.println(order);
}

```

### 10.3.3 람다 표현식을 이용한 함수 시퀀싱
```java
    public static void lambda() {
        Order order = LambdaOrderBuilder.order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });
    }

```
### 10.3.4 조합하기

### 10.3.5 DSL에 메서드 참조 사용하기
***
## 10.4 실생활의 자바 8 DSL
### 10.4.1 jOOQ
- SQL은 DSL은 가장 흔히 광범위 하게 사용하는 분야
- jOOQ는 SQL을 구현하는 내부적 DSL로 자바에 직접 내장된 형식 안전 언어이다. 
- 스트림 API와 조합해서 사용할 수 있다는 것이 jOOQ DSL의 장점이다. 

### 10.4.2 큐컴버
- 동작 주도 개발은 테스트 주도 개발의 확장으로 다양한 비즈니스 시나리오를 구조적으로 서술하는 간단한 도메인 전용 스트립팅 언어를 사용한다. 
- 큐컴버는 명령문을 실행할 수 있는 테스트 케이스로 변환된다. 
### 10.4.3 스프링 통합
- 스프링 통합은 엔터프라이즈 통합 패턴을 지원할 수 있도록 의존성 주입에 기반한 스프링 프로그래밍 모델을 확장한다.

***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

