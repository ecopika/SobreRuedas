package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.menu.Credits;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

/**
 * Created by ecopika on 15/03/16.
 */
public class ViewHandlerMenu {

    public static final String PREFS_NAME = "SobreRuedasPref";
    public static final String  KEY_DIFICULTAT = "Dificultat";
    public static final String KEY_SO = "So";
    public static final ArrayList<String> DIFICULTATS = new ArrayList<String>(){{add("facil");add("moderat");add("dificil");add("moltDificil");}};

    public static final HashMap<String, Integer> difs = new HashMap<String, Integer>();

    public static int dificultat;

    private static Context meuContext;
    private Credits crd;
    private static SharedPreferences settings;
    Personatge per;

    //constructor del controlador de la vista
    public ViewHandlerMenu(Context cnt) {
        meuContext = cnt;
        int x= 0;
        for (int i = 2131558517; i < 2131558521; i++ ) {
            difs.put(DIFICULTATS.get(x), Integer.valueOf(i));
            x++;
        }
    }

    public static void loadPreferences(){
        comprarSharedPreferences();
        settings = meuContext.getSharedPreferences(PREFS_NAME, 0);
        dificultat = settings.getInt(KEY_DIFICULTAT,-1);
    }

    public static SharedPreferences getSettings(){
        return settings;
    }

    public static void setContext(Context cnt){
        meuContext = cnt;
    }

    public static void setSettings(SharedPreferences setting){
        settings = setting;
    }

    public static void comprarSharedPreferences() {
        File f = new File("/data/data/edu.ub.pis2016.dperezgu12alumnes.sobreruedas/shared_prefs/SobreRuedasPref.xml");
        if (!f.exists()) {
            guardarPreferencies(true, 2131558517);
        }
    }

    public static void guardarPreferencies(boolean isChk, int opc){
        SharedPreferences settings = meuContext.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_SO, isChk);
        editor.putInt(KEY_DIFICULTAT, opc);
        editor.commit();


    }

    public static SharedPreferences getPreferences(){
        return settings;
    }

    public void initCredits(){
        crd = new Credits(meuContext);

    }

    /***********************************UTILS MENÚ***************************/

    public static void fullScreen(Activity act){
        //això treu la barra de la part superior de la pantalla
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /***************************************************************************
     * CREDITS
     ***************************************************************************/

    public int creditsGetVoltes(){
        return crd.getVoltes();
    }

    public int creditsGetSortida(){
        return crd.getSortida();
    }

    public void creditsAnimacioSortida(){
        crd.animacioSortida();
    }

    public void creditsShowAll(Canvas c){
        crd.mostraTotsElements(c);
    }

    public void creditsAnimacioEntrada(Canvas c){
        crd.animacioEntrada(c);
    }

    public void creditsFinalitzaPantallaCredits(){
        crd.finalitzaPantallaCredits();
    }

    /********************************************************************************************/


    /**********************************************************************************************
     * TAULA PUNTUACIONS
     **********************************************************************************************/
    public GridView omplirTaules(GridView gv,Activity act, String[] taula){
            gv.setAdapter(new ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,taula));
            return gv;
    }

    private static String[] omplirLlistaDefecte(){
        String[] llista = new String[2];
        llista[0] = "No hi ";
        llista[1] = "ha dades";
        return llista;

    }

    public static String[] omplirLlista(String dificultat){
        String[] llista;
        String[][] rep = ViewMapaHandler.getPuntuacio();
        ArrayList<String> nwLlista = new ArrayList<String>();
        if(rep.length!=0) {
            for (int i = 0; i < rep.length; i++) {
                if(rep[i][1]!=null) {
                    if (dificultat.equals(rep[i][1])) {
                        nwLlista.add(rep[i][0]);
                        nwLlista.add(rep[i][2]);
                    }
                }
            }
        }
        if(nwLlista.size()==0) {
            llista = omplirLlistaDefecte();
        }
        else {
           llista=parseArray(nwLlista);
        }
        return llista;
    }

    private static String[] parseArray(ArrayList<String> lli){
        String[] llista =new String[lli.size()];
        for(int i =0 ;i<lli.size();i++){
            llista[i]=lli.get(i);
        }
        return llista;
    }
    /********************************************************************************************/


    /**********************************************************************************************
     * SELECCIÓ FRAGMENT
     **********************************************************************************************/


    public ArrayList<Personatge> getPersonatges(){
        return ViewMapaHandler.generatePersonatge();
    }





}
