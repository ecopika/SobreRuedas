package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads.MapThread;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.Threads.PersonatgeThread;

/**
 * Created by ecopika on 22/03/16.
 */
public class MapaView extends SurfaceView implements SurfaceHolder.Callback{

    private MapThread map;
    private PersonatgeThread prsT;
    private Context cnt;

    public MapaView(Context cnt, AttributeSet attr){
        super(cnt, attr);
        SurfaceHolder holder = getHolder();
        holder.addCallback( this);
        map = new MapThread(holder,cnt,new Handler(){
            @Override
            public void handleMessage(Message m){
                //aquí podem interactuar amb altres views
            }
        });
        prsT = new PersonatgeThread(holder,cnt,new Handler(){
            @Override
            public void handleMessage(Message m){
                //aquí podem interactuar amb altres views
            }
        });
        this.cnt=cnt;
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        map.setRunning(true);
        map.start();//al crear la superficie per a pintar comença el thread
        prsT.initHandler();
        prsT.setRunning(true);
        prsT.start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                prsT.join();
                map.join();//esperem a que el fil acabi per poder destruirlo

                retry = false;

            }catch (InterruptedException ie){

            }
        }

    }

    public MapThread getMapThread(){
        return map;
    }

    public PersonatgeThread getPersThread(){
        return prsT;
    }


}
