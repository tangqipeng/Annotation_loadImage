package com.example.loadimage;

import android.view.animation.Interpolator;

/**
 * @author tangqipeng
 * @date 3/25/21 5:28 PM
 * @email tangqipeng@aograph.com
 */
public class BallInterpolator implements Interpolator {

    private float factor;

    public BallInterpolator(float factor) {
        this.factor = factor;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
