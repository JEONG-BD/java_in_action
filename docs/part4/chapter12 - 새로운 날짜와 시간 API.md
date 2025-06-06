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
### 12.1.4 Duration과 Period 정의
***

## 12.2 날짜 조정, 파싱 포메팅
### 12.2.1 TemporalAdjusters 사용하기
### 12.2.2 날짜와 시간 객체 출력과 파싱
***

## 12.3 다양산 시간대와 캘린더 활용 방법
### 12.3.1 시간대 사용하기
### 12.3.2 UTC/Greenwich 기준의 고정 오프셋
### 12.3.3 대안 캘린더 시스템 사용하기 
***
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

