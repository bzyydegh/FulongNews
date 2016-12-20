package cn.edu.gdaib.fulongnews.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdaib.fulongnews.R;

/**
 * RecyclerView的Item
 * Created by ZYongY
 * on 2016/11/26.
 */
public class ContentViewHolder extends RecyclerView.ViewHolder{
    public ImageView ivPicture;
    public TextView tvTitle;
    public TextView tvDate;

    public ContentViewHolder(View itemView) {
        super(itemView);
        ivPicture = (ImageView) itemView.findViewById(R.id.home_item_iv_picture);
        tvTitle = (TextView) itemView.findViewById(R.id.home_item_tv_title);
        tvDate = (TextView) itemView.findViewById(R.id.home_item_tv_date);
    }
}