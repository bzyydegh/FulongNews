package cn.edu.gdaib.fulongnews.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.edu.gdaib.fulongnews.R;

/**
 * 底部提示没有更多数据了
 * Created by ZYongY
 * on 2016/11/26.
 */
public class FooterTipViewHolder extends RecyclerView.ViewHolder{
    public TextView itemTextView;

    public FooterTipViewHolder(View itemView) {
        super(itemView);
        itemTextView = (TextView)itemView.findViewById(R.id.home_item_tv_no_data);
    }
}