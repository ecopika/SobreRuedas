package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;

/**
 * Created by ecopika on 22/03/16.
 */
public class MapaView extends SurfaceView implements SurfaceHolder.Callback{

    private JocThread map;
    private Context cnt;
    private SurfaceHolder holder;

    public MapaView(Context cnt, AttributeSet attr){
        super(cnt, attr);
         holder = getHolder();
        holder.addCallback( this);


        this.cnt=cnt;
        setFocusable(true);

    }


    public void initGameThread(Context cnt){
        map = new JocThread(holder,cnt,new Handler(){
            @Override
            public void handleMessage(Message m){
                //aquí podem interactuar amb altres views
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        map.setRunning(true);
        map.start();//al crear la superficie per a pintar comença el thread



    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        map.setRunning(false);
        map.torna();
        map.clearCanvasObjects(1);//netejem el buffer del canvas
        while(retry){
            //try{

            map.interrupt();//matem el thread
            map=null;//acabem de matar el thread
                retry = false;

            //}catch (InterruptedException ie){

           // }
        }

    }

    public JocThread getMapThread(){
        return map;
    }



    public void pauseThread(){
        map.pause();
    }
    public void resumeThread(){
        map.torna();
    }

}
