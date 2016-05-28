package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;

public class TaulaPuntuacioActivity extends Activity {

    //contingut de les taules (provisional)
    /*static final String[] elements = new String[] {"Dani", "300", "Jesus", "200", "xavi", "150", "Andreu", "100", "Hola", "50"};
    static final String[] elements2 = new String[] {"Dani", "700", "Jesus", "400", "xavi", "350", "Andreu", "300", "Hola", "200"};
    static final String[] elements3 = new String[] {"Dani", "350", "Jesus", "280", "xavi", "190", "Andreu", "170", "Hola", "40"};
    static final String[] elements4 = new String[] {"Dani", "1000", "Jesus", "500", "xavi", "450", "Andreu", "200", "Hola", "70"};
    */
    private ViewHandlerMenu ctrlM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TabHost th;
        GridView llistaFacil;
        GridView llistaMitja;
        GridView llistaDificil;
        GridView llistaMoltDificil;
        ctrlM = new ViewHandlerMenu((Context)this);

        super.onCreate(savedInstanceState);
        ViewHandlerMenu.fullScreen(this);//pantalla completa
        setContentView(R.layout.activity_taula_puntuacio);


        th=(TabHost)findViewById(R.id.tabHost);


        llistaFacil = (GridView) findViewById(R.id.gPuntuacioFacil);
        llistaMitja = (GridView) findViewById(R.id.gPuntuacioMitja);
        llistaDificil = (GridView) findViewById(R.id.gPuntuacioDificil);
        llistaMoltDificil = (GridView) findViewById(R.id.gPuntuacioMoltDificil);


        //Omplim les taules

        ctrlM.omplirTaules(llistaFacil,this,ViewHandlerMenu.omplirLlista("facil"));
        ctrlM.omplirTaules(llistaMitja,this,ViewHandlerMenu.omplirLlista("moderat"));
        ctrlM.omplirTaules(llistaDificil,this,ViewHandlerMenu.omplirLlista("dificil"));
        ctrlM.omplirTaules(llistaMoltDificil,this,ViewHandlerMenu.omplirLlista("moltDificil"));


        //tab1
        th.setup();
        TabSpec ts1 = th.newTabSpec("tab1");
        ts1.setIndicator(getResources().getString(R.string.facil));
        ts1.setContent(R.id.linierLayout1);
        th.addTab(ts1);

        //tab2
        th.setup();
        TabSpec ts2 = th.newTabSpec("tab2");
        ts2.setIndicator(getResources().getString(R.string.mitja));
        ts2.setContent(R.id.linearLayout2);

        th.addTab(ts2);

        //tab3
        th.setup();
        TabSpec ts3 = th.newTabSpec("tab3");
        ts3.setIndicator(getResources().getString(R.string.dificil));
        ts3.setContent(R.id.linearLayout3);
        th.addTab(ts3);

        //tab4
        th.setup();
        TabSpec ts4 = th.newTabSpec("tab4");
        ts4.setIndicator(getResources().getString(R.string.molt_dificil));
        ts4.setContent(R.id.linierLayout4);
        th.addTab(ts4);
    }
}
