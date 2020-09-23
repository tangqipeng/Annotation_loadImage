package com.example.loadimage;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.annotation.Repeatable;

/**
 * @author tangqipeng
 * @date 2020/9/23 3:07 PM
 * @email tangqipeng@aograph.com
 */
@Repeatable(BRoles.class)
public @interface BRole {
    String roleName();
}
