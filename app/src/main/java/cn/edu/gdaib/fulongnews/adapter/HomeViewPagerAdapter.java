package cn.edu.gdaib.fulongnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ViewPager适配器
 * Created by ZYongY
 * on 2016/11/26.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragemnts;
    private String[] titles;

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragemnts = fragments;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return fragemnts.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragemnts.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
