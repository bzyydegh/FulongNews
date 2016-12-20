package cn.edu.gdaib.fulongnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.adapter.HomeViewPagerAdapter;
import cn.edu.gdaib.fulongnews.fragment.FragmentHomeEvent;
import cn.edu.gdaib.fulongnews.fragment.FragmentHomePe;
import cn.edu.gdaib.fulongnews.fragment.FragmentHomeScience;
import cn.edu.gdaib.fulongnews.fragment.FragmentHomeTour;

public class HomeActivity extends BaseActivity
        implements TabLayout.OnTabSelectedListener {

    private Toolbar toolbar;
    private Intent intent = null;
    private String[] titles;
    private List<Fragment> fragments;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewPagerAdapter pagerAdapter;
    private MaterialSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //初始化View
        initView();
    }

    //初始化
    @SuppressWarnings("deprecation")
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        tabLayout = (TabLayout) findViewById(R.id.home_tablayout);
        viewPager = (ViewPager) findViewById(R.id.home_viewpager);
        titles = new String[]{"科技", "体育", "旅游", "国际"};
        fragments = new ArrayList<>();
        pagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mSearchView = (MaterialSearchView) findViewById(R.id.home_search_view);

        setSupportActionBar(toolbar);
        //注入标签
        for (String tab : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        tabLayout.setOnTabSelectedListener(this);
        fragments.add((new FragmentHomeScience()));
        fragments.add(new FragmentHomePe());
        fragments.add(new FragmentHomeTour());
        fragments.add(new FragmentHomeEvent());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mSearchView.setVoiceSearch(true); //语音搜索
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));//查询建议
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem menuItem = menu.findItem(R.id.home_menu_search);
        mSearchView.setMenuItem(menuItem);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu_notify:
                intent = new Intent(this, NotifyActivity.class);
                startActivity(intent);
                break;
            case R.id.home_menu_setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //只显示一次启动页，没被kill的情况下
        // super.onBackPressed();不要调用父类的方法
        intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        //Close on backpressed
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mSearchView.setQuery(searchWrd, false);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
