package part4.chapter11;

import java.util.Optional;

public class PersonV2 {
    private Optional<CarV2> car = Optional.empty();

    public Optional<CarV2> getCar() {
        return car;
    }

    public PersonV2() {
    }

    public PersonV2(Optional<CarV2> car) {
        this.car = car;
    }
}
