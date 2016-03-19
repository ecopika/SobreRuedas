package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.entornsVista;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by ecopika on 11/03/16.
 */
public class PanelThread extends Thread {

    private SurfaceHolder _surfaceHolder;
    private DrawingPanel _panel;
    private boolean _run = false;

    public PanelThread(SurfaceHolder surfaceHolder, DrawingPanel panel){
        _surfaceHolder = surfaceHolder;
        _panel = panel;
    }

    public void setRunning(boolean run){
        _run = run;
    }

    public void run(){
        Canvas c;
        while(_run){
            c=null;

            try {

                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {

                    _panel.postInvalidate();
                }
            }finally{
                if (c != null){
                    _surfaceHolder.unlockCanvasAndPost(c);
                }

            }



        }



    }

}
