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
    private boolean pantallaNegra = false;
    private boolean passaTemps;
    private long clickTime;
    private int index = 0;
    private int xTic = 0;
    private int alfa;


    private Personatge prs;
    private Mapa map;
    private boolean moviment;
    private boolean pause;
    private ArrayList<Bitmap> fons;
    private float pl;
    private float fr;
    private ArrayList<ObjecteJoc> obj;
    private boolean clickat;
    private int yTic;
    private boolean primeraVegadaEntra=false;
    private float amplada;


    private Bitmap creu;
    private Bitmap tic;


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


        //INICIALITZEM ELS OBJECTES GLOBALS AL JOC (MAPES, PERSONATGE, VIDES ...)
        initObjectesJoc();


        fons = new ArrayList<Bitmap>();

        obj = ViewMapaHandler.generateObjecteJoc();

        alfa=0;

        //de moment no utilitzem, però s'utilitzarà
        /*//CANVI DE COLOR I TAMANY DE LA VARIABLE DE CONTAR EL TEMPS
        paint.setColor(Color.BLACK);
        paint.setTextSize(59);

        //AGAFA EL TEMPS ACTUAL
        starttime = System.currentTimeMillis();
        **/



        // fons = CanvasUtils.escalaImatge(map.getFons(), map.getAlcada() + 3, map.getAmplada());
        //MAPA 1

        ViewMapaHandler.setClic(false);


        initObjMapa1();


        initCoordsObjects();



        initTouch();
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

    //--------------------------------------------------------------------------------------------------------
    //FUNCIONS THREAD
    public void pause() {
        this.pause = true;
    }

    public void torna() {
        this.pause = false;
    }

    private void update() {
        updateMapa1();
    }

    private void doDraw(Canvas c) throws InterruptedException {
        //drawBitmap(img,x,y,null)
        //canvasutils(img, height, width)
        if (!pantallaNegra) {
            mostraMapa1(c);
        }else {
            pintaNegra(c);
        }
    }

    //FI FUNCIONS THREAD
    //-------------------------------------------------------------------------------------------------------



    //FUNCIONS GLOBALS AL JOC -------------------------------------------------------------------------------
    private void initObjectesJoc(){
        map = ViewMapaHandler.generateMap();
        prs = ViewMapaHandler.generatePersonatge().get(0);

        //inicialitzem l'array de les vides
        vides = new ArrayList<Bitmap>();
        vides = prs.getVides();
        prs.setNumVides(2);

        //CARREGUEM LES IMATGES DEL TIC I LA CREU
        creu = CanvasUtils.loadBitmapFromString(cnt, "cruzroja");
        tic = CanvasUtils.loadBitmapFromString(cnt, "tic");

        passaTemps = false;
        moviment = false;
        pause = false;


        //PERSONATGE
        prs.setAlcada(map.getAlcada() / 4);
        float amplada = ((float) prs.getAlcada() * CanvasUtils.calcularFactorEscalatge(prs.getImg()));
        prs.setAmplada((int) amplada);
        prs.setX(prs.getAmplada() / 2);
        prs.setY(map.getAlcada() * 0.6f);
        fr = (float) prs.getAmplada() / (float) prs.getImg().getWidth();
        prs.setGifX(prs.getX() / fr);
        pl = (float) prs.getAlcada() / (float) prs.getImg().getHeight();
        prs.setGifY(prs.getY() / pl);

    }


    //FUNCIO PER PINTAR LA PANTALLA NEGRE QUAN S'HA ACAVAT EL NIVELL
    private void pintaNegra(Canvas c){
        if(alfa<100)alfa++;
        paint.setAlpha(alfa);
        paint.setColor(Color.BLACK);
        c.drawARGB(alfa,0,0,0);

    }

    //funció per calcular el temps dels tics
    private void comptaTempsTic(int segons,Canvas c){
        if (passaTemps) {
            clickat = true;

            ViewMapaHandler.setClic(false);
            pintaTic(index, xTic, yTic, c);
            long sec = (System.currentTimeMillis() - clickTime) / 1000;
            Log.i("entra temps",String.valueOf(sec));
            Log.i("t2",String.valueOf(segons));

            if (segons <sec) {
                clickat = !clickat;
                passaTemps = false;
                ViewMapaHandler.setClic(true);
            }

        }
    }

    public void pintaTic(int index,int x,int y,Canvas c){
        c.drawBitmap(fons.get(index), x, y, null);

    }


    //FI FUNCIONS GLOBALS AL JOC ----------------------------------------------------------



    /**************************************************************************************************************************
     *  FUNCIONS MAPA 1
      *************************************************************************************************************************/


    //INICIALITZEM ELS OBJECTES DEL MAPA 1
    private void initObjMapa1(){
        //NOIA1
        obj.get(0).setAlcada(map.getAlcada() / 3);
        amplada = ((float) obj.get(0).getAlcada() * 0.75f);
        obj.get(0).setAmplada((int) amplada);



        //BOTIGA
        obj.get(1).setAlcada((int) (map.getAlcada() * 0.8));
        amplada = ((float) obj.get(1).getAlcada() * 0.73f);
        obj.get(1).setAmplada((int) amplada);


        //BARCO2
        obj.get(3).setAlcada((int) (map.getAlcada() * 0.15));
        amplada = ((float) obj.get(3).getAlcada() * 0.92f);
        obj.get(3).setAmplada((int) amplada);


    }

    //FUNCIO PER ESCALAR LES IMATGES
    private void escalaImatges() {

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
        fons.add(CanvasUtils.escalaImatge(creu, CanvasUtils.getWidthScreen() / 4, CanvasUtils.getWidthScreen() / 4));
        //ESCALAR TIC :6
        fons.add(CanvasUtils.escalaImatge(tic, CanvasUtils.getWidthScreen() / 4, CanvasUtils.getWidthScreen() / 4));
        //ESCALAR VIDES
        //1 VDIA :7
        fons.add(CanvasUtils.escalaImatge(vides.get(0), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()));
        //2 VIDES :8
        fons.add(CanvasUtils.escalaImatge(vides.get(1), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()));

        //SEGONA IMATGE DEL MAPA :9
        fons.add(CanvasUtils.escalaImatge(map.getFons2(), map.getAlcada() + 3, map.getAmplada()));

    }

    //CONFIGURACIO DEL TOUCH
    private void initTouch(){

        //CONFIGURACIO DEL TOUCH

        map.getObstacles().setY1((int) (CanvasUtils.getHeightScreen() * 0.094));
        map.getObstacles().setY2((int) (CanvasUtils.getHeightScreen() * 0.26));

        map.getObstacles().setX11Dolenta((int) (CanvasUtils.getWidthScreen() * 0.07));
        map.getObstacles().setX12Dolenta((int) (CanvasUtils.getWidthScreen() * 0.31));

        map.getObstacles().setX21Dolenta((int) (CanvasUtils.getWidthScreen() * 0.385));
        map.getObstacles().setX22Dolenta((int) (CanvasUtils.getWidthScreen() * 0.62));

        map.getObstacles().setX11Bona((int) (CanvasUtils.getWidthScreen() * 0.69));
        map.getObstacles().setX12Bona((int) (CanvasUtils.getWidthScreen() * 0.93));

        yTic=(int) (CanvasUtils.getHeightScreen()*0.089);

        map.getObstacles().setY1((int) (CanvasUtils.getHeightScreen() * 0.094));
        map.getObstacles().setY2((int) (CanvasUtils.getHeightScreen() * 0.26));
    }


    //INICIALITZACIO DE LES POSICIONS INICIALS DELS OBJECTES DEL MAPA 1
    private void initCoordsObjects(){
        map.setX(0);
        map.setY(-1);
        map.setVelX((int) (map.getAmplada() * 0.005f));
        obj.get(0).setY(map.getAlcada() / 2);
        obj.get(0).setX(map.getAmplada() * 0.15f);
        obj.get(0).setVelX(map.getVelX());
        obj.get(1).setY(map.getAlcada() / 6);
        obj.get(1).setX((map.getAmplada() * 0.25f));
        obj.get(1).setVelX(map.getVelX());
        obj.get(3).setY((float) (map.getAlcada() * 0.25));
        obj.get(3).setX((map.getAmplada() * 0.15f));
        obj.get(3).setVelX(map.getVelX());
        prs.setVelX(map.getVelX());
        prs.setVelY((int)(prs.getVelX()*0.3f));
    }

    //funció per reiniciar els valors de tots els objectes del mapa
    private void restartValuesMap1(){
        initCoordsObjects();
        prs.setNumVides(2);
        correcte=false;
        ViewMapaHandler.setClic(false);
        primeraVegadaEntra=false;
    }



    /**************************************************************************************************************
     * UPDATE DEL MAPA 1
     **************************************************************************************************************/

    private void updateMapa1(){
        if(!pantallaNegra){

            if (!passaTemps) {
                //SI AL PERSONATGE LI QUEDEN VIDES SEGUIM AMB EL JOC
                if (prs.getNumVides() > 0) {
                    moviment = false;

                    //if (x > CanvasUtils.getWidthScreen() - map.getAmplada() + (prs.getAmplada() / 2)) {
                    if (map.getX()> map.getAmplada()*-0.5){
                        moviment = true;
                        map.setX(map.getX()-map.getVelX());

                        obj.get(0).setX(obj.get(0).getX() - obj.get(0).getVelX());
                        obj.get(1).setX(obj.get(1).getX() - obj.get(1).getVelX());
                        obj.get(3).setX(obj.get(3).getX() - obj.get(3).getVelX());
                    }


                    if (correcte) {

                        moviment = true;
                        if (map.getX() > map.getAmplada() * -0.55) {
                            map.setX(map.getX()-map.getVelX());
                            obj.get(0).setX(obj.get(0).getX() - obj.get(0).getVelX());
                            obj.get(1).setX(obj.get(1).getX() - obj.get(1).getVelX());
                            obj.get(3).setX(obj.get(3).getX() - obj.get(3).getVelX());


                        } else {
                            prs.setGifX(prs.getGifX() + prs.getVelX());


                            if (prs.getGifX() > map.getAmplada()*0.15f && prs.getGifX()<map.getAmplada()*0.38f) {
                                prs.setGifY(prs.getGifY() + prs.getVelY());

                            }

                            if (prs.getGifX()-prs.getAmplada()*3 > CanvasUtils.getWidthScreen()) {

                                pantallaNegra = true;

                            }
                        }
                    }

                    //SI EL PERSONATGE PERD TOTES LES VIDES REINICIEM LES VARIABLES
                } else {

                    restartValuesMap1();


                }
            }
        }

    }




    /**********************************************************************************************************
     * DRAW DEL MAPA 1
     *******************************************************************************************************/

    public void mostraMapa1(Canvas c){
        //MAPA
        if (!correcte) {
            c.drawBitmap(fons.get(0), map.getX(), map.getY(), null);
        } else {
            c.drawBitmap(fons.get(9), map.getX(), map.getY(), null);
        }


        //OBJECTES
        c.drawBitmap(fons.get(2), obj.get(0).getX(), obj.get(0).getY(), null);
        c.drawBitmap(fons.get(3), obj.get(1).getX(), obj.get(1).getY(), null);
        c.drawBitmap(fons.get(4), obj.get(3).getX(), obj.get(3).getY(), null);
        if (prs.getNumVides() == 2) {
            c.drawBitmap(fons.get(8), 0, 0, null);
        } else {
            c.drawBitmap(fons.get(7), 0, 0, null);
        }


        if (moviment) {//si el personatge esta en moviment
            //cuadrar el temps del GIF amb el temps del joc
            final long now = SystemClock.uptimeMillis();//s'obté el temps actual
            if (prs.getTempsInici() == 0) {
                prs.setTempsInici(now);
            }

            int dur = prs.getGifPrs().getMovie().duration();
            if (dur == 0) dur = 1000;
            int relTime = (int) ((now - prs.getTempsInici()) % dur);
            prs.getGifPrs().getMovie().setTime(relTime);
            //fi de la sincronització temporal
            c.scale(fr, pl);
            //dibuixem el GIF
            prs.getGifPrs().getMovie().draw(c, prs.getGifX(), prs.getGifY());
            c.restore();
        } else {//si el personatge no és mou
            if(!primeraVegadaEntra){
                ViewMapaHandler.setClic(true);
            }
            primeraVegadaEntra=true;

            c.drawBitmap(fons.get(1), prs.getX(), prs.getY() + 5, null);
            c.drawBitmap(CanvasUtils.escalaImatge(map.getObstacles().getRespostes(), CanvasUtils.getHeightScreen(), CanvasUtils.getWidthScreen()), 0, 0, null);
            //variable per desactivar el touch



            //MIREM SI EL CLIC ESTA DINS DE LES POSSIBLES SOLUCIONS
            comprovarTouchMapa1();


            ViewMapaHandler.setX(0);
            ViewMapaHandler.setY(0);
            Log.i("sec", String.valueOf(passaTemps));

        }
        comptaTempsTic(2, c);



    }


    private void comprovarTouchMapa1(){
        if (ViewMapaHandler.getX() > map.getObstacles().getX11Dolenta() && ViewMapaHandler.getX() < map.getObstacles().getX12Dolenta() && ViewMapaHandler.getY() > map.getObstacles().getY1() && ViewMapaHandler.getY() < map.getObstacles().getY2()){
            prs.setNumVides(prs.getNumVides() - 1);
            clickTime = System.currentTimeMillis();
            passaTemps=true;
            index = 5;
            xTic = (int) (CanvasUtils.getWidthScreen()*0.065);

        }
        if (ViewMapaHandler.getX() > map.getObstacles().getX21Dolenta() && ViewMapaHandler.getX() < map.getObstacles().getX22Dolenta() && ViewMapaHandler.getY() > map.getObstacles().getY1() && ViewMapaHandler.getY() < map.getObstacles().getY2())  {
            prs.setNumVides(prs.getNumVides() - 1);
            clickTime = System.currentTimeMillis();
            passaTemps=true;
            index = 5;
            xTic = (int) (CanvasUtils.getWidthScreen()*0.384);
        }
        if (ViewMapaHandler.getX() > map.getObstacles().getX11Bona() && ViewMapaHandler.getX() < map.getObstacles().getX12Bona() && ViewMapaHandler.getY() > map.getObstacles().getY1() && ViewMapaHandler.getY() < map.getObstacles().getY2()) {
            correcte = true;
            passaTemps = true;
            clickTime = System.currentTimeMillis();
            map.setFons(map.getFons2());
            index = 6;
            xTic = (int) (CanvasUtils.getWidthScreen()* 0.69);
        }

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




}
