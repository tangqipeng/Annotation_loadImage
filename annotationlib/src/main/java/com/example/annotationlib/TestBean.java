package com.example.annotationlib;

/**
 * @author tangqipeng
 * @date 2020/9/30 3:34 PM
 * @email tangqipeng@aograph.com
 */
public class TestBean {

    @PropertyAnno(name = "账户")
    @TestAnnotation(annotClass = PropertyAnno.class, annotClassField = "name")
    private String name;

}
