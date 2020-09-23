package com.example.loadimage.custom;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

/**
 * @author tangqipeng
 * @date 2020/9/22 6:24 PM
 * @email tangqipeng@aograph.com
 */
public class BallDrawableView extends RelativeLayout implements View.OnClickListener {

    public static final int STROKE_WIDTH = 8;
    public static final float DEFAULT_RADIUS = 150;
    private int mScreenWidth;
    private int mScreenHeight;
    private ImageView mBallView;
    private BallView mBallDrawable;
    private Paint mPaint;
    private int alpha = 100;
    private Canvas canvas;
    private float x = 0;
    private float y = 0;
    private float radius = 0;
    private PointF ballPoint;
//    private ObjectAnimator animator;

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
        getScreenParams();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(STROKE_WIDTH);

        TextView textView = new TextView(context);
        textView.setText("hahahahha");
        addView(textView);

        mBallView = new ImageView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.width = 300;
//        params.height = 300;
        mBallView.setLayoutParams(params);
        mBallDrawable = new BallView(context);
//        mBallDrawable.setBallPoint(200, 200);
        mBallView.setImageDrawable(mBallDrawable);

        addView(mBallView);

        ballPoint = mBallDrawable.getBallPoint();

        mBallView.setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.canvas == null) {
            this.canvas = canvas;
        }
        //方便刷新透明度
        mPaint.setARGB(alpha, 0, 125, 251);

        canvas.drawCircle(x, y, radius, mPaint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Log.i("HHHH", ballPoint.x + ","+ ballPoint.y);
        PointF endPoint = new PointF(ballPoint.x, mScreenHeight - 80 - 3*mBallDrawable.getBallRadius());
        startAnimator(ballPoint, endPoint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnimator(PointF startPoint, PointF endPoint){
        ValueAnimator animator = ValueAnimator.ofObject(new FloatEvaluator(), startPoint.y, endPoint.y);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                mBallView.animateTransform(); = (PointF)animation.getAnimatedValue();
                float y = (float) animation.getAnimatedValue();
                Log.i("HHHH", ballPoint.x + ","+ ballPoint.y);
                long time = animation.getCurrentPlayTime();
                mBallView.setY(y);
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

    }


    /**
     * 获取屏幕宽高
     */
    public void getScreenParams() {
        WindowManager WM = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(mDisplayMetrics);
        mScreenWidth = mDisplayMetrics.widthPixels;
        mScreenHeight = mDisplayMetrics.heightPixels;
    }

}
