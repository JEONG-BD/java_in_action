package part1.chapter02.ex;

public class MeaningOfThis {

    private final int value = 4; 

    public void doIt(){
        int value = 5; 
        System.out.println("DoIt");
        Runnable r = new Runnable() {
            private final int value = 5;
            
            @Override
            public void run(){
                System.out.println("Runnable Inner");
                int value = 10; 
                System.out.println(this.value);
            }
        };
        r.run();
    }
    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
