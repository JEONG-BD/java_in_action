package part5.chapter16;

import java.util.concurrent.*;

public class AsyncMainV1 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<Double> future = es.submit(new Callable<Double>() {

            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });

        doSomethingElse();
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee){
            System.out.println("ExecutionException 발생");
        } catch (InterruptedException io){
            System.out.println("InterruptedException 발생");
        } catch (TimeoutException te){
            System.out.println("TimeoutException 발생");
            // Future 가 완료 되기 전에 타임 아웃 발생
        }
    }



    public static Double doSomeLongComputation(){
        System.out.println("doSomeLongComputation 시작");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("작업 중 인터럽트 발생");
        }
        System.out.println("doSomeLongComputation 끝");
        return 0.1;

    }

    public static void doSomethingElse(){
        System.out.println(" = ");
    }
}
