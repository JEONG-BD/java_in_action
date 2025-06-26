package part5.chapter17;


import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

// Processor은 Subscriber 이면서 동시에 Publisher
public class TempProcessor implements Flow.Processor<TempInfo, TempInfo> {
    private Subscriber<? super TempInfo> subscriber;

    @Override
    public void subscribe(Subscriber<? super TempInfo> subscriber) {
        this.subscriber = subscriber;

    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(TempInfo item) {
        // 섭씨 -> 화씨 변환 후 전송
        subscriber.onNext( new TempInfo( item.getTown(),
                (item.getTemp() - 32) * 5 / 9) );
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
