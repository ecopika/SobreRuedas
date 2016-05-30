package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;

public class seleccioPersonatgeActivity extends FragmentActivity {

    private static int NUM_PAGES ;
    private SeleccioPagerAdapter adapter;
    private ViewPager viewPager;
    private ViewHandlerMenu menu;
    private Context cnt;

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
        menu = new ViewHandlerMenu(cnt);

        ViewMapaHandler.setActivity(this);
        ViewMapaHandler.setContext(this);
       // ViewMapaHandler.generateBD();
        ViewMapaHandler.generaJoc();
        NUM_PAGES = menu.getPersonatges().size();
        adapter.notifyDataSetChanged();

    }

    public void changePageLeft(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    public void changePageRight(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }

    public void bottonPlay(View view){
        Bundle args = new Bundle();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            args.putInt("noJugar",0);
            Intent in = new Intent(this,MenuActivity.class);
            startActivity(in);
        }
        else{
            args.putInt("noJugar",1);
            Intent in = new Intent(this,MapActivity.class);
            startActivity(in);
        }
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

