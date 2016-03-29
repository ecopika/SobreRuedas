package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

public class SeleccioPersonatgeActivity extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    private SeleccioPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SeleccioPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public void changePageLeft(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }

    public void changePageRight(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }

    public class SeleccioPagerAdapter extends FragmentStatePagerAdapter {

        public SeleccioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new SeleccioFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}


