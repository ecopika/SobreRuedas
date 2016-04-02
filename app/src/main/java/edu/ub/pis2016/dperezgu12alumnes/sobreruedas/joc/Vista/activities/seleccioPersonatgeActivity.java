package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

public class seleccioPersonatgeActivity extends FragmentActivity {

    private SeleccioPagerAdapter adapter;
    private ViewPager viewPager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);

        adapter = new SeleccioPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);


    }


    public static class SeleccioPagerAdapter extends FragmentStatePagerAdapter {

        public SeleccioPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new SeleccioFragment();
            Bundle args = new Bundle();
            args.putString(SeleccioFragment.ARG_SECTION_TEXT, getPageTitle(i).toString());
            args.putInt(SeleccioFragment.ARG_SECTION_IMAGE, i);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT" + (position + 1);
        }


        public static class SeleccioFragment extends Fragment {

            public static final String ARG_SECTION_IMAGE = "section_image";
            public static final String ARG_SECTION_TEXT = "section_text";
            private ViewHandlerMenu ctrl;//controlador del menu
            private Personatge nuria;

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                ctrl= new ViewHandlerMenu(this.getContext());

                nuria = ctrl.generatePersonatge();
                View rootView = inflater.inflate(R.layout.activity_seleccio_personatge, container, false);
                Bundle args = getArguments();
                ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView);
                image1.setImageResource(R.mipmap.nuriafotograma);
                ImageView image2 = (ImageView) rootView.findViewById(R.id.imageView2);
                image2.setImageResource(R.drawable.morada);
                ImageView image3 = (ImageView) rootView.findViewById(R.id.imageView3);
                image3.setImageResource(R.drawable.morada);
                ImageView image4 = (ImageView) rootView.findViewById(R.id.imageView4);
                image4.setImageResource(R.drawable.morada);
                TextView titol = (TextView) rootView.findViewById(R.id.textView2);
                titol.setText(nuria.getNom());
                titol.setTextColor(Color.WHITE);
                titol.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));

                final ImageView jugar = (ImageView) rootView.findViewById(R.id.imageView4);
                jugar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        carregaMapa();

                    }
                });

                return rootView;
            }


            public void carregaMapa(){
                startActivity(new Intent(getActivity().getApplicationContext(),MapActivity.class));
            }
        }
    }
}


