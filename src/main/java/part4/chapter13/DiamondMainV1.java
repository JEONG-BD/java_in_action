package part4.chapter13;

public class DiamondMainV1 {
    public static void main(String[] args) {
        new D().hello();
    }

    static interface A {
        default void hello(){
            System.out.println("Hello from A");

        }
    }
    static interface B extends A{ }
    static interface C extends A{ }

    static class D implements B, C {
        public static void main(String[] args) {

        }
    }
}
