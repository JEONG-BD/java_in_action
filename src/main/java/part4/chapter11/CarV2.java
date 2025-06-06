package part4.chapter11;

import java.util.Optional;

public class CarV2 {


    private Optional<InsuranceV2> insurance = Optional.empty();

    public Optional<InsuranceV2> getInsurance() {
        return insurance;
    }

    public CarV2() {
    }

    public CarV2(Optional<InsuranceV2> insurance) {
        this.insurance = insurance;
    }
}
