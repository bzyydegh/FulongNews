package cn.edu.gdaib.fulongnews.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import cn.edu.gdaib.fulongnews.R;

/**
 * 底部加载更多
 * Created by ZYongY
 * on 2016/11/26.
 */
public class FooterViewHolder extends RecyclerView.ViewHolder{
    public ProgressWheel itemProgressBar;
    public TextView itemTextView;

    public FooterViewHolder(View itemView) {
        super(itemView);
        itemProgressBar = (ProgressWheel)itemView.findViewById(R.id.home_item_pb_more);
        itemTextView = (TextView)itemView.findViewById(R.id.home_item_tv_loading);
    }
}