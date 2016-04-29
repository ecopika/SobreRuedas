package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.GeneradorObjectesJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.ObjecteJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.GifMovieView;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.MapaView;

/**
 * Created by ecopika on 22/03/16.
 */
public  class ViewMapaHandler implements Serializable {

    private static MapaView mapView;
    private static Context cnts;
    private static Activity acts;
    private static GeneradorObjectesJoc genJoc;
    private static DataHandler ctrlBD;
    private static SQLiteDatabase db;


    public ViewMapaHandler(Context cnt, Activity act){
        cnts = cnt;
        acts = act;




    }

    public static void generateBD(){
        ctrlBD = new DataHandler(cnts,"DADES",null,1);

    }



    public static Context getContext(){
        return cnts;
    }
    public static void setContext(Context cnt){
        cnts = cnt;
    }
    public static void init(MapActivity act){
        mapView = (MapaView) act.findViewById(R.id.MapView);
       // ctrl = new ViewHandlerMenu(cnt);
        //generaJoc();
    }

    public static void generaJoc(){
        genJoc = new GeneradorObjectesJoc();
    }

    public static Personatge generatePersonatge(){
        return genJoc.generatePersonatge(cnts,ctrlBD);
    }

    public static Mapa generateMap(){
        return genJoc.generateMap((int) (CanvasUtils.getHeightScreen() * 1.5), CanvasUtils.getHeightScreen(), ctrlBD);
    }

    public static ArrayList<ObjecteJoc> generateObjecteJoc(){
        return genJoc.generateObjecte( ctrlBD);
    }

    public static void finishThread(){
        mapView=null;
    }

    public static void pauseThread(){
        mapView.pauseThread();
    }

    public static void resumeThread(){
        mapView.resumeThread();
    }

    public static Mapa getMap(){
        return genJoc.getMap();
    }

    public static Personatge getPersonatge(){
        return genJoc.getP();
    }

    public static void setActivity(Activity act){
        acts = act;
    }


}
