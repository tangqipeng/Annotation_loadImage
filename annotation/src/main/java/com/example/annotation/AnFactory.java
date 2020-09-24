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
//只存在于源码阶段更好，这里编译后生成了IShapeAnFactory之后就不需要它存在了
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AnFactory {

    Class type();

    String id();

}
