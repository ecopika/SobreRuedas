package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class GeneradorObjectesJoc {

    private ArrayList<Mapa> maps;
    private Mapa map;

    /*********************************************+
     * S'ha d'implementar la part de la base de dades per obtenir els i el personatge a partir del seu identificador
     */

    private Personatge p;

    public GeneradorObjectesJoc(int ampladaMapa, int alcadaMap, Context cnt){
        map = generateMap(ampladaMapa,alcadaMap);


        p = generatePersonatge(cnt);


    }

    /************************************************************************
     *    Perfil personatge
     ***********************************************************************/

    public Personatge generatePersonatge(Context  cnt){
        return new PCadiraRodes("Nuria","nuriafotograma.png", 10 ,"azul", "Pantera","Lasaña","Basquet","Mujer maravilla","pintar","leer comics de mortadelo y filemon","pyasos","no hay nada imposible para mi","llegar a ser una gran politica y cambiar las cosas","nuriafotograma.png",cnt,"movnuria.gif" );

    }

    public Mapa generateMap(int ampladaMapa, int alcadaMapa){
        return new Mapa(ampladaMapa, alcadaMapa);
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
