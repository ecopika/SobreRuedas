package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.menu.Credits;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.PCadiraRodes;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesVista.NuvolView;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MenuActivity;

/**
 * Created by ecopika on 15/03/16.
 */
public class ViewHandlerMenu {


    private Context meuContext;
    private Credits crd;

    //constructor del controlador de la vista
    public ViewHandlerMenu(Context cnt){
        meuContext = cnt;
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





}
