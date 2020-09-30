package com.example.annotationlib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author tangqipeng
 * @date 2020/9/23 2:39 PM
 * @email tangqipeng@aograph.com
 */
public class Test {

    public String getStr(){
        return "你好";
    }

}
