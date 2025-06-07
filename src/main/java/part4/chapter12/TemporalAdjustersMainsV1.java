package part4.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class TemporalAdjustersMainsV1 {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.of(2025, 06, 04);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date1.with(TemporalAdjusters.lastDayOfMonth());
    }

    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

}



