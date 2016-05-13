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
    private int mapa;
    
    private int amplaPantalla;
    private int alcadaPantalla;
    private boolean mostraRespostes = false;

    private Personatge prs;
    private ArrayList<Mapa> map;
    private boolean moviment;
    private boolean pause;
    private ArrayList<Bitmap> fons;
    private float pl;
    private float fr;
    private boolean clickat;
    private int yTic;
    private boolean primeraVegadaEntra=false;
    private float amplada;
    private boolean loadImage;


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

        this.amplaPantalla = CanvasUtils.getWidthScreen();
        this.alcadaPantalla = CanvasUtils.getHeightScreen();

        //-------------------------INICIALITZEM ELS OBJECTES GLOBALS AL JOC (MAPES, PERSONATGE, VIDES ...)----------------------------
        initObjectesJoc();
        fons = new ArrayList<Bitmap>();
        alfa=0;
        //-----------------------------------------------------------------------------------------------------------------------------

        //de moment no utilitzem, però s'utilitzarà
        /*//CANVI DE COLOR I TAMANY DE LA VARIABLE DE CONTAR EL TEMPS
        paint.setColor(Color.BLACK);
        paint.setTextSize(59);

        //AGAFA EL TEMPS ACTUAL
        starttime = System.currentTimeMillis();
        **/
        mapa=0;
        initMapes();

        ViewMapaHandler.setClic(false);











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
        switch (mapa){
            case 0:
                updateMapa1();
                break;

            case 1:
                break;
            case 2:
               // updateMapa3();
                break;
        }


    }

    private void doDraw(Canvas c) throws InterruptedException {
        if (!pantallaNegra) {
            switch (mapa){
                case 0:
                    drawMapa1(c);
                    break;
                case 1:
                    break;

                case 2:
                    //drawMapa3(c);
                    break;
            }


        }else {
            pintaNegra(c);
        }

    }

    //FI FUNCIONS THREAD
    //-------------------------------------------------------------------------------------------------------



    //FUNCIONS GLOBALS AL JOC -------------------------------------------------------------------------------

    private void initMapes(){

                //--------INICIALITZEM EL MAPA -----------
                initObjMapa();
                configPersonatge();
                initTouchMapa1();
                escalaImatgesMapa(mapa);


    }

    private void carregaUnaVida(){
        if (!loadImage) {
            recycle(fons.get(7));
            fons.set(7, CanvasUtils.escalaImatge(vides.get(0), alcadaPantalla, amplaPantalla));
            loadImage = true;
        }
    }


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
        loadImage=false;




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
                if (correcte){
                    recycle(fons.get(8));
                }
            }

        }
    }

    public void pintaTic(int index,int x,int y,Canvas c){
        c.drawBitmap(fons.get(index), x, y, null);

    }

    //funcio per controlar la memoria que emprem per als bitmaps
    public void recycle(Bitmap bm){
        if(bm != null){
            bm.recycle();
            bm=null;
        }
    }


    //FUNCIO QUE PINTA EL GIF DEL PERSONATGE MENTRE S'ESTA MOVENT, SI S'ATURA PINTA LA IMATGE DEL PERSONATGE I L'OBSTACLE DEL MAPA CORRESPONENT
    private void pintarObstaclePersonatge(Canvas c) {
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
            if (!primeraVegadaEntra) {
                ViewMapaHandler.setClic(true);
                carregaRespostes(c);
            }
            primeraVegadaEntra = true;

            c.drawBitmap(fons.get(1), prs.getX(), prs.getY() + 5, null);


            c.drawBitmap(fons.get(8), 0, 0, null);
        }
    }


    //FI FUNCIONS GLOBALS AL JOC ----------------------------------------------------------
    private void carregaRespostes(Canvas c){
        loadBitmap(map.get(0).getObstacles().getRespostes(),alcadaPantalla,amplaPantalla,8);
    }

    private void loadBitmap(Bitmap bm, int alcada, int ampl, int ind){
        if (!loadImage){
            if (fons.size()-1 > ind) {
                recycle(fons.get(ind));
                fons.set(ind, CanvasUtils.escalaImatge(bm, alcada, ampl));
            }else{
                fons.add(CanvasUtils.escalaImatge(bm,alcada,ampl));
            }
            loadImage = true;
        }
    }
    

    /**************************************************************************************************************************
     *  FUNCIONS MAPA
      *************************************************************************************************************************/

    private void configMapes(int i){
        //alcada mapa
        map.get(i).setAlcada(alcadaPantalla);
        //amplada mapa
        map.get(i).setAmplada((int) (map.get(i).getAlcada() * map.get(i).getFactorEscalatge()));
        //VELOCITAT MAPES
        map.get(i).calcularVelocitat();
        //set x dels mapes
        map.get(i).setX(0);
        //set y dels mapes
        map.get(i).setY(-1);

    }

    //INICIALITZEM ELS OBJECTES DEL MAPA 1
    private void initObjMapa(){

        //OBJECTES MAPA
        for (int i = 0; i<map.size(); i++){
            configMapes(i);
            for (int j = 0; j<map.get(i).getObjects().size(); j++){
                //calcul alçada
                map.get(i).getObjects().get(j).setAlcada((int) (map.get(i).getAlcada() * map.get(i).getObjects().get(j).getFactorAlcada()));
                //calcul amplada
                map.get(i).getObjects().get(j).setAmplada((int) (map.get(i).getObjects().get(j).getAlcada()*map.get(i).getObjects().get(j).getFactorAmplada()));
                //calcul Y
                map.get(i).getObjects().get(j).setY(map.get(i).getAlcada()*map.get(i).getObjects().get(j).getFactorY());
                //calcul X
                map.get(i).getObjects().get(j).setX(map.get(i).getAmplada() * map.get(i).getObjects().get(j).getFactorX());
                //calcul velocitat
                map.get(i).getObjects().get(j).setVelX(map.get(i).getVelX());
            }
        }

    }

    //FUNCIO PER ESCALAR LES IMATGES
    private void escalaImatgesMapa(int k) {

        //ESCALAR IMATGE MAPA :0
        fons.add(CanvasUtils.escalaImatge(map.get(k).getFons(), map.get(k).getAlcada() + 3, map.get(k).getAmplada()));

        //ESCALAR IMATGE DEL PERSONATGE :1
        fons.add(CanvasUtils.escalaImatge(prs.getImg(), prs.getAlcada(), prs.getAmplada()));

        //ESCALAR OBJECTES DEL MAPA K
        for (int i = 0;i<map.get(k).getObjects().size()  ;i++){
            fons.add(CanvasUtils.escalaImatge(map.get(k).getObjects().get(i).getImg(), map.get(k).getObjects().get(i).getAlcada(), map.get(k).getObjects().get(i).getAmplada()));
        }


        //ESCALAR CREU :5
        fons.add(CanvasUtils.escalaImatge(creu, amplaPantalla / 4, amplaPantalla / 4));
        //ESCALAR TIC :6
        fons.add(CanvasUtils.escalaImatge(tic, amplaPantalla / 4, amplaPantalla / 4));

        //2 VIDES :8
        fons.add(CanvasUtils.escalaImatge(vides.get(1), alcadaPantalla, amplaPantalla));

        

    }



    //CONFIGURACIO DEL TOUCH
    private void initTouchMapa1(){

        //CONFIGURACIO DEL TOUCH

        map.get(0).getObstacles().setY1((int) (alcadaPantalla * 0.094));
        map.get(0).getObstacles().setY2((int) (alcadaPantalla * 0.26));

        map.get(0).getObstacles().setX11Dolenta((int) (amplaPantalla * 0.07));
        map.get(0).getObstacles().setX12Dolenta((int) (amplaPantalla * 0.31));

        map.get(0).getObstacles().setX21Dolenta((int) (amplaPantalla * 0.385));
        map.get(0).getObstacles().setX22Dolenta((int) (amplaPantalla * 0.62));

        map.get(0).getObstacles().setX11Bona((int) (amplaPantalla * 0.69));
        map.get(0).getObstacles().setX12Bona((int) (amplaPantalla * 0.93));

        yTic=(int) (alcadaPantalla*0.089);

        map.get(0).getObstacles().setY1((int) (alcadaPantalla * 0.094));
        map.get(0).getObstacles().setY2((int) (alcadaPantalla * 0.26));
    }


    //INICIALITZACIO DE LES POSICIONS INICIALS DELS OBJECTES DEL MAPA 1
    private void configPersonatge(){



        //PERSONATGE
        prs.setAlcada(map.get(0).getAlcada() / 4);
        float amplada = ((float) prs.getAlcada() * CanvasUtils.calcularFactorEscalatge(prs.getImg()));
        prs.setAmplada((int) amplada);
        prs.setX(prs.getAmplada() / 2);
        prs.setY(map.get(0).getAlcada() * 0.6f);
        fr = (float) prs.getAmplada() / (float) prs.getImg().getWidth();
        prs.setGifX(prs.getX() / fr);
        pl = (float) prs.getAlcada() / (float) prs.getImg().getHeight();
        prs.setGifY(prs.getY() / pl);

        prs.setVelX(map.get(0).getVelX());
        prs.setVelY((int) (prs.getVelX() * 0.3f));
        prs.setCoords(prs.getX());//coordenades relatives al mapa
    }

    //funció per reiniciar els valors de tots els objectes del mapa
    private void restartValuesMap1(){
        //initCoordsObjectsMapa1();
        prs.setNumVides(2);
        correcte=false;
        ViewMapaHandler.setClic(false);
        primeraVegadaEntra=false;
    }



    /**************************************************************************************************************
     * UPDATE DEL MAPA 1
     **************************************************************************************************************/

    private void endevant(){
        moviment = true;
        //moviment del mapa
        map.get(0).setX(map.get(0).getX() - map.get(0).getVelX());
        //moviment dels objectes
        for (int i = 0; i<map.get(0).getObjects().size(); i++){
            map.get(0).getObjects().get(i).setX(map.get(0).getObjects().get(i).getX()-map.get(0).getObjects().get(i).getVelX());
        }
        //posicio del personatge respecte al mapa
        prs.setCoords(prs.getCoords() + map.get(0).getVelX());
    }

    private void updateMapa1(){
        loadImage=false;
        if(!pantallaNegra){
            if (!passaTemps) {
                //SI AL PERSONATGE LI QUEDEN VIDES SEGUIM AMB EL JOC
                if (prs.getNumVides() > 0) {
                    moviment = false;

                    //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
                    if (map.get(0).getX()> map.get(0).getAmplada()*-0.5){
                        endevant();


                    }

                    if (correcte) {

                        if (map.get(0).getX() > map.get(0).getAmplada() * -0.55) {
                           endevant();

                        } else {
                            prs.setGifX(prs.getGifX() + prs.getVelX());

                            Log.i("amplada", String.valueOf(map.get(0).getAmplada()));
                            prs.setCoords(prs.getCoords() + prs.getVelX());

                            if (prs.getCoords() > map.get(0).getAmplada()*0.7f && prs.getCoords()<map.get(0).getAmplada()*0.8f) {
                                prs.setGifY(prs.getGifY() + prs.getVelY());

                            }

                            if (prs.getGifX()-prs.getAmplada()*3 > amplaPantalla) {

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

    public void drawMapa1(Canvas c){
        //MAPA

        c.drawBitmap(fons.get(0), map.get(0).getX(), map.get(0).getY(), null);





        //OBJECTES
        c.drawBitmap(fons.get(2), map.get(0).getObjects().get(0).getX(),map.get(0).getObjects().get(0).getY(), null);
        c.drawBitmap(fons.get(3), map.get(0).getObjects().get(1).getX(), map.get(0).getObjects().get(1).getY(), null);
        c.drawBitmap(fons.get(4), map.get(0).getObjects().get(2).getX(), map.get(0).getObjects().get(2).getY(), null);

        //vides
        c.drawBitmap(fons.get(7), 0, 0, null);


        pintarObstaclePersonatge(c);


        //MIREM SI EL CLIC ESTA DINS DE LES POSSIBLES SOLUCIONS
        comprovarTouchMapa1();



        //Log.i("sec", String.valueOf(passaTemps));

        comptaTempsTic(2, c);



    }

    //FUNCIO PER COMPROVAR EL TOUCH
    private void comprovarTouchMapa1(){
        if (ViewMapaHandler.getX() > map.get(0).getObstacles().getX11Dolenta() && ViewMapaHandler.getX() < map.get(0).getObstacles().getX12Dolenta() && ViewMapaHandler.getY() > map.get(0).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(0).getObstacles().getY2()){
            prs.setNumVides(prs.getNumVides() - 1);
            clickTime = System.currentTimeMillis();
            passaTemps=true;
            index = 5;
            xTic = (int) (amplaPantalla*0.065);
            if(prs.getNumVides()<2){
                carregaUnaVida();
            }
        }
        if (ViewMapaHandler.getX() > map.get(0).getObstacles().getX21Dolenta() && ViewMapaHandler.getX() < map.get(0).getObstacles().getX22Dolenta() && ViewMapaHandler.getY() > map.get(0).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(0).getObstacles().getY2())  {
            prs.setNumVides(prs.getNumVides() - 1);
            clickTime = System.currentTimeMillis();
            passaTemps=true;
            index = 5;
            xTic = (int) (amplaPantalla*0.384);
            if(prs.getNumVides()<2){
                carregaUnaVida();
            }
        }
        if (ViewMapaHandler.getX() > map.get(0).getObstacles().getX11Bona() && ViewMapaHandler.getX() < map.get(0).getObstacles().getX12Bona() && ViewMapaHandler.getY() > map.get(0).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(0).getObstacles().getY2()) {
            correcte = true;
            passaTemps = true;
            clickTime = System.currentTimeMillis();
            map.get(0).setFons(map.get(0).getFons2());
            index = 6;
            if (!loadImage) {
                recycle(fons.get(0));
                fons.set(0,CanvasUtils.escalaImatge(map.get(0).getFons2(), map.get(0).getAlcada(), map.get(0).getAmplada()));
                loadImage = true;
            }
            xTic = (int) (amplaPantalla* 0.69);
        }
        ViewMapaHandler.setX(0);
        ViewMapaHandler.setY(0);

    }


    /*********************************************************************************************************************
     * ------------------------ FUNCIONS DEL MAPA 3 ----------------------------------------------------------------
     *********************************************************************************************************************/

/*


    private void initObjMapa3(){
        obj = ViewMapaHandler.generateObjecteJoc(1);

        //CASA
        obj.get(0).setAlcada((int) (map.get(1).getAlcada() * 0.8));
        amplada = ((float) obj.get(0).getAlcada() * 0.5f);
        obj.get(0).setAmplada((int) amplada);
    }


    private void initCoordsObjectsMapa3(){
        map.get(1).setX(0);
        map.get(1).setY(-1);
        map.get(1).setVelX((int) (map.get(1).getAmplada() * 0.005f));
        obj.get(0).setY((float) (map.get(1).getAlcada() * 0.1));
        obj.get(0).setX(map.get(1).getAmplada() * 0.6f);
        obj.get(0).setVelX(map.get(1).getVelX());

    }

    private void escalaImatgesMapa3 (){
        fons.add(CanvasUtils.escalaImatge(map.get(1).getFons(), map.get(1).getAlcada() + 3, map.get(1).getAmplada()));
        //ESCALAR IMATGE DEL PERSONATGE :1
        fons.add(CanvasUtils.escalaImatge(prs.getImg(), prs.getAlcada(), prs.getAmplada()));

        //ESCALAR OBJECTES JOC
        //ESCALAR CASA :2
        fons.add(CanvasUtils.escalaImatge(obj.get(0).getImg(), obj.get(0).getAmplada(), obj.get(0).getAlcada()));
        //1 VIDA : 3
        fons.add(CanvasUtils.escalaImatge(vides.get(0), alcadaPantalla, amplaPantalla));
        //2 VIDES :4
        fons.add(CanvasUtils.escalaImatge(vides.get(1), alcadaPantalla, amplaPantalla));
    }

    /*********************************************************************************************************************
     * --------------------------   UPDATE DEL MAPA 3  -------------------------------------------------------------------
     *********************************************************************************************************************/
/*

    private void updateMapa3(){
        if(!pantallaNegra){

            if (!passaTemps) {
                //SI AL PERSONATGE LI QUEDEN VIDES SEGUIM AMB EL JOC
                if (prs.getNumVides() > 0) {
                    moviment = false;

                    //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
                    if (map.get(1).getX()> map.get(1).getAmplada()*-0.5){
                        moviment = true;
                        map.get(1).setX(map.get(1).getX() - map.get(1).getVelX());

                      //  obj.get(0).setX(obj.get(0).getX() - obj.get(0).getVelX());

                    }

/*
                    if (correcte) {

                        moviment = true;
                        if (map.get(0).getX() > map.get(0).getAmplada() * -0.55) {
                            map.get(0).setX(map.get(0).getX() - map.get(0).getVelX());
                            obj.get(0).setX(obj.get(0).getX() - obj.get(0).getVelX());
                            obj.get(1).setX(obj.get(1).getX() - obj.get(1).getVelX());
                            obj.get(3).setX(obj.get(3).getX() - obj.get(3).getVelX());


                        } else {
                            prs.setGifX(prs.getGifX() + prs.getVelX());


                            if (prs.getGifX() > map.get(0).getAmplada()*0.15f && prs.getGifX()<map.get(0).getAmplada()*0.38f) {
                                prs.setGifY(prs.getGifY() + prs.getVelY());

                            }

                            if (prs.getGifX()-prs.getAmplada()*3 > amplaPantalla) {

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
    }
*/
    /*********************************************************************************************************************
    * --------------------------   DRAW DEL MAPA 3  -------------------------------------------------------------------
    *********************************************************************************************************************/
/*
    private void drawMapa3(Canvas c) {
        Log.i("aloooooooo", "");
        c.drawBitmap(fons.get(0), map.get(1).getX(), map.get(1).getY(), null);

        if (prs.getNumVides() == 2) {
            c.drawBitmap(fons.get(3), 0, 0, null);
        } else {
            c.drawBitmap(fons.get(4), 0, 0, null);
        }

        pintarObstaclePersonatge(c, 1);

    }

*/
    public void setRunning(boolean b) {
        mRun = b;
    }


    public void clearCanvasObjects() {
        for (int i = 0; i<fons.size(); i++){
            recycle(fons.get(i));
        }

    }




}
