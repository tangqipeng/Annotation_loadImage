package com.example.loadimage.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.loadimage.R;

/**
 * @author tangqipeng
 * @date 2020/9/3 6:23 PM
 * @email tangqipeng@aograph.com
 */
public class BallView extends Drawable {

    private static final float BAISIDIWEI_SIZE = 20;
    private static final float BAISIDIWEI_PINYIN_SIZE = 45;
    private static final float OFFLCIAL_SIZE = 14;
    private static final float BS_SIZE = 30;
    private final Context mContext;
    private float BALL_RADIUS = 150;
    private Paint paint;
    private Path path;
    private PointF ballPoint;
    private float x, y;
    private static final float BS_WIDTH = 55;
    private static final float BS_HIGHT = 30;

    public BallView(Context context) {
        this.mContext = context;
        getScreenParams(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();
//        ballPoint = new PointF(mScreenWidth / 2, mScreenHeight / 2);
        ballPoint = new PointF(BALL_RADIUS, BALL_RADIUS);
    }


    public BallView(Context context, float x1, float y1) {
        this.mContext = context;
        this.x = x1;
        this.y = y1;
        getScreenParams(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();
//        ballPoint = new PointF(mScreenWidth / 2, mScreenHeight / 2);
        ballPoint = new PointF(x, y);
    }

    public void setBallPoint(float x, float y) {
        this.ballPoint = new PointF(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public PointF getBallPoint() {
        return ballPoint;
    }

    public float getBallRadius() {
        return BALL_RADIUS;
    }

    /**
     * 获取屏幕宽高
     */
    public void getScreenParams(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(mDisplayMetrics);
        int mScreenWidth = mDisplayMetrics.widthPixels;
        int mScreenHeight = mDisplayMetrics.heightPixels;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawball(Canvas canvas, PointF ballPoint) {
        paint.setColor(mContext.getResources().getColor(R.color.turkish_rose));
        canvas.drawCircle(ballPoint.x, ballPoint.y, BALL_RADIUS, paint);

        canvas.save();
        paint.setColor(mContext.getResources().getColor(R.color.au_chico));
        paint.setStrokeWidth(2);
        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(0,0, paint);
//        float[] pts = new float[(int) (BALL_RADIUS)];
        for (int a = 0; a < 360; a++) {
            for (int i = 0; i < 2 * BALL_RADIUS; i += 2) {
                if (Math.pow(i, 2) * 2 < Math.pow(BALL_RADIUS, 2)) {
                    canvas.drawPoint(ballPoint.x - i, ballPoint.y - i, paint);
                }
            }
            canvas.rotate(1, ballPoint.x, ballPoint.y);
        }
//        float[] pts={ballPoint.x - ,50, 400,50, 400,600, 60,600,};
//        canvas.drawPoints(pts, paint);


        canvas.restore();

        paint.setColor(mContext.getResources().getColor(R.color.mobster));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        path.moveTo(ballPoint.x - BALL_RADIUS + 2, ballPoint.y - 15);
        path.quadTo(ballPoint.x - BALL_RADIUS + 12, ballPoint.y - 15, ballPoint.x - BALL_RADIUS + 22, ballPoint.y - 26);
        canvas.drawPath(path, paint);

        path.reset();
        paint.setStrokeWidth(8);
        path.moveTo(ballPoint.x - BALL_RADIUS + 22, ballPoint.y - 26);
//        path.quadTo(ballPoint.x, ballPoint.y - BALL_RADIUS - BALL_RADIUS / 10, ballPoint.x + BALL_RADIUS + 1, ballPoint.y - 20);
        path.quadTo(ballPoint.x, ballPoint.y - BALL_RADIUS - BALL_RADIUS / 10, ballPoint.x + BALL_RADIUS - 21, ballPoint.y - 26);
        drawPaths(path, canvas, 0.4f);

        path.reset();
        paint.setStrokeWidth(4);
        path.moveTo(ballPoint.x + BALL_RADIUS, ballPoint.y - 15);
        path.quadTo(ballPoint.x + BALL_RADIUS - 11, ballPoint.y - 15, ballPoint.x + BALL_RADIUS - 21, ballPoint.y - 26);
        canvas.drawPath(path, paint);

        path.reset();
        paint.setStrokeWidth(8);
        path.moveTo(ballPoint.x - BALL_RADIUS, ballPoint.y);
        path.quadTo(ballPoint.x, ballPoint.y + BALL_RADIUS / 2, ballPoint.x + BALL_RADIUS, ballPoint.y);
//        canvas.drawPath(path, paint);
        drawPaths(path, canvas, 0.3f);

        path.reset();
        paint.setStrokeWidth(4);
        path.moveTo(ballPoint.x - BALL_RADIUS + 2, ballPoint.y + 20);
        path.quadTo(ballPoint.x - BALL_RADIUS + 8, ballPoint.y + 22, ballPoint.x - BALL_RADIUS + 15, ballPoint.y + 30);
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(ballPoint.x - BALL_RADIUS + 15, ballPoint.y + 30);
        path.cubicTo(ballPoint.x - BALL_RADIUS / 2, ballPoint.y + BALL_RADIUS + 16, ballPoint.x + BALL_RADIUS / 2, ballPoint.y + BALL_RADIUS + 16, ballPoint.x + BALL_RADIUS - 15, ballPoint.y + 30);
//        canvas.drawPath(path, paint);
        drawPaths(path, canvas, 0.3f);

        path.reset();
        paint.setStrokeWidth(4);
        path.moveTo(ballPoint.x + BALL_RADIUS - 2, ballPoint.y + 20);
        path.cubicTo(ballPoint.x + BALL_RADIUS - 8, ballPoint.y + 22, ballPoint.x + BALL_RADIUS - 15, ballPoint.y + 30, ballPoint.x + BALL_RADIUS - 19, ballPoint.y + 37);
        canvas.drawPath(path, paint);

        paint.setColor(mContext.getResources().getColor(R.color.saltpan));
        paint.setStrokeWidth(2);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextSize(BAISIDIWEI_SIZE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("百 斯 迪 威", ballPoint.x, ballPoint.y - 50, paint);
        paint.setStrokeWidth(2);
        paint.setTextSize(BAISIDIWEI_PINYIN_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("Baisidiwei", ballPoint.x, ballPoint.y - 4, paint);
        paint.setStrokeWidth(2);
        paint.setTextSize(OFFLCIAL_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        canvas.drawText("offlcial size and weight", ballPoint.x, ballPoint.y + 15, paint);


        paint.setColor(mContext.getResources().getColor(R.color.mobster));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setTextSize(BAISIDIWEI_SIZE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("百 斯 迪 威", ballPoint.x, ballPoint.y - 50, paint);
        paint.setTextSize(BAISIDIWEI_PINYIN_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        canvas.drawText("Baisidiwei", ballPoint.x, ballPoint.y - 4, paint);
        paint.setColor(mContext.getResources().getColor(R.color.hemp));
        paint.setTextSize(OFFLCIAL_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        canvas.drawText("offlcial size and weight", ballPoint.x, ballPoint.y + 15, paint);

        canvas.save();

        float leftX = ballPoint.x - 85;
        float rightX = leftX + BS_WIDTH;
        float topY = ballPoint.y + 55;
        float bottomY = topY + BS_HIGHT;

        RectF bsRectf = new RectF(leftX, topY, rightX, bottomY);

        Log.i("KKKK", "bsRectf.left is " + bsRectf.left);
        canvas.rotate(20);
        float dx = (float) (bsRectf.left - (bsRectf.left * Math.cos(Math.toRadians(20)) - bsRectf.top * Math.sin(Math.toRadians(20))));
        float dy = (float) (2 * (bsRectf.top - bsRectf.top * Math.cos(Math.toRadians(20)) - bsRectf.left * Math.sin(Math.toRadians(20))));
        Log.i("KKKK", "dy is " + dy);
        canvas.translate(dx - BALL_RADIUS / 2 + BS_WIDTH, dy - BS_HIGHT );

        paint.setColor(mContext.getResources().getColor(R.color.saltpan));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(bsRectf, 0, 0, paint);

        paint.setColor(mContext.getResources().getColor(R.color.mobster));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(bsRectf, 0, 0, paint);


        paint.setColor(mContext.getResources().getColor(R.color.saltpan));
        paint.setTextSize(BS_SIZE);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        canvas.drawText("BS", ballPoint.x - 58, ballPoint.y + 80, paint);
        canvas.restore();
        path.reset();
        path.moveTo(ballPoint.x - 50, ballPoint.y + 90);
        path.cubicTo(ballPoint.x + 30, ballPoint.y + 95, ballPoint.x + 60, ballPoint.y + 80, ballPoint.x + 120, ballPoint.y + 60);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawTextOnPath("S U P E R", path, 5, 0, paint);

    }

    private static final float DEFAULT_SEGMENT_LENGTH = 6F;
    private static final float DEFAULT_WIDTH = 4F;
    private static final float MAX_WIDTH = 45F;

    private void drawPaths(Path path, Canvas canvas, float rate) {
        PathMeasure pm = new PathMeasure(path, false);
        float length = pm.getLength();
        int segmentSize = (int) Math.ceil(length / DEFAULT_SEGMENT_LENGTH);
        Path pt;
        for (int i = 0; i < segmentSize; i++) {
            pt = new Path();
            pm.getSegment((i - 1) * DEFAULT_SEGMENT_LENGTH - 0.4f, Math.min(i * DEFAULT_SEGMENT_LENGTH, length), pt, true);
            if (i <= segmentSize / 2) {
                paint.setStrokeWidth((float) Math.min(MAX_WIDTH, i * rate + DEFAULT_WIDTH));
            } else {
                paint.setStrokeWidth((float) Math.min(MAX_WIDTH, (segmentSize - i) * rate + DEFAULT_WIDTH));
            }
            canvas.drawPath(pt, paint);
        }
    }

    private void drawPathsDiminish(Path path, Canvas canvas, float rate) {
        PathMeasure pm = new PathMeasure(path, false);
        float length = pm.getLength();
        int segmentSize = (int) Math.ceil(length / DEFAULT_SEGMENT_LENGTH);
        Path pt;
        for (int i = 0; i < segmentSize; i++) {
            pt = new Path();
            pm.getSegment((i - 1) * DEFAULT_SEGMENT_LENGTH - 0.4f, Math.min(i * DEFAULT_SEGMENT_LENGTH, length), pt, true);
            paint.setStrokeWidth((float) Math.min(MAX_WIDTH, (segmentSize - i) * rate + DEFAULT_WIDTH));
            canvas.drawPath(pt, paint);
        }
    }

    private void drawPathsLargen(Path path, Canvas canvas, float rate) {
        PathMeasure pm = new PathMeasure(path, false);
        float length = pm.getLength();
        int segmentSize = (int) Math.ceil(length / DEFAULT_SEGMENT_LENGTH);
        Path pt;
        for (int i = 0; i < segmentSize; i++) {
            pt = new Path();
            pm.getSegment((i - 1) * DEFAULT_SEGMENT_LENGTH - 0.4f, Math.min(i * DEFAULT_SEGMENT_LENGTH, length), pt, true);
            paint.setStrokeWidth((float) Math.min(MAX_WIDTH, i * rate + DEFAULT_WIDTH));
            canvas.drawPath(pt, paint);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(@NonNull Canvas canvas) {
//        canvas.saveLayerAlpha(0, 0, canvas.getWidth(), canvas.getHeight(), 240, Canvas.ALL_SAVE_FLAG);
        drawball(canvas, ballPoint);
//        canvas.restore();
//        path.reset();
//        paint.setColor(Color.argb(160, 244, 92, 71));
    }

    @Override
    public void setAlpha(int i) {
        paint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    /**
     * 高度要容得下两个鱼身长度
     * 8.36计算过程 身长6.79减去头顶到中部位置的长度2.6 再乘以2
     *
     * @return
     */
    @Override
    public int getIntrinsicHeight() {
        return (int) (2f * BALL_RADIUS);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (2f * BALL_RADIUS);
    }
}
