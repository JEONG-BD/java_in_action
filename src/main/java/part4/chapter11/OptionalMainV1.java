package part4.chapter11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalMainV1 {
    public static void main(String[] args) {
        
        //Person person = new Person();
        //String insuranceName = getCarInsuranceName(person);
        //String insuranceName = getCarInsuranceNameNullCheckV1(person);
        //String insuranceName = getCarInsuranceNameNullCheckV2(person);
        Optional<PersonV2> person = Optional.of(new PersonV2());
        String insuranceName = getCarInsuranceNameOptionalV1(person);

        InsuranceV2 insurance1 = new InsuranceV2("Test1");
        CarV2 car = new CarV2(Optional.of(insurance1));

        List<PersonV2> persons = List.of(new PersonV2(), new PersonV2(Optional.of(car)), new PersonV2());
        System.out.println("insuranceName = " + insuranceName);
        Set<String> carInsuranceNames = getCarInsuranceNames(persons);
        System.out.println("carInsuranceNames = " + carInsuranceNames);
    }

    // Null pointer exception
    public static String getCarInsuranceName(Person person){
        return person.getCar().getInsurance().getName();
    }

    public static String getCarInsuranceNameNullCheckV1(Person person){
        if (person != null){
            Car car = person.getCar();
            if (car != null){
                Insurance insurance = car.getInsurance();
                if (insurance != null){
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
    
    public static String getCarInsuranceNameNullCheckV2(Person person){
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null){
            return "Unknown";
        }
        return insurance.getName();
    }
    
    public static String getCarInsuranceNameOptionalV1(Optional<PersonV2> person) {


        //Optional<PersonV2> optPerson = Optional.of(person);

        //optPerson.map(PersonV2::getCar)
        //        .map(CarV2::getInsurance)
        //        .map(InsuranceV2::getName);

        return person.flatMap(PersonV2::getCar)
                .flatMap(CarV2::getInsurance)
                .map(InsuranceV2::getName)
                .orElse("UnKnown");

    }

    
    public static Set<String> getCarInsuranceNames(List<PersonV2> persons){
        return persons.stream()
                .map(PersonV2::getCar)
                .map(optCar -> optCar.flatMap(CarV2::getInsurance))
                .map(optIns -> optIns.map(InsuranceV2::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }    
    
    public static Set<String> getCarInsuranceNamesV2(List<PersonV2> persons){
        Stream<Optional<String>> result = persons.stream()
                .map(PersonV2::getCar)
                .map(optCar -> optCar.flatMap(CarV2::getInsurance))
                .map(optIns -> optIns.map(InsuranceV2::getName));

        Set<String> collect = result.filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        return collect;
    }


    public InsuranceV2 findCheapesInsurance(PersonV2 person,  CarV2 car){
        return null;
    }


    public Optional<InsuranceV2> nullSafeFindCheapstInsurance(Optional<PersonV2> person, Optional<CarV2> car){

            if(person.isPresent() && car.isPresent()){
                return Optional.of(findCheapesInsurance(person.get(), car.get()));
            }else {
                return Optional.empty();
            }
    }

    public Optional<InsuranceV2> nullSafeFindCheapstInsuranceV2(Optional<PersonV2> person, Optional<CarV2> car){

            return person.flatMap(p -> car.map(c -> findCheapesInsurance(p, c)));
    }

    public String getCarInsuranceNameAge(Optional<PersonV2> person, int minAge){
        return person.filter(p-> p.getAge() > minAge)
                .flatMap(PersonV2::getCar)
                .flatMap(CarV2::getInsurance)
                .map(InsuranceV2::getName)
                .orElse("Unknown");


    }

    public static Optional<Integer> stringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();
        }
    }
}
