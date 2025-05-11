package part3.chapter09;

public class StrategyMain1 {

    
    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumric());
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println("b1 = " + b1);
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println("b2 = " + b2);
        Validator numericLambda = new Validator((s) -> s.matches("[a-z]+"));
        Validator lowerCaseLambda = new Validator((s) -> s.matches("\\d+"));
        boolean b3 = numericLambda.validate("aaaa");
        boolean b4 = lowerCaseLambda.validate("bbbb");
        System.out.println("b3 = " + b3);
        System.out.println("b4 = " + b4);

    }



    static interface ValidationStrategy {
        boolean execute(String s);
    }

    public static class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");

        }
    }

    public static class IsNumric implements ValidationStrategy{
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    static private class Validator {

        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy v) {
            strategy = v;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }

    }
}


