package part3.chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoveIf {

    private final Trader trander;
    private final int year;
    private final int value;
    private final String referenceCode;

    public RemoveIf(Trader trander, int year, int value, String referenceCode) {
        this.trander = trander;
        this.year = year;
        this.value = value;
        this.referenceCode = referenceCode;
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

    public String getReferenceCode() {
        return referenceCode;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trander=" + trander +
                ", year=" + year +
                ", value=" + value +
                ", referenceCode='" + referenceCode + '\'' +
                '}';
    }


    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<RemoveIf> transactions = new ArrayList<>(Arrays.asList(
                new RemoveIf(brian, 2011, 300, "123A"),
                new RemoveIf(raoul, 2012, 1000, "A100"),
                new RemoveIf(raoul, 2011, 400, "2B20"),
                new RemoveIf(mario, 2012, 710, "M777"),
                new RemoveIf(alan, 2012, 950, "9XYZ")
        ));

        transactions.forEach(System.out::println);
        //errorRemve(transactions);
        //iteratorRemove(transactions);
        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
        //transactions.forEach(System.out::println);

    }

    private static void errorRemve(List<RemoveIf> transactions) {
        for (RemoveIf transaction : transactions) {
            if(Character.isDigit(transaction.getReferenceCode().charAt(0))){
                transactions.remove(transaction);
            }
        }
    }

    private static List<RemoveIf>  iteratorRemove(List<RemoveIf> transactions) {
        Iterator<RemoveIf> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            RemoveIf t = iterator.next();
            if (Character.isDigit(t.getReferenceCode().charAt(0))) {
                iterator.remove();
            }
        }
        return transactions;
    }
}
