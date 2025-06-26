package part5.chapter17;
import java.util.concurrent.Flow.Publisher;


public class TempProcessorMainV1 {
    public static void main(String[] args) {
        getCelsiusTemperatures("New york").subscribe(new TempSubscriber());

    }

    private static Publisher<TempInfo> getCelsiusTemperatures(String town) {

        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubScription(processor, town));
        };
    }
}
