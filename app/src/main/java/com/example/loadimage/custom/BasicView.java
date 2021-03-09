package com.example.loadimage.custom;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.loadimage.ImageShowActivity;

/**
 * @author tangqipeng
 * @date 2020/9/3 3:47 PM
 * @email tangqipeng@aograph.com
 */
public class BasicView extends View implements ValueAnimator.AnimatorUpdateListener {
    private static final String TAG = BasicView.class.getName();

    private final Paint paint;
    private int mCanvasWidth;
    private int mCanvasHeight;
    private Rect mRect;
    private final Path path;
    private float percent = 0.1f;
    private PointF oldView = null;
    private PointF view = null;
    private PointF pointF = null;
    private PointF pointB = null;

    public BasicView(Context context) {
        this(context, null);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        path = new Path();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                start(pointF, pointB);
//                Intent intent = new Intent(context, ImageShowActivity.class);
//                context.startActivity(intent);
            }
        });

        pointF = new PointF(10f, 500f);
        pointB = new PointF(500f, 900f);
    }
//    public static final int TRANS_X = 600;

    @Override
    protected void onDraw(Canvas canvas) {
//        mCanvasWidth = getWidth();
//        mCanvasHeight = getHeight();
        canvas.drawARGB(255, 139, 197, 186);
        drawAxis(canvas);

        drawRect(canvas);

//        float[] pts={50,50,
//                400,50,
//                400,600,
//                60,600, };
//        canvas.drawColor(Color.WHITE);                  //白色背景
//        paint.setStrokeWidth((float) 20.0);
//        canvas.drawPoints(pts, paint);

        paint.setColor(Color.RED);
        canvas.drawLine(pointF.x, pointF.y, pointB.x, pointB.y, paint);

//        float f = (float) Math.sqrt(Math.pow(Math.abs(pointF.x - pointB.x),2) + Math.pow(Math.abs(pointF.y - pointB.y),2));
//        animate(pointF, pointB);

//        oldView = pointF;
//        view = pointF;
        paint.setColor(Color.BLUE);
        canvas.drawLine(oldView.x, oldView.y, view.x, view.y, paint);
        Log.i("LLLL", "onDraw");
        Log.i("LLLL", "oldView is "+oldView);
        Log.i("LLLL", "view is "+view);

    }

    private void start(PointF pointF, PointF pointB){
        ValueAnimator animator = new ValueAnimator();
//        float offsetX = view.x;
//        float offsetY = view.y;
        animator.setDuration(2000);
// 和ofFloat类似，设置属性的起始值和结束值。
        animator.setObjectValues(pointF, pointB);
// 设置自定义的Evaluator.
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            // api level 21以上已经实现了PointFEvalutor.
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
//                float d = fraction * TRANS_X;
                pointF.x = endValue.x + endValue.x * percent;
                pointF.y = endValue.y + endValue.y * percent;

                Log.i("LLLL", "pointF is "+pointF);
                Log.i("LLLL", "fraction is "+fraction);
                Log.i("LLLL", "startValue is "+startValue);
                Log.i("LLLL", "endValue is "+endValue);
                oldView = endValue;

                return pointF;
            }
        });
// 通过监听器得到相应的Evaluator值，并应用。
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                view.set(pointF.x, pointF.y);
                invalidate();
//                postInvalidate();
                Log.i("LLLL", "view is "+view);
//                canvas.drawLine(oldView.x, oldView.y, view.x, view.y, paint);
//                requestLayout();
            }
        });
        animator.start();
    }

    //通过属性动画来画出帧动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animate(PointF startF, PointF endf) {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(percent, f);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointFEvaluator(), startF, endf);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(this);
//        valueAnimator.setEvaluator(new FloatEvaluator(){
//            @Override
//            public Float evaluate(float fraction, Number startValue, Number endValue) {
//                float startFloat = startValue.floatValue();
//                Log.i("LLLL", "startFloat is "+startFloat);
//                return startFloat + fraction * (endValue.floatValue() - startFloat);
//            }
//        });
        valueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        percent= (float) animation.getAnimatedValue();
//        setTextColor(Color.argb((int) (percent * 255), 255, 255, 255));
        Log.i("LLLL", "percent is "+percent);
        invalidate();
    }

    private void drawRect(Canvas canvas) {
        canvas.restore();
        canvas.save();
        paint.setStrokeWidth(5);
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.translate(10, 10);
        canvas.drawRect(0,0, mCanvasWidth /4, mCanvasWidth/8, paint);

        paint.setColor(0xffff00ff);
        canvas.translate(mCanvasWidth /4 +100, 0);
        mRect = new Rect(0,0, mCanvasWidth /4, mCanvasWidth/8);
        canvas.drawRect(mRect, paint);

        paint.setColor(Color.GRAY);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(80);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(0, mCanvasWidth/8/4);
        canvas.drawText("跳转", mCanvasWidth /4/2, mCanvasWidth/8/2, paint);

        paint.setColor(0xff0000ff);
        canvas.translate(mCanvasWidth /4 +100, -mCanvasWidth/8/4);
        canvas.drawRect(0,0, mCanvasWidth /4, mCanvasWidth/8, paint);
        canvas.restore();

        paint.setColor(0xff888888);
        path.moveTo(10, 200);
//        path.quadTo(200, 300, 300, 500);
        path.cubicTo(200, 300, 300, 500, 400, 400);
        canvas.drawPath(path, paint);


    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    private void drawAxis(Canvas canvas) {
        Log.i(TAG, "canvasWidth is " + mCanvasWidth + " , canvasHeight is " + mCanvasHeight);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

        paint.setColor(0xffff0000);
        canvas.drawLine(mCanvasWidth / 3, mCanvasHeight * 2 / 3, mCanvasWidth * 2 / 3, mCanvasHeight * 2 / 3, paint);//x轴
        paint.setColor(0xff00ff00);
        canvas.drawLine(mCanvasWidth / 3, mCanvasHeight * 2 / 3, mCanvasWidth / 3, mCanvasHeight / 3, paint);//y轴
        paint.setColor(0xff0000ff);
        canvas.drawLine(mCanvasWidth / 3, mCanvasHeight * 2 / 3, (float) (mCanvasWidth / 3 + mCanvasWidth / 3 / 2 * Math.sqrt(3)), mCanvasHeight * 2 / 3 - mCanvasHeight / 3 / 2, paint);//z轴
        canvas.save();

//        paint.setColor(0xffff00ff);
//        canvas.rotate(60);
//        canvas.drawLine(mCanvasWidth / 3, mCanvasHeight * 2 / 3, mCanvasWidth * 2 / 3, mCanvasHeight * 2 / 3, paint);//z轴
//        canvas.restore();

        paint.setColor(0xff0000ff);
        canvas.drawLine(0, 0, mCanvasWidth, 0, paint);//x轴

        paint.setColor(0xff00ff00);
        canvas.drawLine(0, 0, 0, mCanvasHeight, paint);//y轴

        canvas.translate(mCanvasWidth / 4, mCanvasWidth / 4);
        paint.setColor(0xffff0000);
        canvas.drawLine(0, 0, mCanvasWidth, 0, paint);
        paint.setColor(0xffff00ff);
        canvas.drawLine(0, 0, 0, mCanvasHeight, paint);

        canvas.translate(mCanvasWidth / 4, mCanvasWidth / 4);
        canvas.rotate(30);
        paint.setColor(0xffffff00);
        canvas.drawLine(0, 0, mCanvasWidth, 0, paint);
        paint.setColor(0xff00ffff);
        canvas.drawLine(0, 0, 0, mCanvasHeight, paint);
        drawPoint(canvas);
        drawtext(canvas);
    }

    private void drawPoint(Canvas canvas){
        canvas.rotate(-30);
        paint.setColor(0xffffff00);
        paint.setStrokeWidth(50);
        canvas.translate(0, -200);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(0,0, paint);

        canvas.translate(0, 100);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(0,0, paint);

        canvas.translate(0, 100);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(0,0, paint);

    }

    private void drawtext(Canvas canvas){
        int halfCanvasWidth = mCanvasWidth / 2;
        canvas.restore();
        canvas.save();
        paint.setStrokeWidth(5);
        paint.setColor(0xff88ff88);
        paint.setTextSize(50);
        canvas.translate(halfCanvasWidth, 200);
        canvas.drawText("你好", 0, 0, paint);

        //设置左对齐
        paint.setTextAlign(Paint.Align.LEFT);//设置左对齐
        canvas.translate(0, 200);
        canvas.drawText("左对齐文本", 0, 0, paint);

        //设置左对齐
        paint.setTextAlign(Paint.Align.CENTER);//设置居中对齐
        canvas.translate(0, 200);
        canvas.drawText("设置居中对齐", 0, 0, paint);

        paint.setTextAlign(Paint.Align.RIGHT);//设置右对齐
        canvas.translate(0, 200);
        canvas.drawText("设置右对齐", 0, 0, paint);

        paint.setFakeBoldText(true);
        canvas.translate(0, 200);
        canvas.drawText("设置加粗", 0, 0, paint);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointx = event.getX();
        float pointy = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "pointx is "+pointx +", pointy is "+pointy);
                Log.i(TAG, "mRect.left is "+mRect.left +", mRect.right is "+mRect.right);
                Log.i(TAG, "mRect.top is "+mRect.top +", mRect.bottom is "+mRect.bottom);
                Log.i(TAG, "mRect.width is "+mRect.width() +", mRect.height is "+mRect.height());
                Log.i(TAG, "mRect.centerX is "+mRect.centerX() +", mRect.centerY is "+mRect.centerY());
                Log.i(TAG, "mRect.centerX is "+mRect.centerX() +", mRect.centerY is "+mRect.centerY());
                break;
            case MotionEvent.ACTION_UP:
                start(pointF, pointB);
                break;
        }
        return super.onTouchEvent(event);
    }

}
