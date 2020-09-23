package com.example.loadimage.glide;

import android.content.Context;

/**
 * @author tangqipeng
 * @date 2020/9/2 7:07 PM
 * @email tangqipeng@aograph.com
 */
public class MyGlide {

    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }

}
