package part3.chapter09;

public class Refactoring1 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class");
            }
        };

        r1.run();
        int a = 10;
        Runnable r2 = () -> {
            //int a = 2;
            System.out.println("Lambda " + a );
        };
        r2.run();

        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!");
            }
        });

        doSomething((Task) ()-> System.out.println("Danger danger!"));
        doSomething((Runnable) ()-> System.out.println("Danger danger!"));


    }

    public static void doSomething(Runnable runnable){
        runnable.run();
    }

    public static void doSomething(Task task){
        task.execute();
    }



    static interface Task {
        public void execute();
    }
}
