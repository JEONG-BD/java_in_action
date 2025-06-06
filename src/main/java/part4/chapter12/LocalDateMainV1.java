package part4.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class LocalDateMainV1 {
    public static void main(String[] args) {
        //printLocalDateInformation();
        //LocalDate today = LocalDate.now();
        //System.out.println("today = " + today);
        //getChronFieldInformation();
        //getLocalTimeInformation();
        localDateTimeInformation();
    }


    private static void printLocalDateInformation() {
        LocalDate date = LocalDate.of(2025, 06, 03);
        int year = date.getYear();
        System.out.println("year = " + year);
        Month month = date.getMonth();
        System.out.println("month = " + month);
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        int len = date.lengthOfMonth();
        System.out.println("len = " + len);
        boolean leapYear = date.isLeapYear();
        System.out.println("leapYear = " + leapYear);
    }

    private static void getChronFieldInformation(){
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day = " + day);
        System.out.println("month = " + month);
        System.out.println("year = " + year);
    }

    private static void getLocalTimeInformation(){
        LocalTime localTime = LocalTime.of(18, 20, 30, 21);
        System.out.println("localTime = " + localTime);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
    }

    private static void parseStringLocalDateTime(){
        LocalDate localDate = LocalDate.parse("2025-06-03");
        LocalTime localTime = LocalTime.parse("13:44:50");
    }

    public static void localDateTimeInformation(){
        LocalDateTime localDateTime = LocalDateTime.of(2025, 06, 05, 21, 33, 45, 50);
        System.out.println("localDateTime = " + localDateTime);
        LocalDate localDate = LocalDate.parse("2025-06-03");
        LocalTime localTime = LocalTime.parse("13:44:50");

        LocalDateTime localDateTime1 = LocalDateTime.of(localDate, localTime);
        localDate.atTime(13, 45, 20);
        localDate.atTime(localTime);
        localTime.atDate(localDate);
    }
}
