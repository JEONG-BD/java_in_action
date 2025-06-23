# Chapter 16 CompletableFuture 안정적인 비동기 프로그래밍  
## [목차]
[16.1 Futurue 단순 활용](#161-future-단순-활용)
* [16.1.1 Future 제한](#1611-future-제한)
* [16.1.2 CompletableFuture로 비동기 애플리케이션 만들기](#1612-completablefuture로-비동기-애플리케이션-만들기-)

[16.2 비동기 API 구현](#162-비동기-api-구현)
* [16.2.1 동기 메서드를 비동기 메서드로 변환](#1621-동기-메서드를-비동기-메서드로-변환)
* [16.2.2 에러 처리 방법](#1622-에러-처리-방법)

[16.3 비블록 코드 만들기](#163-비블록-코드-만들기)
* [16.3.1 병렬 스트림으로 요청 병렬화 하기](#1631-병렬-스트림으료-요청-병렬화-하기)
* [16.3.2 completablefuture로 비동기 호출 구현하기](#1632-completablefuture로-비동기-호출-구현하기)
* [16.3.3 확장성이 좋은 해결 방법](#1633-확장성이-좋은-해결-방법)
* [16.3.4 커스텀executor 사용하기](#1634-커스텀-executor-사용하기)

[16.4 비동기 작업 파이프라인 만들기](#164-비동기-작업-파이프라인-만들기)
* [16.4.3 동기 작업과 비동기 작업 조합하기](#1643-동기-작업과-비동기-작업-조합하기-)
* [16.4.4 독립 completablefuture와 비독립 completablefuture 합치기](#1644-독립-completablefuture와-비독립-completablefuture-합치기)
* [16.4.5 future의 리플렉션과 completablefuture 리플렉션](#1645-future의-리플렉션과-completablefuture-리플렉션)

## 16.1 Future 단순 활용 
- 자바 5부터는 미래의 어느 시점에 결과를 얻는 모델에 활용할 수 있도록 Future 인터페이스를 제공한다. 
- 비동기 계산을 모델링 하는 데 Future를 이용할 수 있으며 Future는 계산이 끝났을 때 결과에 접근할 수 있는 참조를 제공한다. 
- 시간이 걸릴 수 있는 작업을 Future내부로 설정 하면 호출자가 스레드 결과를 기다리는 동안 유용한 작업 수행이 가능하다. 
- ExecutorService에서 제공하는 스레드가 시간이 오래 걸리는 작업을 처리하는 동안 우리? 스레드로 다른 작업을 동시에 실행할 수 있다. 
- 다른 작업을 처리하다가 시간이 오래 걸리는 작업의 결과가 필요한 시점이 되었을 때 Futurue get메서드로 결과를 가져올 수 있다.   
- get 메서드를 호출했을 때 이미 계산 결과가 준비되었다면 즉시 결과를 반환하지만 결과가 준비 되지 않았다면 완료 될때 까지 우리 스레드를 블록 시킨다. 

### 16.1.1 Future 제한 
- Future 인터페이스가 비동기 계산이 끝났는지 확인할 수 있는 isDone 메서드, 계산이 끝나기를 기다리는 메서드, 결과 회수 메서드를 제공한다. 
- 위 메서드 만으로는 간결하고 동시 실행 코드를 구현하기에 충분하지 않다. 

### 16.1.2 CompletableFuture로 비동기 애플리케이션 만들기 
- 비동기 API 제공하는 방법 
- 동기 API를 사용해야 할때 코드를 비블록으로 만드는 방법 
- 비동기 동작의 완료에 대응하는 방법 

## 16.2 비동기 API 구현 
```java
public double getPrices(String product){
    //System.out.println("1. [getPrices method]");
    return calculatePrice(product);
}
public double calculatePrice(String product) {
  //System.out.println("2. [calculatePrice method]");
  delay();
  return random.nextDouble() * product.charAt(0) + product.charAt(1);
}

public static void delay() {
  int delay = 1000;
  //int delay = 500 + RANDOM.nextInt(2000);
  try {
    Thread.sleep(delay);
  } catch (InterruptedException e) {
    throw new RuntimeException(e);
  }
}
```
- 위 코드를 호출하면 1초동안 블록된다. 
### 16.2.1 동기 메서드를 비동기 메서드로 변환
```java
public Future<Double> getAsyncPrices(String product){
    //System.out.println("1. [getPrices method]");
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
        double price = calculatePrice(product);
        futurePrice.complete(price); // 계산이 완료 되면 Future에 값을 설정 
    }).start();
    return futurePrice;
}
```
- Future는 결과값의 핸들일 뿐이고 **계산이 완료되면 get메서드를 통해 결과를 얻을수 있다.** 
### 16.2.2 에러 처리 방법 
- 위 코드에서 가격을 계산하는 동안 에러가 발생하면 해당 스레드에만 영향을 미친다. 
- 블록 문제가 발생할 경우에는 타임 아웃을 활용하는 것이 좋다. 
- 문제가 발생했을 때 클라이언트가 영워히 블록되지 않고 타임아웃 시간이 지나면 TimeoutException을 받을 수 있다. 
- 하지만 왜 에러가 발생했는지 알 수 없기 때문에 completeExceptionally 메서드를 이용해서 CompletableFuture 내부에서 발생한 예외를 클라이언트로 전달해야 한다. 
```java
public Future<Double> getAsyncPricesCompleteExceptionally(String product){
    //System.out.println("1. [getPrices method]");
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
        try {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        } catch (Exception ex){
            futurePrice.completeExceptionally(ex);
        }
    }).start();
    return futurePrice;
}

```

#### 팩터리 메서드 supplyAsync 로 CompletableFuture 만들기 
```java
public Future<Double> getAsyncPricesCompleteV2(String product){
    //System.out.println("1. [getPrices method]");
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
}
```
- 16.2.2 코드를 위와 같이 조금 더 간단하게 구현 가능하다. 
- suppluAsync()메서드는 Supplier를 인수로 받아서 CompletableFuture를 반환한다. 
- 
## 16.3 비블록 코드 만들기
### 16.3.1 병렬 스트림으료 요청 병렬화 하기 
```java
public  List<String> findPrice(String prodduct){
    return shops.stream()
            .map(shop -> String.format("%s price is %.2f",
                    shop.getName(),
                    shop.getPrices(prodduct)))
            .collect(Collectors.toList());
}


public  List<String> findPriceParallel(String prodduct){
  return shops.parallelStream()
          .map(shop -> String.format("%s price is %.2f",
                  shop.getName(),
                  shop.getPrices(prodduct)))
          .collect(Collectors.toList());
}
```
- 병렬 스트림을 이용해서 순차 계산을 병렬로 처리 해서 성능 개선을 할 수 있다. 
### 16.3.2 CompletableFuture로 비동기 호출 구현하기 
```java
public  List<String> findPriceCompletableFutureExecutor(String prodduct){

    List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                    () -> String.format("%s price is %.2f",
                            shop.getName(),
                            shop.getPrices(prodduct), executor))
            )
            .collect(Collectors.toList());


    List<String> prices = priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    return prices;

}
```
- 두 map연산을 하나의 스트림 처리 파이프라인으로 처리하지 않고 두 개의 스트림 파이프 라인으로 처리 했다. 
- 스트림 연산은 게으른 특성이 있으므로 하나의 파이프 라인으로 처리 하면 모든 가격 정보 요청 동작이 동기적, 순차적으로 이루어진다. 
- 순차적 블록 방식의 구현에 비해서 빠르지만 병렬 스트림을 사용한 구현보다는 느리다. 
### 16.3.3 확장성이 좋은 해결 방법 
- 병렬 스트림 버전에서는 네 개의 상점을 검색, 네 개의 스레드가 사용되고 네 개의 스레드 중에 하나가 완료 해야 다섯 번째 작업이 가능하다. 
### 16.3.4 커스텀 Executor 사용하기 
```java

public final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
});

```
## 16.4 비동기 작업 파이프라인 만들기 
### 16.4.3 동기 작업과 비동기 작업 조합하기 
```java
public List<String> findPricesDiscountFuture(String product){
    List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                    () -> shop.getPriceWithDiscount(product), executor
            ))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote ->
                    CompletableFuture.supplyAsync(
                            () -> Discount.applyDiscount(quote), executor
                    )))
            .collect(Collectors.toList());

    return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
}
```
- 세 개의 map 연산을 적용하고, CompletableFuture 클래스의 기능을 사용해서 동작을 비동기로 처리한다. 
- 자바 8의 thenCompose 메서드는 두 비동기 연산을 파이프라인으로 만들 수 있다. 
- thenCompose 메서드는 첫 번재 연산의 결과를 두 번째 연산을 전달한다. 
### 16.4.4 독립 CompletableFuture와 비독립 CompletableFuture 합치기 
- 첫번째 CompletableFuture에 thenCompose 메서드 실행 후 다응 실행 결과를 첫번째 실행 결과를 입력으로 받는 CompletableFuture로 전달한다. 

### 16.4.5 Future의 리플렉션과 CompletableFuture 리플렉션
- 자바 8 이전의 Future에 비해 CompletableFuture는 람다 표현식을 사용 이 덕분에 다양한 동기, 비동기 테스크를 활용해서 복잡한 연산 수행이 가능하다. 

### 16.4.6 타임 아웃 효과적으로 사용하기 
- 자바 9에서 CompletableFuture 에서 getTimeout 메서드는 지정된 시간이 지난 후에 CompletableFuture 를 TimeoutException으로 완료하면서 또 다른 CompletableFuture를 반환할 수 있도록 ScheduledThreadExecutor를 활용한다.
***
 
> 마틴 게이브리얼 우르마, 『모던 자바 인 액션』, 한빛미디어 (2019)  

