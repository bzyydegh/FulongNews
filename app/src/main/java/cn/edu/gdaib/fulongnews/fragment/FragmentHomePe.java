package cn.edu.gdaib.fulongnews.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.activity.NewsDetailActivity;
import cn.edu.gdaib.fulongnews.adapter.PeRecyclerAdapter;
import cn.edu.gdaib.fulongnews.common.PeHttpRequest;
import cn.edu.gdaib.fulongnews.common.RecyclerItemClickListener;
import cn.edu.gdaib.fulongnews.utils.Constant;
import cn.edu.gdaib.fulongnews.utils.PromptUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 体育--Fragment
 * Created by ZYongY
 * on 2016/11/26.
 */

public class FragmentHomePe extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PeRecyclerAdapter mRecyclerAdapter;
    private boolean isRefreshing = false;
    private int tempCount;
    private int tempTotal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pe_home, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_pe_recycler);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.home_pe_swipe_refresh);
        initView();//初始化
        return view;
    }

    //初始化
    private void initView() {
        mRecyclerAdapter = new PeRecyclerAdapter(getContext(), PeHttpRequest.getData());
        LinearLayoutManager mll= new LinearLayoutManager(getContext());
        mll.setOrientation(LinearLayoutManager.VERTICAL);

        /*RecyclerView部分*/
        mRecyclerView.setLayoutManager(mll); //线性布局
        mRecyclerView.setAdapter(mRecyclerAdapter); //适配器
        mRecyclerView.setHasFixedSize(true); //高度固定
        mRecyclerAdapter.setOnItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = PeHttpRequest.getData().get(0).getResult().get(position).getUrl();
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("HtmlUrl", url);
                intent.putExtra("ToolbarTitle", "体育");
                startActivity(intent);
            }
        });
        //滑动监听，是否滑到最后
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //得到当前显示的最后一个item的view
                View lastChildView = recyclerView.getLayoutManager().getChildAt(
                        recyclerView.getLayoutManager().getChildCount() - 1);
                //得到lastChildView的bottom坐标值
                int lastChildBottom = lastChildView.getBottom();
                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                int recyclerBottom = recyclerView.getBottom() - recyclerView
                        .getPaddingBottom();
                //通过这个lastChildView得到这个view当前的position值
                int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
                //判断lastChildView的bottom值跟recyclerBottom
                //判断lastPosition是不是最后一个position
                //如果两个条件都满足则说明是真正的滑动到了底部
                if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.
                        getLayoutManager().getItemCount() - 1) {
                    //触发事件写在这里
                    loadMoreData();
                }
            }
        });

        /*SwipeRefreshLayout部分*/
        //设置下拉圆圈的大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置下拉圆圈上的颜色，红蓝黄绿
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE,
                Color.YELLOW, Color.GREEN);
        //设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeRefreshLayout.setDistanceToTriggerSync(300);
        //设定下拉圆圈的背景
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //设置监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //刷新动画开始后回调到此方法
            @Override
            public void onRefresh() {
                if (! isRefreshing) {
                    isRefreshing = true;
                    Observable.timer(4, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Long>() {
                                @Override
                                public void call(Long aLong) {
                                    //重新加载数据
                                    PeHttpRequest.networkRequest(Constant.PE_API_URL,
                                            Constant.PE_APP_KEY, "1", "50");
                                    mRecyclerAdapter.setCount(10);
                                    mRecyclerAdapter.notifyDataSetChanged();
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    PromptUtil.ToastPrompt(getContext(), "刷新完成", Toast.LENGTH_SHORT);
                                }
                            });
                    isRefreshing = false;
                }
            }
        });

    }

    //加载更多数据
    private void loadMoreData () {
        tempCount = mRecyclerAdapter.getCount();
        tempTotal = PeHttpRequest.getData().get(0).getResult().size();
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (tempCount < tempTotal && (tempCount + 5) <= tempTotal) {
                            tempCount += 5;
                            mRecyclerAdapter.setCount(tempCount);
                            mRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
