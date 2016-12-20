package cn.edu.gdaib.fulongnews.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.edu.gdaib.fulongnews.R;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (savedInstanceState == null){
            mSettingFragment = new SettingFragment();
            replaceFragment(R.id.content_setting, mSettingFragment);
        }
        mToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //在这里保存数据
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(int viewId, Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(viewId, fragment)
                .commit();
    }

    //A placeholder fragment containing a settings view.
    public static class SettingFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_preference);
        }
    }

}
