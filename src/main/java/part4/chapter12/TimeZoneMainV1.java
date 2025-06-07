package part4.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class TimeZoneMainV1 {
    public static void main(String[] args) {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println("zoneId = " + zoneId);
        System.out.println("romeZone = " + romeZone);

        LocalDate date = LocalDate.of(2025, 6, 5);
        ZonedDateTime zonedDateTime = date.atStartOfDay(romeZone);
        LocalDateTime localDateTime = LocalDateTime.of(2025, 6, 5, 13, 11, 45);
        System.out.println("zonedDateTime = " + zonedDateTime);
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(romeZone);
        System.out.println("zonedDateTime1 = " + zonedDateTime1);
    }
}
