package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.DataHandler;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class GeneradorObjectesJoc {

   // private ArrayList<Mapa> maps;
    private Mapa map;

    /*********************************************+
     * S'ha d'implementar la part de la base de dades per obtenir els i el personatge a partir del seu identificador
     */

    private Personatge p;
    private ObjecteJoc o;
    public GeneradorObjectesJoc(){
    }



    /************************************************************************
     *    Perfil personatge
     ***********************************************************************/

    public ArrayList<Personatge> generatePersonatge(Context  cnt, DataHandler bd){
        ArrayList<Personatge> prs = bd.getPersonatges();

        return prs;

    }

    public Mapa generateMap(int ampladaMapa, int alcadaMapa, DataHandler db){

        map = db.getMapes().get(0);
        map.setAlcada(alcadaMapa);
        map.setAmplada(ampladaMapa);
        return map;
    }

    public ArrayList<ObjecteJoc> generateObjecte( DataHandler bd){
        ArrayList<ObjecteJoc> obj = bd.getObjectes(0);
        return obj;

    }

    public Mapa getMap() {
        return map;
    }

    public void setMap(Mapa map) {
        this.map = map;
    }

    public Personatge getP() {
        return p;
    }

    public void setP(Personatge p) {
        this.p = p;
    }
}
