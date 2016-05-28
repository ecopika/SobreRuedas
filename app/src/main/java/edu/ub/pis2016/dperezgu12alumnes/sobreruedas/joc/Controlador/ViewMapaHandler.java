package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.GeneradorObjectesJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Joc;
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
public  class ViewMapaHandler{

    private static MapaView mapView;
    private static Context cnts;
    private static Activity acts;
    private static GeneradorObjectesJoc genJoc;
    private static DataHandler ctrlBD;
    private static SQLiteDatabase db;
    private static float x;
    private static float y;
    private static boolean clic;
    private static ArrayList<Personatge> prs;
    private static Joc joc;


    public ViewMapaHandler(Context cnt, Activity act){
        cnts = cnt;
        acts = act;




    }

    public static float getX() {
        return x;
    }

    public static void setX(float x) {
        ViewMapaHandler.x = x;
    }

    public static float getY() {
        return y;
    }

    public static void setY(float y) {
        ViewMapaHandler.y = y;
    }

    public static void generateBD(){
        if(ctrlBD==null) {
            ctrlBD = new DataHandler(cnts, "DADES", null, 1);
        }
    }

    public static void guardarPuntuacio(String dificultat,int puntuacio,String nom){
        ctrlBD.guardarPuntuacio(dificultat,puntuacio,nom);
    }

    public static String[][] getPuntuacio(){
        return ctrlBD.getPuntuacio();
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
        joc=new Joc();
        genJoc = new GeneradorObjectesJoc();
    }

    public static ArrayList<Personatge> generatePersonatge(){
        prs = genJoc.generatePersonatge(cnts, ctrlBD);
        joc.setPersonatge(prs.get(0));
        return prs;
    }

    public static ArrayList<Float> getFactors(int id){
        return ctrlBD.getFactors(id);
    }

    public static ArrayList<Mapa> generateMap(){
        return genJoc.generateMap(ctrlBD);
    }

    public static ArrayList<ObjecteJoc> generateObjecteJoc(int id){

        return genJoc.generateObjecte(id, ctrlBD);
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

    public static ArrayList<Mapa> getMap(){
        return genJoc.getMap();
    }

   // public static Personatge getPersonatge(){
     //   return genJoc.getP();
    //}

    public static void setPersonatge(Personatge prs){
        genJoc.setP(prs);
    }

    public static void setActivity(Activity act){
        acts = act;
    }



    public static void setClic(boolean Clic){
        clic = Clic;
    }

    public static boolean isClic(){
        return clic;
    }

    public static ArrayList<Personatge> getPersonatges(){
        return prs;
    }

    public static int numPersonatges(){
        return prs.size();
    }


    /*******************************************************************************************
     *  FUNCIONS JOC THREAD
     ********************************************************************************************/
//configurem les transicions entre pantalles
    public static void initFinalPantalla(){
        joc.initFinalPantalla();
    }
//carreguem el mapa
    public static void generateMapa(){
        joc.setContext(cnts);

        joc.generateMapa();


    }
//inicia el personatge
    public static void initPersonatge(int indx) {
        joc.initPersonatge(indx);
    }
//obtenim el personatge utilitzat
    public static Personatge getPersonatge(){
       return joc.getPersonatge();

    }

    //reiniciem tots els valors
    public static void restartValuesMap(){
        joc.restartValuesMap();
    }


    public static void updateMapa(){
        joc.updateMapa();
    }

    public static void drawMapa(Canvas c){
        joc.pintar(c);
    }

    public static void clearCanvasObjects(){
        joc.clearCanvasObjects(1);
    }
}
