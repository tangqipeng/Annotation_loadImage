package com.example.loadimage.factorymode;

import com.example.annotation.AnFactory;

/**
 * @author tangqipeng
 * @date 2020/9/23 4:34 PM
 * @email tangqipeng@aograph.com
 */
@AnFactory(id = "Circle", type = IShape.class)
public class Circle implements IShape {

    public Circle() {
    }

    public void draw(String s){
        System.out.println(s);
    }

    @Override
    public void draw() {
        System.out.println("Draw a Circle");
    }
}
