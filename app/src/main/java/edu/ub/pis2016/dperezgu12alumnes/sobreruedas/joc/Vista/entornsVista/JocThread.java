package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.ArrayAdapter;

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
    private ArrayList<Bitmap> fons;
    private float pl;
    private float fr;
    private ArrayList<ObjecteJoc> obj;




    private float pastX;
    private float pastY;

    private Bitmap creu;
    private Bitmap tic;

    private boolean clicado=false;
    long starttime;

    public static boolean clic=false;
    private boolean correcte = false;
    Paint paint = new Paint();

    //ARRAY PER POSAR LES IMATGES DE LES VIDES DEL PERSONATGE
    private ArrayList<Bitmap> vides;




    //objecte GIF




    public JocThread(SurfaceHolder sHold, Context cnt, Handler handler) {
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt = cnt;
        ViewMapaHandler.setActivity((MapActivity) this.cnt);
        ViewMapaHandler.setContext(cnt);
        map = ViewMapaHandler.generateMap();

        x = 0;

        //CANVI DE COLOR I TAMANY DE LA VARIABLE DE CONTAR EL TEMPS
        paint.setColor(Color.BLACK);
        paint.setTextSize(59);
        //AGAFA EL TEMPS ACTUAL
        starttime = System.currentTimeMillis();
        prs = ViewMapaHandler.generatePersonatge();

        //inicialitzem l'array de les vides
        vides = new ArrayList<Bitmap>();
        vides = prs.getVides();
        prs.setNumVides(2);


        creu = CanvasUtils.loadBitmapFromString(cnt, "cruzroja");
        tic = CanvasUtils.loadBitmapFromString(cnt, "tic");

        moviment = false;
        pause = false;

        fons = new ArrayList<Bitmap>();
       // fons = CanvasUtils.escalaImatge(map.getFons(), map.getAlcada() + 3, map.getAmplada());
        //MAPA 1
        obj = ViewMapaHandler.generateObjecteJoc();


        //PERSONATGE
        prs.setAlcada(map.getAlcada() / 4);
        float amplada = ((float)prs.getAlcada()*CanvasUtils.calcularFactorEscalatge(prs.getImg()));
        prs.setAmplada((int) amplada);
        prs.setX(CanvasUtils.getWidthScreen() * 0.2f);
        prs.setY(CanvasUtils.getHeightScreen() * 0.6f);
        fr = (float)prs.getAmplada() /(float) prs.getImg().getWidth();
        prs.setGifX(prs.getX() /fr );
        pl = (float)prs.getAlcada()/(float)prs.getImg().getHeight();
        prs.setGifY(prs.getY() / pl);


        //NOIA1
        obj.get(0).setAlcada(map.getAlcada() / 3);
        amplada = ((float)obj.get(0).getAlcada()*0.75f);
        obj.get(0).setAmplada((int) amplada);
        obj.get(0).setY(map.getAlcada() / 2);
        obj.get(0).setX(map.getAmplada() * 0.15f);


        //BOTIGA
        obj.get(1).setAlcada((int) (map.getAlcada() * 0.8));
        amplada = ((float)obj.get(1).getAlcada()*0.73f);
        obj.get(1).setAmplada((int) amplada);
        obj.get(1).setY(map.getAlcada() / 6);
        obj.get(1).setX((map.getAmplada() * 0.25f));

        //BARCO2
        obj.get(3).setAlcada((int) (map.getAlcada() * 0.15));
        amplada = ((float)obj.get(3).getAlcada()*0.92f);
        obj.get(3).setAmplada((int) amplada);
        obj.get(3).setY((float) (map.getAlcada() * 0.25));
        obj.get(3).setX((map.getAmplada() * 0.15f));
   /*     //
        obj.get(3).setX(CanvasUtils.getWidthScreen() / 2);
        obj.get(3).setY(CanvasUtils.getHeightScreen() / 2 - 50);
*/

        //OBSTACLE
        map.getObstacles().setY1((int) (CanvasUtils.getHeightScreen() * 0.094));
        map.getObstacles().setY2((int) (CanvasUtils.getHeightScreen() * 0.26));





        //GIF cadira
        escalaImatges();



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

    //FUNCIO PER ESCALAR LES IMATGES
    private void escalaImatges(){
        //ESCALAR IMATGE MAPA :0
        fons.add(CanvasUtils.escalaImatge(map.getFons(), map.getAlcada() + 3, map.getAmplada()));
        //ESCALAR IMATGE DEL PERSONATGE :1
        fons.add(CanvasUtils.escalaImatge(prs.getImg(), prs.getAlcada(), prs.getAmplada()));

        //ESCALAR OBJECTES JOC
        //ESCALAR NOIA1 :2
        fons.add(CanvasUtils.escalaImatge(obj.get(0).getImg(), obj.get(0).getAmplada(), obj.get(0).getAlcada()));
        //ESCALAR BOTIGA3 :3
        fons.add(CanvasUtils.escalaImatge(obj.get(1).getImg(), obj.get(1).getAmplada(), obj.get(1).getAlcada()));
        //ESCALAR BARCO2 :4
        fons.add(CanvasUtils.escalaImatge(obj.get(3).getImg(), obj.get(3).getAmplada(), obj.get(3).getAlcada()));
        //ESCALAR CREU :5
        fons.add(CanvasUtils.escalaImatge(creu,CanvasUtils.getWidthScreen()/4,CanvasUtils.getWidthScreen()/4));
        //ESCALAR TIC :6
        fons.add(CanvasUtils.escalaImatge(tic, CanvasUtils.getWidthScreen()/4, CanvasUtils.getWidthScreen()/4));
        //ESCALAR VIDES
        //1 VDIA :7
        fons.add(CanvasUtils.escalaImatge(vides.get(0), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()));
        //2 VIDES :8
        fons.add(CanvasUtils.escalaImatge(vides.get(1), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()));


    }


    private void update() {

        //SI AL PERSONATGE LI QUEDEN VIDES SEGUIM AMB EL JOC
        if (prs.getNumVides()>0) {
            moviment = false;
            if (x > CanvasUtils.getWidthScreen() - map.getAmplada() + prs.getAmplada()) {
                moviment = true;
                x -= 5;
                obj.get(0).setX(obj.get(0).getX() - 5);
                obj.get(1).setX(obj.get(1).getX() - 5);
                obj.get(3).setX(obj.get(3).getX() - 5);
            }

            if (correcte) {
                moviment = true;
                if (prs.getGifX() < CanvasUtils.getWidthScreen() * 0.75) {
                    prs.setGifX(prs.getGifX() + 5);
                } else if (prs.getGifX() < CanvasUtils.getWidthScreen() * 0.9 && prs.getGifY() > CanvasUtils.getHeightScreen() * 0.8) {
                    prs.setGifX(prs.getGifX() + 5);
                    prs.setGifY(prs.getGifY() + 4);
                } else {
                    prs.setGifX(prs.getGifX() + 5);

                }
            }


            //SI EL PERSONATGE PERD TOTES LES VIDES REINICIEM LES VARIABLES
        }else{

            x=0;
            obj.get(0).setY(map.getAlcada() / 2);
            obj.get(0).setX(map.getAmplada() * 0.15f);
            obj.get(1).setY(map.getAlcada() / 6);
            obj.get(1).setX((map.getAmplada() * 0.25f));
            obj.get(3).setY((float) (map.getAlcada() * 0.25));
            obj.get(3).setX((map.getAmplada() * 0.15f));
            prs.setNumVides(2);

        }
        //VELOCITAT DELS OBJECTES NO MOVILS
        //obj.get(0).setX(obj.get(0).getX()-5);
        //obj.get(1).setX(obj.get(1).getX()-5);


        //if(obj.get(0).getX()>0-obj.get(0).getAmplada()*2)obj.get(0).setX(obj.get(0).getX() - 8);
       // if(obj.get(1).getX()<map.getAmplada())obj.get(1).setX(obj.get(1).getX() + 8);


    }


    private void doDraw(Canvas c) throws InterruptedException {
        //drawBitmap(img,x,y,null)
        //canvasutils(img, height, width)
        if(clicado){
            sleep(3000);
            clicado=false;
        }
        //MAPA
        c.drawBitmap(fons.get(0), x, -1, null);



        //OBJECTES
        c.drawBitmap(fons.get(2), obj.get(0).getX(), obj.get(0).getY(), null);
        c.drawBitmap(fons.get(3), obj.get(1).getX(), obj.get(1).getY(), null);
        c.drawBitmap(fons.get(4), obj.get(3).getX(), obj.get(3).getY(), null);
        if (prs.getNumVides() == 2){
            c.drawBitmap(fons.get(8),0,0,null);
        }else {
            c.drawBitmap(fons.get(7), 0, 0, null);
        }

        //c.drawBitmap(CanvasUtils.escalaImatge(obj.get(3).getImg(),obj.get(3).getAmplada(), obj.get(3).getAlcada()), obj.get(3).getX(), obj.get(3).getY(), null);


        // c.drawBitmap(CanvasUtils.escalaImatge(obj.get(0).getImg(), obj.get(0).getAmplada(), obj.get(0).getAlcada()), obj.get(0).getX(), obj.get(0).getY(), null);
      //  c.drawBitmap(CanvasUtils.escalaImatge(obj.get(1).getImg(),obj.get(1).getAmplada(), obj.get(1).getAlcada()), obj.get(1).getX(), obj.get(1).getY(), null);
        //c.drawBitmap(CanvasUtils.escalaImatge(obj.get(2).getImg(),obj.get(2).getAmplada(), obj.get(2).getAlcada()), obj.get(2).getX(), obj.get(2).getY(), null);
        //c.drawBitmap(CanvasUtils.escalaImatge(obj.get(3).getImg(),obj.get(3).getAmplada(), obj.get(3).getAlcada()), obj.get(3).getX(), obj.get(3).getY(), null);

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
            c.scale(fr,pl);
            //dibuixem el GIF
            //prs.getGifPrs().getMovie().draw(c,50,CanvasUtils.getHeightScreen() - CanvasUtils.getHeightScreen() / 3);

            prs.getGifPrs().getMovie().draw(c, prs.getGifX(), prs.getGifY());
            c.restore();
            }
        else {//si el personatge no és mou
            c.drawBitmap(fons.get(1), prs.getX(), prs.getY() + 5, null);
            c.drawBitmap(CanvasUtils.escalaImatge(map.getObstacles().getRespostes(), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()), 0, 0, null);
            clic = true;

            //MIREM SI EL CLIC ESTA DINS DE LES POSSIBLES SOLUCIONS
            
            if (MapActivity.x!= pastX || MapActivity.y != pastY){
                pastX = MapActivity.x;
                pastY = MapActivity.y;
                if (MapActivity.x>50 && MapActivity.x<235 && MapActivity.y>map.getObstacles().getY1() && MapActivity.y<map.getObstacles().getY2()) {
                    c.drawBitmap(fons.get(5), 50, 115, null);
                    clicado=true;
                    clic = false;
                    prs.setNumVides(prs.getNumVides()-1);
                }
                if (MapActivity.x>295 && MapActivity.x<480 && MapActivity.y>map.getObstacles().getY1() && MapActivity.y<map.getObstacles().getY2()){
                    c.drawBitmap(fons.get(5), 295, 115, null);
                    clicado = true;
                    clic = false;
                    prs.setNumVides(prs.getNumVides()-1);
                }
                if (MapActivity.x>530 && MapActivity.x<715 && MapActivity.y>map.getObstacles().getY1() && MapActivity.y<map.getObstacles().getY2()){
                    c.drawBitmap(fons.get(6), 530, 115, null);
                    clicado = true;
                    clic = false;
                    correcte = true;
                }
                Log.d("X", "X" + Float.toString(MapActivity.x));
                Log.d("Y","Y"+Float.toString(MapActivity.y));
            }
        }

        //CALCULAR I DIBUIXAR TEMPS
        long segons = (System.currentTimeMillis() - starttime) / 1000;
        c.drawText(String.valueOf(segons),50,50,paint);
    }



    public void setRunning(boolean b) {
        mRun = b;
    }


    public void clearCanvasObjects() {
        map.setFons(null);
        prs.setImg(null);
        map = null;
        prs = null;

    }

    public int getMapSize() {
        return mapSize;
    }


}
