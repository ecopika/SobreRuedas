package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ecopika on 11/03/16.
 */
public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {

    private PanelThread _thread;
    private DisplayMetrics  tamanyPantalla;

    public DrawingPanel(Context context, DisplayMetrics metric){
        super(context);
        getHolder().addCallback(this);
        tamanyPantalla = metric;
    }

    public void onDraw(Canvas canvas){

        Paint paint = new Paint();
        canvas.drawColor(Color.BLACK);
        Bitmap titol = BitmapFactory.decodeResource(getResources(),R.mipmap.sobreruedas);
        Bitmap cadira = BitmapFactory.decodeResource(getResources(),R.mipmap.nuriafotograma);
        Bitmap fons = BitmapFactory.decodeResource(getResources(),R.mipmap.intro);
        canvas.drawBitmap(escalaImatge(fons,getHeightScreen(),getWidthScreen()),0,0,paint);
        canvas.drawBitmap(escalaImatge(titol,200,700), 40, 50, null);
        canvas.drawBitmap(escalaImatge(cadira,500,250), 300, 700, null);



    }

    //funcio que retorna l'amplada de la pantalla
    public int getWidthScreen(){
        return tamanyPantalla.widthPixels;
    }

    //funcio que retorna l'alçada de la pantalla
    public int getHeightScreen(){
        return tamanyPantalla.heightPixels;
    }



    //funció per escalar una imatge
    public Bitmap escalaImatge(Bitmap bm,int newHeight,int newWidth){
        int width = bm.getWidth();
        int height = bm.getHeight();
        float escalaWidth = ((float) newWidth) / width;
        float escalaHeight = ((float) newHeight) / height;
        //es creea una matriu per manipular el tamany d'una imatge
        Matrix matrix = new Matrix();
        //escalem la imatge bitmap
        matrix.postScale(escalaWidth,escalaHeight);
        //generem el nou bitmap
        return Bitmap.createBitmap(bm,0,0,width,height,matrix,false);


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
