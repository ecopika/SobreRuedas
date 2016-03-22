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





        if(ctrlAnimacio.getVoltes()>=50){

            ctrlAnimacio.animacioSortida();
            ctrlAnimacio.mostraTotsElements(c);

        }
        else if(ctrlAnimacio.getSortida()==0 || ctrlAnimacio.getSortida()==1 || ctrlAnimacio.getSortida()==2){
            ctrlAnimacio.animacioEntrada(c);
        }

        if(ctrlAnimacio.getSortida()==3){
            ctrlAnimacio.finalitzaPantallaCredits();

        }

        ctrl.postDelayed(r, FRAM_RATE);


    }






}
