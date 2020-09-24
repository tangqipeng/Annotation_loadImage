package com.example.loadimage.factorymode;

import com.example.annotation.AnFactory;

/**
 * @author tangqipeng
 * @date 2020/9/23 4:19 PM
 * @email tangqipeng@aograph.com
 */
@AnFactory(id = "Rectangle", type = IShape.class)
public class Rectangle implements IShape {

    @Override
    public void draw() {
        System.out.println("Draw a Rectangle");
//        Log.i("IShape", "Draw a Rectangle");
    }
}
