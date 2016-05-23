package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

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
    private Context cnt = null;
    private boolean mRun = false;

    private boolean pause;

    private static final long ourTarget_millisPerFrame = 5; // ~30 FPS




    public JocThread(SurfaceHolder sHold, Context cnt, Handler handler) {
        mSurfHolder = sHold;
        this.cnt = cnt;
        ViewMapaHandler.setActivity((MapActivity) this.cnt);
        ViewMapaHandler.setContext(cnt);






        //de moment no utilitzem, però s'utilitzarà
        /*//CANVI DE COLOR I TAMANY DE LA VARIABLE DE CONTAR EL TEMPS
        paint.setColor(Color.BLACK);
        paint.setTextSize(59);
        //AGAFA EL TEMPS ACTUAL
        starttime = System.currentTimeMillis();
        **/


        ViewMapaHandler.generateMapa();

        ViewMapaHandler.restartValuesMap();

        pause = false;

        ViewMapaHandler.setClic(false);
    }







    @Override
    public void run() {

        while (mRun) {
            while (pause) {

            }

            Canvas c = null;
            try {
                ViewMapaHandler.getPersonatge().getStream().close();
                c = mSurfHolder.lockCanvas(null);
                synchronized (mSurfHolder) {



                        update();
                        doDraw(c);




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
       /* long startTime = SystemClock.uptimeMillis();
        long stopTime = SystemClock.uptimeMillis();
        long howLongItTakesForUsToDoOurWork = stopTime - startTime;
        long timeToWait = ourTarget_millisPerFrame - howLongItTakesForUsToDoOurWork;
        if ( timeToWait < 2 )
            timeToWait = 2;
        /*try {
            sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ViewMapaHandler.updateMapa();
    }



    private void doDraw(Canvas c) {
        ViewMapaHandler.drawMapa(c);

    }

    public void clearCanvasObjects(){
        ViewMapaHandler.clearCanvasObjects();
    }



    public void setRunning(boolean b) {
        mRun = b;
    }















}
