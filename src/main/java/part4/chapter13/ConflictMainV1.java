package part4.chapter13;

public class ConflictMainV1 {

    public static void main(String[] args) {
        new C1().hello();
        new C().hello();

    }
    static interface A{
        default void hello() {
            System.out.println("Hello from A");
        }
    }


    static interface B extends A{
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class D implements A {
        public void hello() {
            System.out.println("Hello from D");
        }
    }

    static class C implements B, A {
        public static void main(String[] args) {
            new C().hello();
        }
    }

    static class C1 extends D implements B, A {
        public static void main(String[] args) {
            new C().hello();
        }
    }




}



