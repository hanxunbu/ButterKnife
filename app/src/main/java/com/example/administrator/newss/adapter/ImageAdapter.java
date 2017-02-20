package com.example.administrator.newss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.newss.R;
import com.example.administrator.newss.entity.ImageInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder>{
    private List<ImageInfo> list = new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public List<ImageInfo> getList() {
        return list;
    }

    public void setList(List<ImageInfo> list) {
        this.list = list;
    }
    /**创建一个ViewHolder*/
    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_image_item,null);
        return new ImageHolder(view);
    }
    /**绑定ViewHolder*/
    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        //给控件赋值
        Picasso.with(context)
                .load(list.get(position).getUrl())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv);
    }
    /**返回RecyclerView的项数*/
    @Override
    public int getItemCount() {
        return list.size();
    }
    /**ViewHolder*/
    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ImageHolder(View itemView) {
            super(itemView);
            //找到管理的控件
            iv = (ImageView) itemView.findViewById(R.id.adapter_image_item_iv);
        }
    }
}
