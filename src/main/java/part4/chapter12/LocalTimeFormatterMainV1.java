package part4.chapter12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatterMainV1 {
    public static void main(String[] args) {
        formatterPrint();
        LocalDate date1 = LocalDate.parse("20250605", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("date1 = " + date1);
        formatterPattern();
        formatterPatternLocale();
    }

    private static void formatterPrint() {
        LocalDate date = LocalDate.of(2025, 6, 18);
        String s1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String s2 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s2 = " + s2);
        System.out.println("s1 = " + s1);
    }

    private static void formatterPattern(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.of(2025, 6, 5);
        String formattedDate = date.format(dateTimeFormatter);
        System.out.println("formattedDate = " + formattedDate);
    }

    private static void formatterPatternLocale(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date = LocalDate.of(2025, 6, 5);
        String formattedDate = date.format(dateTimeFormatter);
        System.out.println("formattedDate = " + formattedDate);
    }
}
