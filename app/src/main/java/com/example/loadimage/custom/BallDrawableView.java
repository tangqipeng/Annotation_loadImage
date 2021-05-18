package com.example.loadimage.custom;

import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.example.loadimage.BallInterpolator;

/**
 * @author tangqipeng
 * @date 2020/9/22 6:24 PM
 * @email tangqipeng@aograph.com
 */
public class BallDrawableView extends RelativeLayout implements View.OnClickListener {

    public static final int STROKE_WIDTH = 8;
    private int mScreenHeight;
    private ImageView mBallView;
    private Paint mPaint;
    private Canvas canvas;

    private int y;

    public BallDrawableView(Context context) {
        this(context, null);
    }

    public BallDrawableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStuff(context);
    }

    private void initStuff(Context context) {
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        int x = 300;
        y = 300;

        mBallView = new ImageView(context);
        LayoutParams params = new LayoutParams(x, y);
        mBallView.setLayoutParams(params);
        BallView mBallDrawable = new BallView(context);
        PointF ballPoint = new PointF(150, 150);
        mBallDrawable.setBallPoint(ballPoint);
        mBallView.setImageDrawable(mBallDrawable);

        addView(mBallView);

        mBallView.setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.canvas == null) {
            this.canvas = canvas;
        }
        //方便刷新透明度
        int alpha = 100;
        mPaint.setARGB(alpha, 0, 125, 251);

        float x = 0;
        float radius = 0;
        float y = 0;
        canvas.drawCircle(x, y, radius, mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        startAnimator();
    }


    private void startAnimator(){

        @SuppressLint("ObjectAnimatorBinding")
        ObjectAnimator animator = ObjectAnimator.ofObject(mBallView, "y", new IntEvaluator() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                y = (int) (startValue + (endValue - startValue) * fraction);
                return y;
            }
        }, 0, mScreenHeight - 300);
        animator.setDuration(1200);
        animator.setInterpolator(new BallInterpolator(0.3f));
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }

}
