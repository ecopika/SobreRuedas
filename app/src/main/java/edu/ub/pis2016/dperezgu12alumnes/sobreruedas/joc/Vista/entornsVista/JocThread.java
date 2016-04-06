package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.SystemClock;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
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
    private ViewMapaHandler ctrl;
    private boolean moviment;
    private boolean pause;
    private int x;
    private int mapSize;

    //objecte GIF

    private long tempsInici;
    private GifMovieView gifPrs;
    private InputStream stream;


    public JocThread(SurfaceHolder sHold, Context cnt, Handler handler) {
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt = cnt;
        x = 0;

        mapSize = 3700;
        //initHandler();
        prs = ctrl.getPersonatge();
        moviment = false;
        pause = false;
        //GIF cadira
        stream = null;
        try {
            stream = cnt.getAssets().open("movnuria.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }

        gifPrs = new GifMovieView(cnt, stream);
        map = ctrl.getMap();


    }


    @Override
    public void run() {
        while (mRun) {
            while (pause) {

            }
            Canvas c = null;
            try {
                stream.close();
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
        if (x > -2500 + CanvasUtils.getWidthScreen()) {
            moviment = true;
            x -= 5;

        }


    }


    private void doDraw(Canvas c) {
        //Bitmap fons = Bitmap.createBitmap(mapa,0,0,CanvasUtils.getWidthScreen(),956);

        c.drawBitmap(CanvasUtils.escalaImatge(map.getFons(), CanvasUtils.getHeightScreen() + 3, 5000), x, -1, null);

        if (moviment) {//si el personatge esta en moviment
            //cuadrar el temps del GIF amb el temps del joc
            final long now = SystemClock.uptimeMillis();//s'obté el temps actual
            if (tempsInici == 0) {
                tempsInici = now;
            }

            int dur = gifPrs.getMovie().duration();
            if(dur==0)dur=1000;
            int relTime = (int) ((now - tempsInici) % dur);
            gifPrs.getMovie().setTime(relTime);
            //fi de la sincronització temporal

            //dibuixem el GIF
            gifPrs.getMovie().draw(c,50,CanvasUtils.getHeightScreen() - CanvasUtils.getHeightScreen() / 3);

        } else {//si el personatge no és mou
            c.drawBitmap(CanvasUtils.escalaImatge(prs.getImg(), CanvasUtils.getWidthScreen() / 3, CanvasUtils.getHeightScreen() / 4), 50, CanvasUtils.getHeightScreen() - CanvasUtils.getHeightScreen() / 3, null);

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
