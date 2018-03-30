package com.example.redoy.lynk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.redoy.lynk.R;
import com.example.redoy.lynk.fragment.FragmentSettings;
import com.example.redoy.lynk.fragment.GoogleMapSearchFragment;
import com.example.redoy.lynk.fragment.VoiceSearchFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment mFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    mFragment = new VoiceSearchFragment();
                    getSupportActionBar().setTitle(R.string.title_fragment_voice_search);
                    break;
                }
                case R.id.navigation_dashboard: {
                    mFragment = new GoogleMapSearchFragment();
                    getSupportActionBar().setTitle(R.string.title_fragment_google_map_search);
                    break;
                }
                case R.id.navigation_notifications: {
                    mFragment = new FragmentSettings();
                    getSupportActionBar().setTitle(R.string.title_fragment_settings);
                    break;
                }
            }
            FragmentManager mFragmentManager = getSupportFragmentManager();
            if (mFragment != null) {
                mFragmentManager.beginTransaction().replace(R.id.frame_layout, mFragment).commit();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
