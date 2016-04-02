package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.content.Context;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads.MapThread;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads.PersonatgeThread;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.MapaView;

/**
 * Created by ecopika on 22/03/16.
 */
public class ViewMapaHandler {

    private MapaView mapView;
    private MapThread mapThread;
    private PersonatgeThread prsThread;
    private Context cnt;
    private MapActivity act;
    private ViewHandlerMenu ctrl;

    public ViewMapaHandler(Context cnt, MapActivity act){
        this.cnt = cnt;
        this.act = act;
        mapView = (MapaView) act.findViewById(R.id.MapView);
        mapThread = mapView.getMapThread();
        prsThread = mapView.getPersThread();
        ctrl = new ViewHandlerMenu(cnt);
    }

    public Personatge generatePersonatge(){
        return ctrl.generatePersonatge();
    }


}
