package com.example.loadimage.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

/**
 * @author tangqipeng
 * @date 2020/9/2 6:31 PM
 * @email tangqipeng@aograph.com
 */
public class BitmapDispather extends Thread {

    private BlockingQueue<BitmapRequest> requestBlockingQueue;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public BitmapDispather(BlockingQueue<BitmapRequest> requestBlockingQueue) {
        this.requestBlockingQueue = requestBlockingQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = requestBlockingQueue.take();
                showLoadingImage(request);
                Bitmap bitmap = findBitmap(request);
                showBitmap(bitmap, request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showBitmap(final Bitmap bitmap, final BitmapRequest request) {
        if (request.getImage() != null && bitmap != null && request.getImage().getTag().equals(request.getUrlMd5())){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    request.getImage().setImageBitmap(bitmap);
                }
            });
        }
    }

    private Bitmap findBitmap(BitmapRequest request) {
        Bitmap bitmap = downloadBitmap(request.getUrl());
        return bitmap;
    }

    private Bitmap downloadBitmap(String uri) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//父类变子类需要强转
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void showLoadingImage(BitmapRequest request) {
        if (request.getLoadingResId() > 0) {
            final ImageView imageView = request.getImage();
            final int resID = request.getLoadingResId();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resID);
                }
            });
        }
    }
}
