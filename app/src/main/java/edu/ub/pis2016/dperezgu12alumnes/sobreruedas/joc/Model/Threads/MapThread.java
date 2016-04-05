package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.SystemClock;
import android.view.SurfaceHolder;

import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;

/**
 * Created by ecopika on 22/03/16.
 */
public class MapThread extends Thread {

    private SurfaceHolder mSurfHolder = null;
    private Handler hndl= null;
    private Context cnt= null;
    private boolean mRun = false;
    private Bitmap mapa;
    private Bitmap personatge;
    private Personatge prs;
    private ViewMapaHandler ctrl;
    private boolean moviment;



    private int x;
    private int mapSize;

    public MapThread(SurfaceHolder sHold,Context cnt, Handler handler){
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt=cnt;
        x = 0;
       mapa = BitmapFactory.decodeResource(cnt.getResources(),R.drawable.mapabuit);
        mapSize = 3700;
        //initHandler();
        personatge = BitmapFactory.decodeResource(cnt.getResources(), R.mipmap.nuriafotograma);
        moviment = false;


    }

    @Override
    public void run(){
        while(mRun){
            Canvas c = null;
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



    public void initHandler(){
       // ctrl = new ViewMapaHandler(cnt,(MapActivity)cnt);
        //prs = ctrl.generatePersonatge();
        //prs.setVelX(10);


    }

    private void update(){
        moviment = false;
        if(x>-2500+CanvasUtils.getWidthScreen()) {
            moviment = true;
            x -= 5;

        }


    }

    private void doDraw(Canvas c){
       //Bitmap fons = Bitmap.createBitmap(mapa,0,0,CanvasUtils.getWidthScreen(),956);

        c.drawBitmap(CanvasUtils.escalaImatge(mapa,CanvasUtils.getHeightScreen()+3,mapSize),x,0,null);

        if(moviment){
            final long now = SystemClock.uptimeMillis();
            

        }
        else{
           c.drawBitmap(CanvasUtils.escalaImatge(personatge, 400, 400),50,CanvasUtils.getHeightScreen()-500,null);

        }

    }

    public void setRunning(boolean b){
        mRun = b;
    }

    public int getMapSize(){
        return mapSize;
    }


}
