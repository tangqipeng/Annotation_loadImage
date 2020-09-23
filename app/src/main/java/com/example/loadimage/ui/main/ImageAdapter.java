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

/**
 * @author tangqipeng
 * @date 2020/9/2 7:35 PM
 * @email tangqipeng@aograph.com
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private String[] urls = {"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3003960322,3342785987&fm=26&gp=0.jpg",
    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=294837591,2117133976&fm=26&gp=0.jpg",
    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3811847585,1117697310&fm=26&gp=0.jpg",
    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=203392229,2792120288&fm=26&gp=0.jpg"};
    private Context mContext;
    private LayoutInflater inflater;

    public ImageAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = Utils.getScreenWidth(mContext);
//        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = Utils.getScreenWidth(mContext) * 2 / 3;
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;


        MyGlide.with(mContext).loading(R.mipmap.meinv_2).load(urls[position]).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TestActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageview);
        }
    }
}
