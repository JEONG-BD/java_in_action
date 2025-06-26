package part5.chapter17;

import part5.chapter15.cell.SimpleCell;

import java.util.concurrent.Flow;

public class TempMainV1 {
    public static void main(String[] args) {

        getTemperature("NewYork").subscribe(new TempSubscriber());
    }
    // publisher 생성 후 구독 시작
    private static Flow.Publisher<TempInfo> getTemperature(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubScription(subscriber, town));
    }
}
