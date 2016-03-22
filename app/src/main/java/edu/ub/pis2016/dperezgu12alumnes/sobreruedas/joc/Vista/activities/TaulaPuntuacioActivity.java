package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

public class TaulaPuntuacioActivity extends Activity {

    //contingut de les taules (provisional)
    static final String[] elements = new String[] {"Dani", "300", "Jesus", "200", "xavi", "150", "Andreu", "100", "Hola", "50"};
    static final String[] elements2 = new String[] {"Dani", "700", "Jesus", "400", "xavi", "350", "Andreu", "300", "Hola", "200"};
    static final String[] elements3 = new String[] {"Dani", "350", "Jesus", "280", "xavi", "190", "Andreu", "170", "Hola", "40"};
    static final String[] elements4 = new String[] {"Dani", "1000", "Jesus", "500", "xavi", "450", "Andreu", "200", "Hola", "70"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TabHost th;
        GridView llistaFacil;
        GridView llistaMitja;
        GridView llistaDificil;
        GridView llistaMoltDificil;


        super.onCreate(savedInstanceState);
        //això treu la barra de la part superior de la pantalla
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_taula_puntuacio);


        th=(TabHost)findViewById(R.id.tabHost);


        llistaFacil = (GridView) findViewById(R.id.gPuntuacioFacil);
        llistaMitja = (GridView) findViewById(R.id.gPuntuacioMitja);
        llistaDificil = (GridView) findViewById(R.id.gPuntuacioDificil);
        llistaMoltDificil = (GridView) findViewById(R.id.gPuntuacioMoltDificil);


        //Omplim les taules
        ArrayAdapter<String> llista = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);
        ArrayAdapter<String> llista2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements2);
        ArrayAdapter<String> llista3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements3);
        ArrayAdapter<String> llista4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements4);

        llistaFacil.setAdapter(llista);
        llistaMitja.setAdapter(llista2);
        llistaDificil.setAdapter(llista3);
        llistaMoltDificil.setAdapter(llista4);

        //tab1
        th.setup();
        TabSpec ts1 = th.newTabSpec("tab1");
        ts1.setIndicator("Facil");
        ts1.setContent(R.id.linierLayout1);
        th.addTab(ts1);

        //tab2
        th.setup();
        TabSpec ts2 = th.newTabSpec("tab2");
        ts2.setIndicator("mitja");
        ts2.setContent(R.id.linearLayout2);

        th.addTab(ts2);

        //tab3
        th.setup();
        TabSpec ts3 = th.newTabSpec("tab3");
        ts3.setIndicator("Dificil");
        ts3.setContent(R.id.linearLayout3);
        th.addTab(ts3);

        //tab4
        th.setup();
        TabSpec ts4 = th.newTabSpec("tab4");
        ts4.setIndicator("Molt dificil");
        ts4.setContent(R.id.linierLayout4);
        th.addTab(ts4);
    }
}
