package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by ecopika on 22/03/16.
 */
public class MapThread extends Thread {

    private SurfaceHolder mSurfHolder = null;
    private Handler hndl= null;
    private Context cnt= null;
    private boolean mRun = false;
    private Bitmap mapa;
    private int x;
    private int mapSize;
    private PersonatgeThread prsT;

    public MapThread(SurfaceHolder sHold,Context cnt, Handler handler){
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt=cnt;
        x = 0;
       mapa = BitmapFactory.decodeResource(cnt.getResources(),R.drawable.map1pre);
        mapSize = 3700;


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

    public void afegeixPersonatge(PersonatgeThread prs){
        this.prsT=prs;
    }

    private void update(){
        if(x>-2500+CanvasUtils.getWidthScreen()) {
            x -= 10;
        }


    }

    private void doDraw(Canvas c){
       //Bitmap fons = Bitmap.createBitmap(mapa,0,0,CanvasUtils.getWidthScreen(),956);

        c.drawBitmap(CanvasUtils.escalaImatge(mapa,CanvasUtils.getHeightScreen()+3,mapSize),x,0,null);


    }

    public void setRunning(boolean b){
        mRun = b;
    }

    public int getMapSize(){
        return mapSize;
    }
}
