package part4.chapter12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class DurationMainV1 {
    public static void main(String[] args) {

        durationLocalTime();
        periodLocalDate();
    }

    private static void durationLocalTime() {
        LocalTime time1 = LocalTime.of(13, 00, 00);
        LocalTime time2 = LocalTime.of(11, 30, 00);
        Duration duration = Duration.between(time1, time2);
        System.out.println(duration);
    }

    private static void periodLocalDate(){
        Period period = Period.between(
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2026, 1, 1));
        System.out.println(period);
    }
}
