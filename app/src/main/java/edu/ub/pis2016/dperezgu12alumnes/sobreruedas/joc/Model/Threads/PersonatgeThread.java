package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.view.SurfaceHolder;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;

/**
 * Created by ecopika on 22/03/16.
 */
public class PersonatgeThread extends Thread {

    private SurfaceHolder mSurfHolder = null;
    private Handler hndl= null;
    private Context cnt= null;
    private boolean mRun = false;
    private Bitmap personatge;
    private Movie mPersonatge;
    private ViewMapaHandler ctrl;
    private Personatge prs;
    private int mapsize;
    private long delay =100;
    private Canvas c;

    public PersonatgeThread(SurfaceHolder sHold,Context cnt, Handler handler){
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt=cnt;
        personatge = BitmapFactory.decodeResource(cnt.getResources(), R.mipmap.nuriafotograma);
        mapsize = 3700;

    }

    @Override
    public void run(){
        while(mRun){
            c = null;
            try{
                c = mSurfHolder.lockCanvas(null);
                synchronized (mSurfHolder){
                    update();
                    doDraw(c);
                }
            }finally {
                if(c!=null){
                    mSurfHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void setCanvas(Canvas c){
        this.c = c;
    }

    public void initHandler(){
        ctrl = new ViewMapaHandler(cnt,(MapActivity)cnt);
        prs = ctrl.generatePersonatge();
        prs.setVelX(10);


    }
//en aquesta funció actualitzem l'estat de cadascún dels threads
    private void update(){
        if(prs.getX()<mapsize ) {
            prs.setMovX(true);
        }
        else {
            prs.setMovX(false);
        }

        if(prs.isMovX()) {
            prs.mouX();
        }
        if(prs.isMovY()) {
            prs.mouY();
        }


    }
//en aquest mètode executem el bucle infinit de dibuix del canvas
    private void doDraw(Canvas c){
        //Bitmap fons = Bitmap.createBitmap(mapa,0,0,CanvasUtils.getWidthScreen(),956);

        c.drawBitmap(CanvasUtils.escalaImatge(personatge,prs.getAmplada(),prs.getAlcada()),prs.getX(),prs.getY(),null);


    }

    public void setRunning(boolean b){
        mRun = b;
    }
}


