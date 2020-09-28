package com.example.loadimage.mode.decorator;

import android.util.Log;

import com.example.loadimage.mode.IShape;

/**
 * @author tangqipeng
 * @date 2020/9/28 4:45 PM
 * @email tangqipeng@aograph.com
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(IShape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        setRedBorder(shape);
    }

    private void setRedBorder(IShape decoratedShape){
        Log.i("RedShapeDecorator", "Border Color: Red");
    }
}
