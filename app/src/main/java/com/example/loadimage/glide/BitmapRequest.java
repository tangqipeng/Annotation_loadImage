package com.example.loadimage.glide;

import android.content.Context;
import android.widget.ImageView;

import com.example.loadimage.Utils;

import java.lang.ref.SoftReference;

/**
 * @author tangqipeng
 * @date 2020/9/2 6:17 PM
 * @email tangqipeng@aograph.com
 */
public class BitmapRequest {

    private Context context;
    private RequestListener listener;
    private String url;
    private String urlMd5;
    private int loadingResId;
    private SoftReference<ImageView> image;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest loading(int loadingResId){
        this.loadingResId = loadingResId;
        return this;
    }

    public BitmapRequest load(String url){
        this.url = url;
        this.urlMd5 = Utils.md5(url);
        return this;
    }

    public BitmapRequest listener(RequestListener listener){
        this.listener = listener;
        return this;
    }

    public void into(ImageView imageView){
        this.image = new SoftReference<>(imageView);
        imageView.setTag(this.urlMd5);
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public String getUrl() {
        return url;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public int getLoadingResId() {
        return loadingResId;
    }

    public ImageView getImage() {
        return image.get();
    }
}
