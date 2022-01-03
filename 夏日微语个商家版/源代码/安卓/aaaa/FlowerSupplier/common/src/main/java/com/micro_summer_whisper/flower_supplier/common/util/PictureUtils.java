package com.micro_summer_whisper.flower_supplier.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class PictureUtils {
    /**
     * 将bitmap转换成base64字符串
     *
     * @param bitmap
     * @return base64 字符串
     */
    public static String bitmap2String(Bitmap bitmap, int bitmapQuality) {
        // 将Bitmap转换成字符串

        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, bStream);

        byte[] bytes = bStream.toByteArray();

        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return string;
    }

    /**

     * 将base64转换成bitmap图片

     *

     * @param string base64字符串

     * @return bitmap

     */

    public static Bitmap string2Bitmap(String string) {

        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
        byte[] bitmapArray;

        bitmapArray = Base64.decode(string, Base64.DEFAULT);

        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                bitmapArray.length);
        } catch (Exception e) {

          e.printStackTrace();

        }

        return bitmap;

    }


}
