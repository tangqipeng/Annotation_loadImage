package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangqipeng
 * @date 2020/9/23 4:33 PM
 * @email tangqipeng@aograph.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AnFactory {

    Class type();

    String id();

}
