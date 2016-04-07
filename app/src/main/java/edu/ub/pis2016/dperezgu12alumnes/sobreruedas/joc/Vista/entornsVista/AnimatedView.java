package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import android.os.Handler;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MenuActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by dperezgu12.alumnes on 14/03/16.
 */
public class AnimatedView extends ImageView {


    private Handler ctrl;

    private final int FRAM_RATE = 8;
    private ViewHandlerMenu ctrlAnimacio;


    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctrlAnimacio = new ViewHandlerMenu(context);
        ctrlAnimacio.initCredits();
        ctrl = new Handler();



    }



    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c){
        Bitmap fons = BitmapFactory.decodeResource(getResources(), R.mipmap.intro);
        
        c.drawBitmap(CanvasUtils.escalaImatge(fons, CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()), 0, 0, null);





        if(ctrlAnimacio.creditsGetVoltes()>=50){

            ctrlAnimacio.creditsAnimacioSortida();
            ctrlAnimacio.creditsShowAll(c);

        }
        else if(ctrlAnimacio.creditsGetSortida()==0 || ctrlAnimacio.creditsGetSortida()==1 || ctrlAnimacio.creditsGetSortida()==2){
            ctrlAnimacio.creditsAnimacioEntrada(c);
        }

        if(ctrlAnimacio.creditsGetSortida()==3){
            ctrlAnimacio.creditsFinalitzaPantallaCredits();

        }

        ctrl.postDelayed(r, FRAM_RATE);


    }






}
