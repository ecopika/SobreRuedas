package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.entornsVista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import android.os.Handler;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.objectesVista.NuvolView;

/**
 * Created by dperezgu12.alumnes on 14/03/16.
 */
public class AnimatedView extends ImageView {

    private Context meuContext;

    private boolean puja;
    private Handler ctrl;

    private final int FRAM_RATE = 12;
    private boolean show;
    private boolean show2;
    private NuvolView nBlanc;
    private NuvolView nMorat1;

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context,attrs);
        meuContext = context;
        show = false;
        show2 = false;
        puja = false;
        ctrl = new Handler();
        //nBlanc = new NuvolView("blanca",meuContext);
        //nMorat1 = new NuvolView("",meuContext);

    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c){
        Bitmap fons = BitmapFactory.decodeResource(getResources(), R.mipmap.intro);
        c.drawBitmap(CanvasUtils.escalaImatge(fons, CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()),0,0,null);
        Paint paint = new Paint();


        //per defecte inicialitzats a -1 tant la X com la Y de qualsevol numero
        if (nBlanc.getX() < 0 && nBlanc.getY() < 0){
            nBlanc.setX(this.getWidth() / 2 - (nBlanc.getAmplada() / 2));
            nBlanc.setY(this.getHeight()/2-(nBlanc.getAlcada()/2));

        }else{

            if(nBlanc.getAmplada()<350){
                nBlanc.setAmplada(nBlanc.getAmplada()+20);
            }
            if(nBlanc.getAlcada()<310){
                nBlanc.setAlcada(nBlanc.getAlcada()+20);
            }

            if(nBlanc.getAmplada()>=350 && nBlanc.getAlcada()>=300 && nBlanc.getY()-(nBlanc.getAlcada()/2)>200){
                show = true;
                puja = true;
            }
            if(puja){
                nBlanc.setY(nBlanc.getY()-1);
            }
            if(nBlanc.getY()-(nBlanc.getAlcada()/2)<200){
                puja = false;
            }
        }
        //c.drawBitmap(CanvasUtils.escalaImatge(nBlanc.getImatge().getBitmap(), nBlanc.getAlcada(), nBlanc.getAmplada()), x - (widthNuvol / 2), y - (heightNuvol / 2), null);
        if (show){
            paint.setColor(Color.MAGENTA);
            paint.setStrokeWidth(12);
            paint.setTextSize(32f);
            c.drawText("DESENVOLUPADORS", (nBlanc.getX() - (nBlanc.getAmplada() / 2)) + 10, nBlanc.getY() + 75, paint);
        }

        if (show2){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(12);
            paint.setTextSize(32f);
           // c.drawText("DESENVOLUPADORS", ( - (widthNuvol / 2)) + 10, y + 75, paint);
        }

        ctrl.postDelayed(r, FRAM_RATE);


    }
}
