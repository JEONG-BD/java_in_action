package part5.chapter17;

import java.util.concurrent.Flow;

import static java.util.concurrent.Flow.*;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(TempInfo item) {
        // 구독 저장 요청 전달
        System.out.println("OnNext.start");
        System.out.println(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error");
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");

    }
}
