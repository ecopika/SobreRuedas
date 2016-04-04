package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

public class seleccioPersonatgeActivity extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    private SeleccioPagerAdapter adapter;
    private ViewPager viewPager;
    private ImageButton btnJugar;
    private LinearLayout layout;

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
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    public void changePageRight(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }

    public void bottonPlay(View view){
        startActivity(new Intent(getApplicationContext(),MapActivity.class));
    }

    public class SeleccioPagerAdapter extends FragmentStatePagerAdapter {

        public SeleccioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            SeleccioFragment fragment = new SeleccioFragment();
            Bundle args = new Bundle();
            args.putInt(SeleccioFragment.IMAGE, i);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                args.putInt("noJugar",0);
            }
            else{
                args.putInt("noJugar",1);
            }

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

