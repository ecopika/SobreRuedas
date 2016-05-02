package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.GeneradorObjectesJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.ObjecteJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MapActivity;

/**
 * Created by ecopika on 22/03/16.
 */
public class JocThread extends Thread {

    private SurfaceHolder mSurfHolder = null;
    private Handler hndl = null;
    private Context cnt = null;
    private boolean mRun = false;

    private Personatge prs;
    private Mapa map;
    private boolean moviment;
    private boolean pause;
    private int x;
    private int mapSize;

    private ArrayList<ObjecteJoc> obj;

    //objecte GIF




    public JocThread(SurfaceHolder sHold, Context cnt, Handler handler) {
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt = cnt;
        ViewMapaHandler.setActivity((MapActivity) this.cnt);
        ViewMapaHandler.setContext(cnt);


        x = 0;

        mapSize = 3700;
        prs = ViewMapaHandler.generatePersonatge();
        moviment = false;
        pause = false;
        obj = ViewMapaHandler.generateObjecteJoc();
        obj.get(0).setX(CanvasUtils.getWidthScreen()/2);
        obj.get(0).setY(CanvasUtils.getHeightScreen()/2-(obj.get(0).getAlcada()/2));
        obj.get(1).setX(CanvasUtils.getWidthScreen()/2);
        obj.get(1).setY(CanvasUtils.getHeightScreen()/2-50);


        //GIF cadira
        map = ViewMapaHandler.generateMap();


    }


    @Override
    public void run() {

        while (mRun) {
           // Log.i("JocThread","whileThread");
            while (pause) {

            }

            Canvas c = null;
            try {
                prs.getStream().close();
                c = mSurfHolder.lockCanvas(null);
                synchronized (mSurfHolder) {
                    update();
                    doDraw(c);

                    sleep(2);


                }
            } catch (Exception e) {

            } finally {
                if (c != null) {
                    mSurfHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void pause() {
        this.pause = true;
    }

    public void torna() {
        this.pause = false;
    }


    private void update() {
        moviment = false;
        if (x > CanvasUtils.getWidthScreen()-map.getAmplada()+ prs.getAmplada()) {
            moviment = true;
            x -= 5;

        }

        if(obj.get(0).getX()>0-obj.get(0).getAmplada()*2)obj.get(0).setX(obj.get(0).getX() - 8);
        if(obj.get(1).getX()<map.getAmplada())obj.get(1).setX(obj.get(1).getX() + 8);


    }


    private void doDraw(Canvas c) {
        c.drawBitmap(CanvasUtils.escalaImatge(map.getFons(), map.getAlcada() + 3, map.getAmplada()), x, -1, null);
        c.drawBitmap(CanvasUtils.escalaImatge(obj.get(0).getImg(), obj.get(0).getAmplada(), obj.get(0).getAlcada()), obj.get(0).getX(), obj.get(0).getY(), null);
        c.drawBitmap(CanvasUtils.escalaImatge(obj.get(1).getImg(),obj.get(1).getAmplada(), obj.get(1).getAlcada()), obj.get(1).getX(), obj.get(1).getY(), null);
        if (moviment) {//si el personatge esta en moviment
            //cuadrar el temps del GIF amb el temps del joc
            final long now = SystemClock.uptimeMillis();//s'obté el temps actual
            if (prs.getTempsInici() == 0) {
                prs.setTempsInici( now);
            }

            int dur = prs.getGifPrs().getMovie().duration();
            if(dur==0)dur=1000;
            int relTime = (int) ((now - prs.getTempsInici()) % dur);
            prs.getGifPrs().getMovie().setTime(relTime);
            //fi de la sincronització temporal

            //dibuixem el GIF
            prs.getGifPrs().getMovie().draw(c,50,CanvasUtils.getHeightScreen() - CanvasUtils.getHeightScreen() / 3);

        } else {//si el personatge no és mou
            c.drawBitmap(CanvasUtils.escalaImatge(prs.getImg(), CanvasUtils.getWidthScreen() / 3, CanvasUtils.getHeightScreen() / 4), 50, CanvasUtils.getHeightScreen() - CanvasUtils.getHeightScreen() / 3, null);
            c.drawBitmap(CanvasUtils.escalaImatge(map.getObstacles().getRespostes(),CanvasUtils.getWidthScreen(),CanvasUtils.getHeightScreen()/2),0,0,null);
        }

    }

    public void setRunning(boolean b) {
        mRun = b;
    }


    public void clearCanvasObjects() {
        map.setFons(null);
        prs.setImg( null);
        map = null;
        prs = null;

    }

    public int getMapSize() {
        return mapSize;
    }


}
