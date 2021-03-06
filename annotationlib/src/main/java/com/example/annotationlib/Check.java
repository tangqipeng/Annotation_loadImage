package com.example.annotationlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangqipeng
 * @date 2020/9/23 2:31 PM
 * @email tangqipeng@aograph.com
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Check {
}
