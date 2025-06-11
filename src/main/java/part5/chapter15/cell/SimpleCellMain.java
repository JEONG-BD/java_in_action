package part5.chapter15.cell;

public class SimpleCellMain {

    public static void main(String[] args) {
        //SimpleCell c1 = new SimpleCell("C1");
        //SimpleCell c2 = new SimpleCell("C2");
        //SimpleCell c3 = new SimpleCell("C3");
        //c1.subscribe(c3);
        //c1.onNext(10);
        //c2.onNext(20);


        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);

    }
}
