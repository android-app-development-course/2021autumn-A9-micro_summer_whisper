package com.micro_summer_whisper.flower_supplier.common.util;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.LocalDateTimeUtil;

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

    public static LocalDateTime toLocalDateTimeFromLong(long time){
        return LocalDateTimeUtil.of(time);
    }

    public static LocalDateTime toLocalDateTimeFromDate(Date date){
        return LocalDateTimeUtil.of(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Date toDateFromLocalDateTime(LocalDateTime localDateTime){
        Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Gson newGsonInstance() {
        //对localdate 和 localdatetime 的处理 做格式化处理（兼容老核心的string 类型）
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                    String datetime = json.getAsJsonPrimitive().getAsString();
                    return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }).registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                    String datetime = json.getAsJsonPrimitive().getAsString();
                    return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }).create();
    }




}
