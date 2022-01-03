package com.example.floor_myshop.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;

/**
 * @author tong
 * @date 2021/12/24
 */
public class ControllerUtils {

    public static boolean checkALessThanB(Integer a, Integer b, Integer aBound){
        return a!=null&&b!=null&&a>=aBound&&b>a;
    }

    public static boolean checkALessThanBOnLocalDateTime(LocalDateTime a, LocalDateTime b){
        return a!=null&&b!=null&& DateTime.of(DateTimeUtils.toDateFromLocalDateTime(a)).isBefore(DateTimeUtils.toDateFromLocalDateTime(b));
    }

    public static <T> void trySetImg(T pv , String  p, BiConsumer<T, String> consumer){
        if (StringUtils.isNotBlank(p)){
            final File destF = new File(
                    UUID.randomUUID().toString(true) + ".png");
            final File file = Base64.decodeToFile(p,destF);
            QiNiuUtil.upload(file.getAbsolutePath(),file.getName(),true);
            file.delete();
            try {
                final String fileUrl = QiNiuUtil.fileUrl(file.getName());
                consumer.accept(pv,fileUrl);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
