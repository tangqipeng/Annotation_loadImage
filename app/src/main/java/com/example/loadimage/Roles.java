package com.example.loadimage;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.annotation.Repeatable;

/**
 * @author tangqipeng
 * @date 2020/9/23 3:02 PM
 * @email tangqipeng@aograph.com
 */
public @interface Roles {
    Role[] roles();
}
