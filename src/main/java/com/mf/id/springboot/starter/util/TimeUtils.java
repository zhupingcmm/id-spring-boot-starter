package com.mf.id.springboot.starter.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    public static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }
}
