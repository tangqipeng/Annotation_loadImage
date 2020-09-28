package com.example.loadimage.mode;

import com.example.annotation.AnFactory;

/**
 * @author tangqipeng
 * @date 2020/9/23 4:20 PM
 * @email tangqipeng@aograph.com
 */
@AnFactory(id = "Triangle", type = IShape.class)
public class Triangle implements IShape {

    @Override
    public void draw() {
        System.out.println("Draw a Triangle");
//        Log.i("IShape", "Draw a Triangle");
    }
}
