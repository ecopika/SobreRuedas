package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;


import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
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
