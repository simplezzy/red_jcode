package com.redcode.demo.java8.time;

import java.time.*;

/**
 * Created by zhiyu.zhou on 2017/10/27.
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("CurrentDate:" + today);

        LocalDate specifyDay = LocalDate.of(2017, Month.DECEMBER,27);
        System.out.println("specifyDay:" + specifyDay);

        LocalDate todayKol = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Asia/Kolkata:" + todayKol);

        LocalTime time = LocalTime.now();
        System.out.println("time:" + time);

        Instant timestamp = Instant.now();
        System.out.println("timestamp:" + timestamp);
    }
}
