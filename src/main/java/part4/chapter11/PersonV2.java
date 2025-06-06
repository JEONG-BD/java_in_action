package part4.chapter11;

import java.util.Optional;

public class PersonV2 {
    private Optional<CarV2> car = Optional.empty();
    private int age;

    public PersonV2() {
    }

    public PersonV2(Optional<CarV2> car) {
        this.car = car;
    }

    public int getAge() {
        return age;
    }

    public Optional<CarV2> getCar() {
        return car;
    }


}
