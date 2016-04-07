package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.app.Activity;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.GeneradorObjectesJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.GifMovieView;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.MapaView;

/**
 * Created by ecopika on 22/03/16.
 */
public class ViewMapaHandler implements Serializable {

    private transient MapaView mapView;
    private transient Context cnt;
    private transient Activity act;
    private transient ViewHandlerMenu ctrl;
    private transient GeneradorObjectesJoc genJoc;



    public ViewMapaHandler(Context cnt, Activity act){
        this.cnt = cnt;
        this.act = act;
        generaJoc();



    }

    public void init(){
        mapView = (MapaView) act.findViewById(R.id.MapView);
       // ctrl = new ViewHandlerMenu(cnt);
    }

    public void generaJoc(){
        genJoc = new GeneradorObjectesJoc();
    }

    public Personatge generatePersonatge(){
        return genJoc.generatePersonatge(cnt);
    }

    public Mapa generateMap(){
        return genJoc.generateMap(3700,CanvasUtils.getHeightScreen());
    }

    public void finishThread(){
        mapView=null;
    }

    public void pauseThread(){
        mapView.pauseThread();
    }

    public void resumeThread(){
        mapView.resumeThread();
    }

    public Mapa getMap(){
        return genJoc.getMap();
    }

    public Personatge getPersonatge(){
        return genJoc.getP();
    }

    public void setActivity(Activity act){
        this.act = act;
    }


}
