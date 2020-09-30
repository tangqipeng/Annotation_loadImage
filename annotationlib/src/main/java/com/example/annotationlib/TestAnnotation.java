package com.example.annotationlib;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangqipeng
 * @date 2020/9/30 3:31 PM
 * @email tangqipeng@aograph.com
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    Class<?> annotClass() default Void.class;

    String annotClassField() default "";
}
