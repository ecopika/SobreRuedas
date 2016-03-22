package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by ecopika on 11/03/16.
 */
public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {

    private PanelThread _thread;
    private CanvasUtils utils;

    public DrawingPanel(Context context, DisplayMetrics metric){
        super(context);
        getHolder().addCallback(this);
        utils = new CanvasUtils(metric);
    }

    public void onDraw(Canvas canvas){

        Paint paint = new Paint();
        canvas.drawColor(Color.BLACK);
        Bitmap titol = BitmapFactory.decodeResource(getResources(), R.mipmap.sobreruedas);
        Bitmap cadira = BitmapFactory.decodeResource(getResources(),R.mipmap.nuriafotograma);
        Bitmap fons = BitmapFactory.decodeResource(getResources(),R.mipmap.intro);
        canvas.drawBitmap(utils.escalaImatge(fons,utils.getHeightScreen(),utils.getWidthScreen()),0,0,paint);
        canvas.drawBitmap(utils.escalaImatge(titol, 200, 700), 40, 50, null);
        canvas.drawBitmap(utils.escalaImatge(cadira,500,250), 300, 700, null);



    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        _thread=new PanelThread(getHolder(),this);
        _thread.setRunning(true);
        _thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            _thread.setRunning(false);
            _thread.join();
        } catch (InterruptedException e) {
        }

    }
}
