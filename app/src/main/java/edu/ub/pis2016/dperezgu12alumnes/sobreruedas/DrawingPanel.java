package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ecopika on 11/03/16.
 */
public class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {

    private PanelThread _thread;

    public DrawingPanel(Context context){
        super(context);
        getHolder().addCallback(this);
    }

    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(4, 4, 4, 4, paint);
        Bitmap titol = BitmapFactory.decodeResource(getResources(),R.mipmap.sobreruedas);
        Bitmap cadira = BitmapFactory.decodeResource(getResources(),R.mipmap.nuriafotograma);
        canvas.drawBitmap(titol,10,10,null);
        canvas.drawBitmap(cadira,20,20,null);



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
