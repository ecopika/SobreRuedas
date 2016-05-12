package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.DataHandler;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class GeneradorObjectesJoc {

   // private ArrayList<Mapa> maps;
    private ArrayList<Mapa> map;

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

    public ArrayList<Mapa> generateMap(DataHandler db){

        map = db.getMapes();
        return map;
    }

    public ArrayList<ObjecteJoc> generateObjecte(int id, DataHandler bd){
        ArrayList<ObjecteJoc> obj = bd.getObjectes(id);
        return obj;

    }

    public ArrayList<Mapa> getMap() {
        return map;
    }

    public void setMap(ArrayList<Mapa> map) {
        this.map = map;
    }

    public Personatge getP() {
        return p;
    }

    public void setP(Personatge p) {
        this.p = p;
    }
}
