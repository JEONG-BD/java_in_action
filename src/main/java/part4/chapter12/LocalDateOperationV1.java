package part4.chapter12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateOperationV1 {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2025, 06, 03);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);

        LocalDate date5 = date4.plusWeeks(1);
        LocalDate date6 = date5.minusYears(6);
        LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);

    }
}
