package com.example.loadimage.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loadimage.R;
import com.example.loadimage.TestActivity;
import com.example.loadimage.Utils;
import com.example.loadimage.glide.MyGlide;

import java.util.List;

/**
 * @author tangqipeng
 * @date 2020/9/2 7:35 PM
 * @email tangqipeng@aograph.com
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mUrls;
    private LayoutInflater inflater;

    public ImageAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setUrls(List<String> mUrls) {
        this.mUrls = mUrls;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = Utils.getScreenWidth(mContext);
//        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = Utils.getScreenWidth(mContext) * 2 / 3;
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;


        MyGlide.with(mContext).loading(R.mipmap.meinv_2).load(mUrls.get(position)).into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, TestActivity.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mUrls == null ? 0 : mUrls.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
