package com.micro_summer_whisper.flower_supplier.common.util;


import java.util.Date;

import cn.hutool.core.date.DateTime;

public class DateTimeUtils {

    public static final String DEFAULT_FORMAT_PATTERN = "yyyy/MM/dd hh:mm:ss";

    public static String toStrFromDate(Date date){
        return DateTime.of(date).toString("yyyy/MM/dd hh:mm:ss");
    }

    public static long toLongFromDate(Date date){
        return date.getTime();
    }

    public static Date toDateFromLong(long time){
        return new Date(time);
    }

    public static String toStrFromLong(long time){
        return toStrFromDate(toDateFromLong(time));
    }

    public static Date toDateFromStr(String timeStr){
        return DateTime.of(timeStr,"yyyy/MM/dd hh:mm:ss").toJdkDate();
    }

    public static long toLongFromStr(String timeStr){
        return toLongFromDate(toDateFromStr(timeStr));
    }


}
