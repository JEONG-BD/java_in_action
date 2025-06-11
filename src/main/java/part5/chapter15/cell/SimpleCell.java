package part5.chapter15.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.concurrent.Flow.*;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();
    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }


    public void subscribe(Consumer<? super Integer> onNext) {
        subscribers.add(new Subscriber<>() {

            @Override
            public void onComplete() {}

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onNext(Integer val) {
                onNext.accept(val);
            }

            @Override
            public void onSubscribe(Subscription s) {}

        });
    }

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(Integer newValue) {
       this.value = newValue;
       System.out.println(this.name + " : " + this.value);
       nofifyAllSubscribers();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    private void nofifyAllSubscribers(){
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));

    }


}
