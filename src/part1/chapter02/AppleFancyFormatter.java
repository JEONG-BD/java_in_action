package part1.chapter02;

import part1.Apple;

public class AppleFancyFormatter implements AppleFormatter{

    @Override
    public String accept(Apple a) {
        String characteristic = a.getWeight() > 150? "Heavy" : "Light";
        
        return characteristic + " " + a.getColor() + " " + a;
    }
    

}
