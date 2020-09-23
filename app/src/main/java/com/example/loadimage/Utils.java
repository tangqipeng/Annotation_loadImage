package com.example.loadimage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import java.security.MessageDigest;

/**
 * @author tangqipeng
 * @date 2020/9/2 7:03 PM
 * @email tangqipeng@aograph.com
 */
public class Utils {

    /**
     * md5加密
     *
     * @param str String
     * @return static
     */
    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                if (Integer.toHexString(0xFF & b).length() == 1) {
                    sb.append("0").append(
                            Integer.toHexString(0xFF & b));
                } else {
                    sb.append(Integer.toHexString(0xFF & b));
                }
            }
            return sb.toString();
        } catch (Exception e) {
            Log.e("UTIL", "md5 error is " + e.getMessage());
        }

        return str;
    }

    public static int getScreenWidth(Context context){
        int width;
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            width = manager.getCurrentWindowMetrics().getBounds().width();
        } else {
            width = manager.getDefaultDisplay().getWidth();
        }
        return width;
    }


    public static int getScreenWidth1(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
