package part4.chapter13;

public class ConflictMainV2 {

    public static void main(String[] args) {
        new D().hello();
    }

    static interface A{
        default void hello() {
            System.out.println("Hello from A");
        }
    }


    static interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class D implements B, A {

        @Override
        public void hello() {
            B.super.hello();
        }
    }


}



