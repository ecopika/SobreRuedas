package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Collections;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
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
    private int resposta;

    private int amplaPantalla;
    private int alcadaPantalla;
    private float accMetro;
    private boolean metroEnPantalla;
    private boolean metroAturat;
    private boolean primeraVegadaThread;
    private ArrayList<Integer> auxiliarNumMapes;

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
    private boolean loadImage;
    private int alfaText;
    private Paint pText;
    private ArrayList<Integer> numMapes;
    private String frase;
    private Bitmap finalFons;
    private boolean finalJoc;

    private Bitmap creu;
    private Bitmap tic;

    private boolean correcte = false;
    Paint paint = new Paint();




    public JocThread(SurfaceHolder sHold, Context cnt, Handler handler) {
        mSurfHolder = sHold;
        hndl = handler;
        this.cnt = cnt;
        ViewMapaHandler.setActivity((MapActivity) this.cnt);
        ViewMapaHandler.setContext(cnt);

        this.amplaPantalla = CanvasUtils.getWidthScreen();
        this.alcadaPantalla = CanvasUtils.getHeightScreen();
        numMapes = new ArrayList<Integer>();
        finalJoc = false;

        //-------------------------INICIALITZEM ELS OBJECTES GLOBALS AL JOC (MAPES, PERSONATGE, VIDES ...)----------------------------
        fons = new ArrayList<Bitmap>();
        alfa=0;
        alfaText = 0;

        pText = new Paint();
        frase = "Al cap d'una estona..";
        pText.setTextSize((amplaPantalla/frase.length())*2);
        pText.setColor(Color.WHITE);
        //-----------------------------------------------------------------------------------------------------------------------------

        //de moment no utilitzem, però s'utilitzarà
        /*//CANVI DE COLOR I TAMANY DE LA VARIABLE DE CONTAR EL TEMPS
        paint.setColor(Color.BLACK);
        paint.setTextSize(59);
        //AGAFA EL TEMPS ACTUAL
        starttime = System.currentTimeMillis();
        **/
        primeraVegadaThread=true;
        generateMapa();
        generatePersonatge();

        restartValuesMap();



        ViewMapaHandler.setClic(false);

    }

    private void generateMapa(){
        map = ViewMapaHandler.generateMap();
        for(int i=0;i<map.size();i++){
            numMapes.add(i);
        }
        Collections.shuffle(numMapes);
    }

    private void generatePersonatge(){
        prs = ViewMapaHandler.generatePersonatge().get(0);
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
        updateMapa();


    }

    private void doDraw(Canvas c) throws InterruptedException {
       if(!finalJoc) {
           if (!pantallaNegra) {
               drawMapa(c);


           } else {
               pintaNegra(c);

           }
       }
       else{
            mostraFinal(c);
        }

    }

    //FI FUNCIONS THREAD
    //-------------------------------------------------------------------------------------------------------



    //FUNCIONS GLOBALS AL JOC -------------------------------------------------------------------------------

    private void initMapes(){
        //--------INICIALITZEM EL MAPA -----------
        initObjectesJoc();
        initObjMapa();
        if (mapa == 1){
            configMetro();
        }
        configPersonatge();
        initTouchMapa();
        escalaImatgesMapa(mapa);


    }

    private void carregaUnaVida(){
        if (!loadImage) {
            recycle(fons.get(map.get(mapa).getObjects().size() + 4));
            fons.set(map.get(mapa).getObjects().size()+4, CanvasUtils.escalaImatge(prs.getVides().get(0), alcadaPantalla, amplaPantalla));
            loadImage = true;
        }
    }


    private void initObjectesJoc(){

        passaTemps = false;
        moviment = false;
        pause = false;
        loadImage=false;




    }

    private void configMetro(){
        accMetro = map.get(1).getVelX()*0.001666667f;
        metroAturat = false;
    }

    private void loadTics(){
        //CARREGUEM LES IMATGES DEL TIC I LA CREU
        recycle(creu);
        recycle(tic);
        creu=null;
        tic=null;
        creu = CanvasUtils.loadBitmapFromString(cnt, "cruzroja");
        tic = CanvasUtils.loadBitmapFromString(cnt, "tic");
    }

    //FUNCIO PER PINTAR LA PANTALLA NEGRE QUAN S'HA ACAVAT EL NIVELL
    private void pintaNegra(Canvas c){

            if (alfa < 100) alfa++;
            paint.setAlpha(alfa);
            paint.setColor(Color.BLACK);
            c.drawARGB(alfa, 0, 0, 0);
        if(numMapes.size()>0) {
            if (alfa == 100) {
                if (alfaText < 100) alfaText++;
                pText.setAlpha(alfaText);
                c.drawText(frase, 5, alcadaPantalla / 2, pText);
            }
        }

    }

    //funció per calcular el temps dels tics
    private void comptaTempsTic(int segons,Canvas c){
        if (passaTemps) {
            clickat = true;

            ViewMapaHandler.setClic(false);
            pintaTic(index, xTic, yTic, c);
            long sec = (System.currentTimeMillis() - clickTime) / 1000;
            if (segons <sec) {
                clickat = !clickat;
                passaTemps = false;
                ViewMapaHandler.setClic(true);
                if (correcte){
                    recycle(fons.get(map.get(mapa).getObjects().size()+5));
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

    //funció que mostra la pantalla final del joc
    private void mostraFinal(Canvas c){
        finalFons = ((BitmapDrawable)cnt.getResources().getDrawable(R.drawable.pantallafinal)).getBitmap();
        c.drawBitmap(CanvasUtils.escalaImatge(finalFons,alcadaPantalla,amplaPantalla),0,0,null);

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

            c.scale(1/fr, 1/pl);


        } else {//si el personatge no és mou
            if (!primeraVegadaEntra) {
                ViewMapaHandler.setClic(true);
                carregaRespostes(c);
            }
            primeraVegadaEntra = true;

            c.drawBitmap(fons.get(1), prs.getX(), prs.getY() + 5, null);


            c.drawBitmap(fons.get(map.get(mapa).getObjects().size()+5), 0, 0, null);
        }
    }


    //FI FUNCIONS GLOBALS AL JOC ----------------------------------------------------------
    private void carregaRespostes(Canvas c){
        loadBitmap(map.get(mapa).getObstacles().getRespostes(), alcadaPantalla, amplaPantalla, 8);
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
        map.get(i).setAmplada(map.get(i).getAlcada() * map.get(i).getFactorEscalatge());
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
                map.get(i).getObjects().get(j).setAlcada( (map.get(i).getAlcada() * map.get(i).getObjects().get(j).getFactorAlcada()));
                //calcul amplada
                map.get(i).getObjects().get(j).setAmplada( (map.get(i).getObjects().get(j).getAlcada()*map.get(i).getObjects().get(j).getFactorAmplada()));
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
        recycle(map.get(k).getFons());
        //ESCALAR IMATGE DEL PERSONATGE :1
        fons.add(CanvasUtils.escalaImatge(prs.getImg(), prs.getAlcada(), prs.getAmplada()));

        //ESCALAR OBJECTES DEL MAPA K
        for (int i = 0;i<map.get(k).getObjects().size()  ;i++){
            fons.add(CanvasUtils.escalaImatge(map.get(k).getObjects().get(i).getImg(), map.get(k).getObjects().get(i).getAlcada(), map.get(k).getObjects().get(i).getAmplada()));
            recycle(map.get(k).getObjects().get(i).getImg());
        }


        //ESCALAR CREU :5
        fons.add(CanvasUtils.escalaImatge(creu, amplaPantalla / 4, amplaPantalla / 4));
        recycle(creu);
        //ESCALAR TIC :6
        fons.add(CanvasUtils.escalaImatge(tic, amplaPantalla / 4, amplaPantalla / 4));
        recycle(tic);
        //2 VIDES :8
        if(prs.getNumVides()==2) {
            fons.add(CanvasUtils.escalaImatge(prs.getVides().get(1), alcadaPantalla, amplaPantalla));
        }
        else{
            fons.add(CanvasUtils.escalaImatge(prs.getVides().get(0), alcadaPantalla, amplaPantalla));

        }


    }

    private void regenerateBitmaps(){
        map.get(mapa).loadFons1();
        regenerateObjectsMapa();
        loadTics();

    }

    private void regenerateObjectsMapa(){
        for(int i = 0;i<map.get(mapa).getObjects().size();i++){
            map.get(mapa).getObjects().get(i).loadImatge();
        }

    }



    //CONFIGURACIO DEL TOUCH
    private void initTouchMapa(){

        //CONFIGURACIO DEL TOUCH

        map.get(mapa).getObstacles().setY1((int) (alcadaPantalla * 0.094));
        map.get(mapa).getObstacles().setY2((int) (alcadaPantalla * 0.26));

        map.get(mapa).getObstacles().setX11((int) (amplaPantalla * 0.07));
        map.get(mapa).getObstacles().setX12((int) (amplaPantalla * 0.31));

        map.get(mapa).getObstacles().setX21((int) (amplaPantalla * 0.385));
        map.get(mapa).getObstacles().setX22((int) (amplaPantalla * 0.62));

        map.get(mapa).getObstacles().setX31((int) (amplaPantalla * 0.69));
        map.get(mapa).getObstacles().setX32((int) (amplaPantalla * 0.93));

        yTic=(int) (alcadaPantalla*0.089);

        map.get(mapa).getObstacles().setY1((int) (alcadaPantalla * 0.094));
        map.get(mapa).getObstacles().setY2((int) (alcadaPantalla * 0.26));
    }


    //INICIALITZACIO DE LES POSICIONS INICIALS DELS OBJECTES DEL MAPA 1
    private void configPersonatge(){



        //PERSONATGE
        prs.setAlcada(map.get(mapa).getAlcada() / 4);
        float amplada = ( prs.getAlcada() * CanvasUtils.calcularFactorEscalatge(prs.getImg()));
        prs.setAmplada( amplada);
        prs.setX(prs.getAmplada() / 2);
        prs.setY(map.get(mapa).getAlcada() * 0.6f);
        fr =  prs.getAmplada() / (float) prs.getImg().getWidth();
        prs.setGifX(prs.getX() / fr);
        pl =  prs.getAlcada() / (float) prs.getImg().getHeight();
        prs.setGifY(prs.getY() / pl);

        prs.setVelX(map.get(mapa).getVelX());
        prs.setVelY((prs.getVelX() * 0.6f));
        prs.setCoords(prs.getX());//coordenades relatives al mapa
    }

    private ArrayList<Integer> cloneArray(ArrayList<Integer> llista){
        ArrayList<Integer> nwLlista = new ArrayList<Integer>();
        for(int i = 0;i<llista.size();i++){
            nwLlista.add(llista.get(i));
        }
        return nwLlista;
    }

    //funció per reiniciar els valors de tots els objectes del mapa
    private void restartValuesMap(){
        //initCoordsObjectsMapa1();

        clearCanvasObjects(0);

            if (primeraVegadaThread) {
                auxiliarNumMapes = cloneArray(numMapes);
                primeraVegadaThread = false;
                prs.setNumVides(2);

            } else {
                if (prs.getNumVides() == 0) {
                    numMapes = cloneArray(auxiliarNumMapes);
                    prs.setNumVides(2);

                }
            }
        if(numMapes.size()==0){
            finalJoc=true;
        }
            if (numMapes.size() > 0) {

                mapa = numMapes.remove(0);
            }

        if(!finalJoc) {
            fons = new ArrayList<Bitmap>();
            regenerateBitmaps();
            initMapes();
            correcte = false;
            ViewMapaHandler.setClic(false);
            primeraVegadaEntra = false;
            if (mapa == 1) {
                metroEnPantalla = true;
            } else {
                metroEnPantalla = false;
            }
            alfa = 0;
            alfaText = 0;

        }
        pantallaNegra = false;

    }



    /**************************************************************************************************************
     * UPDATE DEL MAPA 1
     **************************************************************************************************************/

    private void endevant(){
        moviment = true;
        //moviment del mapa
        map.get(mapa).setX((int) (map.get(mapa).getX() - map.get(mapa).getVelX()));
        //moviment dels objectes
        for (int i = 0; i<map.get(mapa).getObjects().size(); i++){
            map.get(mapa).getObjects().get(i).setX(map.get(mapa).getObjects().get(i).getX()-map.get(mapa).getObjects().get(i).getVelX());
        }
        //posicio del personatge respecte al mapa
        prs.setCoords(prs.getCoords() + map.get(mapa).getVelX());
    }

    private void updateMapa1(){
        moviment = false;

        //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
        if (map.get(0).getX()> map.get(0).getAmplada()*-0.5){
            endevant();


        }

        if (correcte) {

            if (map.get(0).getX() > map.get(0).getAmplada() * -0.55) {
                endevant();

            } else {
                moviment = true;
                prs.setGifX(prs.getGifX() + prs.getVelX());

                prs.setCoords(prs.getCoords() + prs.getVelX());

                if (prs.getCoords() > map.get(0).getAmplada()*0.65f && prs.getCoords()<map.get(0).getAmplada()*0.97f) {
                    prs.setGifY(prs.getGifY() + prs.getVelY()*0.5f);

                }


                if (prs.getGifX()-prs.getAmplada()*3 > amplaPantalla) {

                    pantallaNegra = true;


                }
            }
        }
    }

    private void updateMapa(){
        loadImage=false;
        if(!finalJoc) {
            if (!pantallaNegra) {
                if (!passaTemps) {
                    //SI AL PERSONATGE LI QUEDEN VIDES SEGUIM AMB EL JOC
                    if (prs.getNumVides() > 0) {
                        switch (mapa) {
                            case 0:
                                updateMapa1();
                                break;
                            case 1:
                                Log.i("velocitat",String.valueOf(map.get(1).getVelX()));
                                Log.i("velocitat",String.valueOf(map.get(1).getObjects().get(2).getVelX()));
                                updateMapa2();
                                break;
                            case 2:
                                updateMapa3();
                                break;
                        }

                        //SI EL PERSONATGE PERD TOTES LES VIDES REINICIEM LES VARIABLES
                    } else {

                        restartValuesMap();


                    }
                }
            } else {
                Log.i("alfa",String.valueOf(alfa));
                Log.i("numMapes",String.valueOf(numMapes.size()));
                if (alfaText == 100 || (numMapes.size()==0 && alfa==100)) {
                    restartValuesMap();

                }

            }
        }
    }




    /**********************************************************************************************************
     * DRAW DEL MAPA
     *******************************************************************************************************/

    public void drawMapa(Canvas c){
        //MAPA

        c.drawBitmap(fons.get(0), map.get(mapa).getX(), map.get(mapa).getY(), null);

        if (mapa == 1 && accMetro<0 && metroEnPantalla){
            pintarObstaclePersonatge(c);
        }



        //OBJECTES
        for(int i = 2;i<map.get(mapa).getObjects().size()+2;i++) {
            if(mapa==1) {
                if (i != 4) {
                    c.drawBitmap(fons.get(i), map.get(mapa).getObjects().get(i - 2).getX(), map.get(mapa).getObjects().get(i - 2).getY(), null);
                } else if (i == 4 && metroEnPantalla) {
                    c.drawBitmap(fons.get(i), map.get(mapa).getObjects().get(i - 2).getX(), map.get(mapa).getObjects().get(i - 2).getY(), null);

                }
            }else{
                c.drawBitmap(fons.get(i), map.get(mapa).getObjects().get(i - 2).getX(), map.get(mapa).getObjects().get(i - 2).getY(), null);

            }
        }
        //vides
        c.drawBitmap(fons.get(map.get(mapa).getObjects().size() + 4), 0, 0, null);

        if (!metroEnPantalla) {
            pintarObstaclePersonatge(c);
        }


        //MIREM SI EL CLIC ESTA DINS DE LES POSSIBLES SOLUCIONS
        comprovarTouchMapa();




        comptaTempsTic(2, c);



    }

    private void controlarResposta(){
        if (resposta != -1){
            clickTime = System.currentTimeMillis();
            passaTemps=true;

            if (resposta == map.get(mapa).getObstacles().getRespostaCorrecte()){
                correcte = true;
                map.get(mapa).setFons(map.get(mapa).getFons2());
                index = map.get(mapa).getObjects().size()+3;
                if (!loadImage) {
                    recycle(fons.get(0));
                    recycle(map.get(mapa).getFons());
                    map.get(mapa).loadFons2();
                    fons.set(0,CanvasUtils.escalaImatge(map.get(mapa).getFons2(), map.get(mapa).getAlcada(), map.get(mapa).getAmplada()));
                    loadImage = true;
                }
            }else{
                prs.setNumVides(prs.getNumVides() - 1);
                index = map.get(mapa).getObjects().size()+2;
                if(prs.getNumVides()<2){
                    carregaUnaVida();
                }

            }
        }
    }

    //FUNCIO PER COMPROVAR EL TOUCH
    private void comprovarTouchMapa(){
        resposta = -1;

        if (ViewMapaHandler.getX() > map.get(mapa).getObstacles().getX11() && ViewMapaHandler.getX() < map.get(mapa).getObstacles().getX12() && ViewMapaHandler.getY() > map.get(mapa).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(mapa).getObstacles().getY2()){
            resposta = 1;
            controlarResposta();
            xTic = (int) (amplaPantalla*0.065);

        }
        if (ViewMapaHandler.getX() > map.get(mapa).getObstacles().getX21() && ViewMapaHandler.getX() < map.get(mapa).getObstacles().getX22() && ViewMapaHandler.getY() > map.get(mapa).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(mapa).getObstacles().getY2())  {
            resposta = 2;
            controlarResposta();
            xTic = (int) (amplaPantalla*0.384);

        }
        if (ViewMapaHandler.getX() > map.get(mapa).getObstacles().getX31() && ViewMapaHandler.getX() < map.get(mapa).getObstacles().getX32() && ViewMapaHandler.getY() > map.get(mapa).getObstacles().getY1() && ViewMapaHandler.getY() < map.get(mapa).getObstacles().getY2()) {
            resposta=3;
            controlarResposta();
            xTic = (int) (amplaPantalla* 0.69);
        }
        ViewMapaHandler.setX(0);
        ViewMapaHandler.setY(0);

    }
    /*********************************************************************************************************************
     * ------------------------ FUNCIONS DEL MAPA 2 ----------------------------------------------------------------
     *********************************************************************************************************************/

    /*********************************************************************************************************************
     * --------------------------   UPDATE DEL MAPA 2  -------------------------------------------------------------------
     *********************************************************************************************************************/

    private void arribaMetro(){
        if (map.get(1).getObjects().get(2).getVelX() > 0) {
            //moviment = true;

            map.get(1).getObjects().get(2).setVelX(map.get(1).getObjects().get(2).getVelX() - accMetro);
            map.get(1).getObjects().get(2).setX(map.get(1).getObjects().get(2).getX() + (map.get(1).getObjects().get(2).getVelX()));
        }else{
            metroAturat = true;
        }
    }

    private void metroAturada(int s){
      //  try {
           // sleep(s*1000);
            metroAturat = false;
            accMetro = map.get(1).getVelX()*-0.02f;
            map.get(1).getObjects().get(2).setVelX(1);

        //} catch (InterruptedException e) {
         //   e.printStackTrace();
        //}
    }

    private void personatgeSurtMetro(){
        //prs.setGifX((prs.getGifX()+prs.getVelX()*0.5f));
        float yPersonatge = 0f;
        if (alcadaPantalla>1000){
            yPersonatge = 0.5f;
        }else{
            yPersonatge = 0.75f;
        }
        if (prs.getGifY()>alcadaPantalla*yPersonatge){
            Log.i("velocitat",String.valueOf(prs.getVelY()));
            prs.setGifY(prs.getGifY() - prs.getVelY()*2);
            prs.setY(prs.getGifY()*fr);
        }
    }

    private void updateMapa2() {

        if(!correcte) {
            if (map.get(1).getObjects().get(2).getX() > amplaPantalla) {
                recycle(fons.get(4));
                metroEnPantalla = false;
            }

            if (metroEnPantalla) {
                moviment = true;
                if (!metroAturat) {
                    arribaMetro();
                    if (accMetro < 0) {
                        personatgeSurtMetro();
                    }
                } else {
                    metroAturada(2);
                }

            } else if (map.get(1).getX() > map.get(1).getAmplada() * -0.5) {
                endevant();

            } else {
                moviment = false;

            }
        }else{
            if(map.get(1).getX()>map.get(1).getAmplada()*-0.65f){

                endevant();


            }
            else{
                pantallaNegra=true;


            }
        }
    }








    /*********************************************************************************************************************
     * ------------------------ FUNCIONS DEL MAPA 3 ----------------------------------------------------------------
     *********************************************************************************************************************/

    /*********************************************************************************************************************
     * --------------------------   UPDATE DEL MAPA 3  -------------------------------------------------------------------
     *********************************************************************************************************************/


    private void updateMapa3(){
        moviment = false;

        //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
        if (map.get(2).getX()> map.get(2).getAmplada()*-0.5){
            endevant();
            map.get(2).getObjects().get(0).setX(map.get(2).getObjects().get(0).getX() - map.get(2).getObjects().get(0).getVelX());


        }

        if (correcte) {

            if (map.get(2).getX() > map.get(2).getAmplada() * -0.55) {
                endevant();

            } else {
                pantallaNegra = true;
                }


        }

    }

    /***********************************************************************************************************-
     * --------- FI FUNCIONS MAPA 3 ---------     ------------- INICI CONTROL THREAD I BITMAPS ----------------
     ************************************************************************************************************/


    public void setRunning(boolean b) {
        mRun = b;
    }


    public void clearCanvasObjects(int fijoc) {
        recycleImatgesFons();
        fons=null;
        if(fijoc==1){
            recycle(prs.getImg());
            recycleVides();
            recycle(finalFons);

        }
    }





    private void recycleVides(){
        //netejem imatges vida
        for(int i = 0;i<prs.getVides().size();i++){
            recycle(prs.getVides().get(i));
        }
    }

    private void recycleImatgesFons(){
        //netejem imatges fons
        if(fons!=null) {
            for (int i = 0; i < fons.size(); i++) {
                recycle(fons.get(i));
            }
        }
    }




}
