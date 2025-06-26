package part5.chapter17;

import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;

public class TempSubScription implements Subscription {

    private final Subscriber<? super TempInfo> subscriber;

    private final String town;

    public TempSubScription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        for (int i = 0; i < n ; i++) {
            try {
                // 온도 Subscriber로 전달
                subscriber.onNext(TempInfo.fetch(town));
            } catch (Exception e) {
                // 에러 전달
                subscriber.onError(e);
                break;
            }
        }
    }

    @Override
    public void cancel() {
        // 구독 취소 되면 완료를 Subscriber에게 전달
        subscriber.onComplete();
    }
}
