package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.GifMovieView;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.MapaView;

/**
 * Created by ecopika on 22/03/16.
 */
public class ViewMapaHandler {

    private MapaView mapView;
    private Context cnt;
    private MapActivity act;
    private ViewHandlerMenu ctrl;



    public ViewMapaHandler(Context cnt, MapActivity act){
        this.cnt = cnt;
        this.act = act;
        mapView = (MapaView) act.findViewById(R.id.MapView);
        ctrl = new ViewHandlerMenu(cnt);

    }

    public Personatge generatePersonatge(){
        return ctrl.generatePersonatge();
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


}
