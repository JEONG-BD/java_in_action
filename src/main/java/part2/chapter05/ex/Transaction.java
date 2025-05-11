package part2.chapter05.ex;

public class Transaction {

    private final Trader trander;
    private final int year;
    private final int value;

    public Transaction(Trader trander, int year, int value) {
        this.trander = trander;
        this.year = year;
        this.value = value;
    }

    public Trader getTrander() {
        return trander;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trander=" + trander +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
