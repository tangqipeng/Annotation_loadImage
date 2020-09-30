package com.example.annotationlib;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        try {//运行时更改注解
            Field field = TestBean.class.getDeclaredField("name");
            TestAnnotation testAnnotation = field.getAnnotation(TestAnnotation.class);

            Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) testAnnotation.annotClass();

            Annotation annotation = field.getAnnotation(annotationClass);

            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);

            Field memberValueField = invocationHandler.getClass().getDeclaredField("memberValues");

            memberValueField.setAccessible(true);

            Map memberValues = (Map) memberValueField.get(invocationHandler);

            for (Object value : memberValues.values()){
                System.out.println("Map value is ：" + value.toString());
            }

            Object testAnnotationClassField = memberValues.get(testAnnotation.annotClassField());

            System.out.println("注释名称1：" + testAnnotationClassField.toString());

            memberValues.put(testAnnotation.annotClassField(), "用户名");

            testAnnotationClassField = memberValues.get(testAnnotation.annotClassField());

            System.out.println("注释名称2：" + testAnnotationClassField.toString());


        }catch (Exception e){}
    }
}