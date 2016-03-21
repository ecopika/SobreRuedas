package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.entornsVista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import android.os.Handler;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.activities.MenuActivity;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.objectesVista.NuvolView;

/**
 * Created by dperezgu12.alumnes on 14/03/16.
 */
public class AnimatedView extends ImageView {

    private Context meuContext;

    private boolean puja;
    private Handler ctrl;

    private final int FRAM_RATE = 8;
    private boolean show;
    private boolean show2;
    private NuvolView nBlanc;
    private NuvolView nMorat1;
    private NuvolView nMorat2;
    private  int voltes;
    private int sortida;

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        meuContext = context;

        ctrl = new Handler();
        inits();
        sortida=0;


    }

    public void inits(){
        show = false;
        show2 = false;
        puja = false;
        nBlanc = new NuvolView("blanca",meuContext,false);
        nMorat1 = new NuvolView("",meuContext,false);
        nMorat2 = new NuvolView("",meuContext,false);


        nMorat1.setAmplada(300);
        nMorat2.setAmplada(300);
        nMorat1.setAlcada(100);
        nMorat2.setAlcada(100);


        voltes=0;
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





        if(voltes>=300){
            //Log.i("IF", "ENtra");

            animacioSortida();
            mostraTotsElements(c);

        }
        else if(sortida==0 || sortida==1 || sortida==2){
            animacioEntrada(c);
        }

        if(sortida==3){

            meuContext.startActivity(new Intent(meuContext,MenuActivity.class));

        }

        ctrl.postDelayed(r, FRAM_RATE);


    }

    public void animacioSortida() {
        if(nBlanc.getY() +  (nBlanc.getAlcada() / 2) > 0) nBlanc.setY(nBlanc.getY() - 10);
        if(nMorat1.getY()<this.getHeight())nMorat1.setY(nMorat1.getY()+10);
        if(nMorat2.getY()<this.getHeight())nMorat2.setY(nMorat2.getY()+10);

        if(nBlanc.getY() + (nBlanc.getAlcada() / 2)  <= 0 && nMorat1.getY()>=this.getHeight() && nMorat2.getY()>=this.getHeight() ){
            inits();
            sortida++;
        }
    }

    public void mostraTotsElements(Canvas c){
        c.drawBitmap(CanvasUtils.escalaImatge(nMorat1.getImatge().getBitmap(), nMorat1.getAlcada(), nMorat1.getAmplada()), nMorat1.getX() - (nMorat1.getAmplada() / 2), nMorat1.getY(), null);
        c.drawBitmap(CanvasUtils.escalaImatge(nMorat2.getImatge().getBitmap(),nMorat2.getAlcada(),nMorat2.getAmplada()),nMorat2.getX(),nMorat2.getY() ,null);
        c.drawBitmap(CanvasUtils.escalaImatge(nBlanc.getImatge().getBitmap(), nBlanc.getAlcada(), nBlanc.getAmplada()), nBlanc.getX() - (nBlanc.getAmplada() / 2), nBlanc.getY() - (nBlanc.getAlcada() / 2), null);
        c.drawText(nMorat2.getText(), (nMorat2.getX()) + nMorat2.getAmplada() / 4, nMorat2.getY() + 100, nMorat2.getTextNuvol().getPaint());
        c.drawText(nBlanc.getText(), (nBlanc.getX() - (nBlanc.getAmplada() / 2)) + 80, nBlanc.getY() + 75, nBlanc.getTextNuvol().getPaint());
        c.drawText(nMorat1.getText(), (nMorat1.getX() - (nMorat1.getAmplada() / 2)) + nMorat1.getAmplada() / 4, nMorat1.getY() + 100, nMorat1.getTextNuvol().getPaint());

    }

    public void animacioEntrada(Canvas c){
        //per defecte inicialitzats a -1 tant la X com la Y de qualsevol nuvol
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
                nBlanc.showText(true);
                puja = true;
            }
            if(puja){
                nBlanc.setY(nBlanc.getY()-10);
            }
            if(nBlanc.getY()-(nBlanc.getAlcada()/2)<200){
                puja = false;
                nMorat1.showText(true);
                nMorat2.showText(true);
            }

        }
        if(!puja && nMorat1.isShowText() && nMorat2.isShowText()){
            //Nuvol morat 1
            //***************************************************************************************
            if(nMorat1.getAmplada()<350){
                nMorat1.setAmplada(nMorat1.getAmplada()+20);
            }
            if(nMorat1.getAlcada()<150){
                nMorat1.setAlcada(nMorat1.getAlcada()+20);
            }

            if(nMorat1.getAmplada()>=350 && nMorat1.getAlcada()>=300 && nMorat1.getY()-(nMorat1.getAlcada()/2)>200){
                nMorat1.showText(true);
            }
            nMorat1.setX((this.getWidth()/2-(nMorat1.getAmplada()/2)));
            nMorat1.setY(((this.getHeight() / 2 - (nMorat1.getAlcada() / 2))));
            c.drawBitmap(CanvasUtils.escalaImatge(nMorat1.getImatge().getBitmap(),nMorat1.getAlcada(),nMorat1.getAmplada()),nMorat1.getX()-(nMorat1.getAmplada()/2),nMorat1.getY() ,null);


            //Nuvol Morat 2
            //***************************************************************************************
            if(nMorat2.getAmplada()<350){
                nMorat2.setAmplada(nMorat2.getAmplada()+20);
            }
            if(nMorat2.getAlcada()<150){
                nMorat2.setAlcada(nMorat2.getAlcada()+20);
            }

            if(nMorat2.getAmplada()>=350 && nMorat2.getAlcada()>=300 && nMorat2.getY()-(nMorat2.getAlcada()/2)>200){
                nMorat2.showText(true);
            }
            nMorat2.setX((this.getWidth() / 2 + (nMorat2.getAmplada() / 2)) - ((nMorat2.getAmplada() / 2)));
            nMorat2.setY(((this.getHeight() / 2 - (nMorat2.getAlcada() / 2))));
            c.drawBitmap(CanvasUtils.escalaImatge(nMorat2.getImatge().getBitmap(),nMorat2.getAlcada(),nMorat2.getAmplada()),nMorat2.getX(),nMorat2.getY() ,null);


        }

        c.drawBitmap(CanvasUtils.escalaImatge(nBlanc.getImatge().getBitmap(), nBlanc.getAlcada(), nBlanc.getAmplada()), nBlanc.getX() - (nBlanc.getAmplada() / 2), nBlanc.getY() - (nBlanc.getAlcada() / 2), null);

        //Configuracio del text del nuvol blanc
        if (nBlanc.isShowText()){
            if(sortida==0 || sortida==1) nBlanc.setText(getResources().getString(R.string.des));
            else nBlanc.setText(getResources().getString(R.string.dis));
            c.drawText(nBlanc.getText(), (nBlanc.getX() - (nBlanc.getAmplada() / 2)) + 80, nBlanc.getY() + 75, nBlanc.getTextNuvol().getPaint());
        }
//Configuracio del text del nuvol morat 1
        if (!puja && nMorat1.isShowText()){
            if(sortida==0) nMorat1.setText(getResources().getString(R.string.d3));
            else if(sortida==1)nMorat1.setText(getResources().getString(R.string.d4));
            else nMorat1.setText(getResources().getString(R.string.e2));
            c.drawText(nMorat1.getText(), (nMorat1.getX() - (nMorat1.getAmplada() / 2)) + nMorat1.getAmplada() / 4, nMorat1.getY() + 100, nMorat1.getTextNuvol().getPaint());
        }
//Configuracio del text del nuvol morat 2
        if (!puja && nMorat2.isShowText()){
            if(sortida==0) nMorat2.setText(getResources().getString(R.string.d1));
            else if(sortida==1)nMorat2.setText(getResources().getString(R.string.d2));
            else nMorat2.setText(getResources().getString(R.string.e1));
            c.drawText(nMorat2.getText(), (nMorat2.getX()) + nMorat2.getAmplada() / 4, nMorat2.getY() + 100, nMorat2.getTextNuvol().getPaint());
            voltes++;
            //Log.i("Voltes",String.valueOf(voltes));

        }

    }
}
