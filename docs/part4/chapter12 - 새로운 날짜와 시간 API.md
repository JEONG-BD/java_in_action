# Chapter 12 Null 대신 Optional 클래스 
## [목차]

[12 들어가며](#12-들어가며)
[12.1 LocalDate, LocalTime, Instant, Duration, Period 클래스 ]()
* [12.1.1 LocalDate와 LocalTime 사용]()  
* [12.1.2 날짜와 시간 조합]()  
* [12.1.3 Instant 클래스 : 기계의 날짜와 시간]()  
* [12.1.4 Duration과 Period 정의]() 

[12.2 날짜 조정, 파싱 포메팅 ]()       
* [12.2.1 TemporalAdjusters 사용하기 ]()
* [12.2.2 날짜와 시간 객체 출력과 파싱]()

[12.3 다양산 시간대와 캘린더 활용 방법]()
* [12.3.1 시간대 사용하기 ]()
* [12.3.2 UTC/Greenwich 기준의 고정 오프셋]()
* [12.3.3 대안 캘린더 시스템 사용하기]() 
## 12 들어가며
- 자바 1.0 에서는 java.util.Date 클래스 하나로 날짜와 시간 관련 기능을 제공했다. 
- Date클래스는 특정 시점을 날짜가 아닌 밀리초 단위로 제공했다.(1900 기준)
- Date 클래스의 toString으로는 반환되는 문자열을 추가로 활용하기 어려웠다.
- 자바 1.1 에서는 java.util.Calendar 라는 클래스를 대안으로 제공했지만 혼란이 가중되었다. 
- Dateformat은 스레드에 안전하지 않다. 두 스레드가 동시에 하나의 포메터로 날짜를 파싱할 때 예기치 못한 결과가 발행했다. 
- Date, Calendar는 모두 가변 클래스다. 
## 12.1 LocalDate, LocalTime, Instant, Duration, Period 클래스
### 12.1.1 LocalDate와 LocalTime 사용
- 새로운 날짜와 시간 API를 사용할때 접하는 것이 LocalDate다. 
- LocalDate 인스턴스는 시간을 제외한 날짜를 표현하는 불변 객체다. 
- 정적 팩토리 of로 LocalDate 인스턴스를 만들 수 있다. 
```java
private static void printLocalDateInformation() {
        LocalDate date = LocalDate.of(2025, 06, 03);
        int year = date.getYear();
        System.out.println("year = " + year);
        Month month = date.getMonth();
        System.out.println("month = " + month);
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        int len = date.lengthOfMonth();
        System.out.println("len = " + len);
        boolean leapYear = date.isLeapYear();
        System.out.println("leapYear = " + leapYear);
}
```
- 팩토리 메서드 now는 시스템 시계의 정보를 이용해서 현재 날짜 정보를 얻는다. 
```java
LocalDate today = LocalDate.now();
```
- get 메서드에 TemporalField를 전달해서 정보를 얻는 방법도 있다. 
- TemporalField는 시간 관련 객체에서 어떤 필드에 접근할지 정의하는 인터페이스다. 
```java
private static void getChronFieldInformation(){
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day = " + day);
        System.out.println("month = " + month);
        System.out.println("year = " + year);
}
```
- 13:45:20 같은 시간은 LocalDateTime클래스로 표현할 수 있다. 
- LocalDate 클래스 처럼 LocalTime 클래스는 게터 메서드를 제공한다. 
```java
private static void getLocalTimeInformation(){
        LocalTime localTime = LocalTime.of(18, 20, 30, 21);
        System.out.println("localTime = " + localTime);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
}
```
- 날짜와 시간 문자열로 LocalDate, LocalTime의 인스턴스를 만드는 방법도 있다.
```java
private static void parseStringLocalDateTime(){
    LocalDate localDate = LocalDate.parse("2025-06-03");
    LocalTime localTime = LocalTime.parse("13:44:50");
}

```
### 12.1.2 날짜와 시간 조합
- LocalDateTime은 LocalDate와 LocalTime를 쌍으로 갖는 복합 클래스다. 
```java
public static void localDateTimeInformation(){
    LocalDateTime localDateTime = LocalDateTime.of(2025, 06, 05, 21, 33, 45, 50);
    System.out.println("localDateTime = " + localDateTime);
    LocalDate localDate = LocalDate.parse("2025-06-03");
    LocalTime localTime = LocalTime.parse("13:44:50");

    LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
    localDate.atTime(13, 45, 20);
    localDate.atTime(localTime);
    localTime.atDate(localDate);
}
```
### 12.1.3 Instant 클래스 : 기계의 날짜와 시간
- 기계의 관점에서는 연속된 시간에서 특정 지점을 하나의 큰 수로 표현하는 것이 가장 자연스러운 시간 표현 방법이다. 
- java.time.Instant 클래스에서는 이와 같은 기계적인 관점에서 시간을 표현하다. 
- 유닉스 에포크 시간(1970.01.01.00:00:00:UTC) 기준으로 특정 지점까지 시간을 초로 표현한다. 
- 팩토리 메서드 ofEpochSecond에 초를 넘겨줘서 인스턴스 생성이 가능하다. 
### 12.1.4 Duration과 Period 정의
- 위의 클래스들은 Temporal 인터페이스를 구현한다. 
- Temporal 인터페이스는 특정 시간을 모델링하는 객체의 값을 어떻게 읽고 조작할지 정의한다. 
- Duration 클래스의 정적 팩토리 메서드 between을 사용하면 두 객체 사이의 지속 시간을 만들 수 있다. 
- Duration 클래스는 초와 나노초로 시간 단위를 표현하므로 between 메서드에는 LocalDate를 전달 불가능하다. 
- 년, 월, 일로 시간을 표현할 때는 Period 클래스를 사용한다. 
```java
private static void durationLocalTime() {
    LocalTime time1 = LocalTime.of(13, 00, 00);
    LocalTime time2 = LocalTime.of(11, 30, 00);
    Duration duration = Duration.between(time1, time2);
    System.out.println(duration);
}

private static void periodLocalDate(){
    Period period = Period.between(
            LocalDate.of(2025, 1, 1), 
            LocalDate.of(2026, 1, 1));
    System.out.println(period);
}
```
- 지금 까지 살펴본 모든 클래스는 불변이다. 불변 클래스는 함수형 프로그래밍 그리고 스레드 안전성과 도메인 모델의 일관성을 유지하는 데 좋은 특징이다. 

***

## 12.2 날짜 조정, 파싱 포메팅
### 12.2.1 TemporalAdjusters 사용하기
- 
### 12.2.2 날짜와 시간 객체 출력과 파싱
***

## 12.3 다양산 시간대와 캘린더 활용 방법
### 12.3.1 시간대 사용하기
### 12.3.2 UTC/Greenwich 기준의 고정 오프셋
### 12.3.3 대안 캘린더 시스템 사용하기 
***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

