package cn.edu.gdaib.fulongnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.bean.PeInfoBean;
import cn.edu.gdaib.fulongnews.common.ContentViewHolder;
import cn.edu.gdaib.fulongnews.common.FooterTipViewHolder;
import cn.edu.gdaib.fulongnews.common.FooterViewHolder;
import cn.edu.gdaib.fulongnews.common.RecyclerItemClickListener;
import cn.edu.gdaib.fulongnews.utils.Constant;

/**
 * RecyclerView适配器
 * Created by ZYongY
 * on 2016/11/08.
 */

public class PeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
                        implements View.OnClickListener{

    private RecyclerItemClickListener recyclerItemClickListener = null; //rv监听接口
    private Context context;
    private LayoutInflater inflater;
    private List<PeInfoBean> mPeData;
    private int count = 10;

    public PeRecyclerAdapter(Context context, List<PeInfoBean> mPeData) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mPeData = mPeData;
    }

    @Override
    public int getItemCount() {
        if (mPeData.size() == 0){
            return 0;
        }else{
            return count + 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Constant.TYPE_CONTENT == viewType) {
            View itemView = inflater.inflate(R.layout.item_rv_content_home, parent, false);
            itemView.setOnClickListener(this);
            return new ContentViewHolder(itemView);
        }else if (Constant.TYPE_FOOTER == viewType){
            View itemView = inflater.inflate(R.layout.item_rv_foot_home, parent, false);
            return new FooterViewHolder(itemView);
        }else if (Constant.TYPE_FOOTER_TIP == viewType){
            View itemView = inflater.inflate(R.layout.item_rv_foot_tip_home, parent, false);
            return new FooterTipViewHolder(itemView);
        }else {
            Log.i("onCreateViewHolder-pe", "ViewHolder=null");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ContentViewHolder myHolder = (ContentViewHolder) holder;
            Glide.with(context)
                    .load(mPeData.get(0).getResult().get(position).getPicUrl()) //图片来源地址
                    .placeholder(R.drawable.default_picture) //加载中的图片
                    .crossFade() //淡入淡出效果
                    .into(myHolder.ivPicture); //显示到相应的ImageView
            myHolder.tvTitle.setText(mPeData.get(0).getResult().get(position).getTitle());
            myHolder.tvDate.setText(mPeData.get(0).getResult().get(position).getCtime());
            myHolder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount() &&
                position + 1 < mPeData.get(0).getResult().size()) {
            return Constant.TYPE_FOOTER;
        }else if (position + 1 == getItemCount()){
            Log.i("getItemCount-pe", "" + getItemCount());
            return Constant.TYPE_FOOTER_TIP;
        } else {
            return Constant.TYPE_CONTENT;
        }
    }

    @Override
    public void onClick(View view) {
        if(recyclerItemClickListener != null) {
            //getTag获取的即是点击位置
            recyclerItemClickListener.onItemClick(view, (int)view.getTag());
        }
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
