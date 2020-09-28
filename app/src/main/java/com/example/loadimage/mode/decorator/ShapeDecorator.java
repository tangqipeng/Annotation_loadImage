package com.example.loadimage.mode.decorator;

import com.example.loadimage.mode.IShape;

/**
 * @author tangqipeng
 * @date 2020/9/28 4:44 PM
 * @email tangqipeng@aograph.com
 */
public abstract class ShapeDecorator implements IShape {

    protected IShape shape;

    public ShapeDecorator(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
