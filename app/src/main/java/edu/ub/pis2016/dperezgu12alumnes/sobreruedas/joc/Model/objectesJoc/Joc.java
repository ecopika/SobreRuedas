package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by ecopika on 22/05/16.
 */
public class Joc {

    //variables configuració general
    private int amplaPantalla;
    private int alcadaPantalla;
    private Context cnt;

    //variables final pantalla
    //transparencia de la pantalla negra del final de la pantalla
    private int alfa;
    //transparencia del text
    private int alfaText;
    private int alfaMissatge;
    //paint per al text del final
    private Paint pText;
    //frase a mostrar com a transició entre pantalles
    private String frase;
    //imatge final
    private Bitmap finalFons;
    //puntuació
    private int puntuacio;
    //pantalla negre
    private Paint paint;

    //configuració dels textos
    private ArrayList<String> textosTrans;
    private ArrayList<String> textosResp;

    //configuracio bitmaps
    BitmapFactory.Options op = new BitmapFactory.Options();


    //variables gestio mapes
    private ArrayList<Integer> numMapes;


    //objectes joc
    //array que conté les diferents parts del mapa
    private ArrayList<Mapa> map;
    //personatge que utilitzem
    private Personatge prs;
    private ArrayList<Bitmap> fons;
    //bitmaps que apareixen quan fem un touch
    private Bitmap creu;
    private Bitmap tic;
    //paint per al temps del joc
    private Paint pTemps;
    //paint de la puntuacio
    private Paint pPuntuacio;



    //VARIABLES DE CONTROL
    //CONTROLEM SI EL PERSONATGE ESTA EN MOVIMENT
    private boolean moviment;
    //CONTROLEM SI S'HA DE CARREGAR UNA IMATGE
    private boolean loadImage;
    //CONTROLA SI A PASSAT EL TEMPS ENTRE TOUCH I TOUCH
    private boolean passaTemps;
    //CONTROLA L'INDEX DE LA PART DEL MAPA A LA QUE ESTAS JUGANT
    private int mapa;
    //CONTROLEM SI EL METRO ESTÀ ATURAT
    private boolean metroAturat;
    //CONTROLA SI ES LA PRIMERA VEGADA QUE ENTRA AL THREAD
    private boolean primeraVegadaThread;
    //CONTROLA SI HAS GUANYAT
    private boolean finalJoc;
    //CONTROLA SI S'HA DE PINTAR LA PANTALLA DE NEGRE
    private boolean pantallaNegra = false;
    //CONTROLA SI EL METRO ESTA DINS LA PANTALLA
    private boolean metroEnPantalla;
    //CONTROLA
    private boolean primeraVegadaEntra=false;
    //CONTROLA SI S'HA ENCERTAT LA RESPOSTA
    private boolean correcte = false;
    //CONTROLA LA RESPOSTA SELECCIONADA
    private int resposta;
    //CAPTURA EL MOMENT DEL TOUCH
    private long clickTime;
    //CONTROLA SI S'HA DE PINTAR LA CREU O EL TIC
    private int index = 0;
    //CONTROLA SI HA PASSAT EL TEMPS PER PODER FER UN ALTRE TOUCH
    private boolean clickat;
    //controla si s'ha de pintar el personatge als finals de les pantalles
    private boolean prsVisio;
    //controla que el final de la pantalla es faci correctament
    private int finalPantalla;
    //variables per controlar el temps segons la dificultat
    private long tempsInici;
    private long tempsActual;
    private int maxTemps;
    private boolean mostraTemps;
    //CONTROLEM EL FADEIN FADEOUT DEL TEXT
    private boolean ocultaText;

    //control del mostreig de preguntes
    private boolean mostraPreguntes;



    //VARIABLES SUPORT
    //ARRAY DE SUPORT DE numMapes
    private ArrayList<Integer> auxiliarNumMapes;


    //VARIABLES DE CONFIGURACIÓ D'OBJECTES
    //acceleració del metro
    private float accMetro;
    //factor d'escalatge de l'eix Y del Gif del personatge
    private float escalatgeYGif;
    //factor d'escalatge de l'eix X del Gif del personatge
    private float escalatgeXGif;
    //coordenades per al tic i la creu(touch)
    private int yTic;
    private int xTic = 0;





    public Joc(){
        this.amplaPantalla = CanvasUtils.getWidthScreen();
        this.alcadaPantalla = CanvasUtils.getHeightScreen();
        //array que guarda tots els bitmaps dels objectes que pintarem
        fons = new ArrayList<Bitmap>();
        //array dels index de les parts del mapa a les que jugarem
        numMapes = new ArrayList<Integer>();
        //INICIALITZEM ELS BOOLEANS
        finalJoc = false;
        primeraVegadaThread=true;


        initFinalPantalla();

        //inicialitzem la puntuacio
        puntuacio=0;
    }


    /*****************************************************************************************************************
     * FUNCIONS PER INICIALITZAR EL JOC
     *****************************************************************************************************************/


    //--------INICIALITZEM EL MAPA -----------

    private void initMapes(){
        initVariablesControl();
        initObjMapa();
        if (mapa == 1){
            configMetro();
        }
        configPersonatge();
        initTouchMapa();
        escalaImatgesMapa(mapa);


    }

    //INICIALITZEM LES VARIABLES DE CONTROL
    private void initVariablesControl(){

        passaTemps = false;
        moviment = false;
        loadImage=false;
        ocultaText=false;




    }


    //INICIALITZEM ELS OBJECTES DEL MAPA
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

    //CONFIGUREM LES POSICIONS DELS OBJECTES DEL MAPA
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

    //inicialitzem el personatge
    public void initPersonatge(int indx){
        prs = ViewMapaHandler.getPersonatges().get(indx);
    }



    //inicialitzem la configuració de les variables per a la transicio entre pantalles
    public void initFinalPantalla(){
        alfa=0;
        alfaText = 0;
        alfaMissatge=0;
        frase=".";
        pText = new Paint();

        pText.setColor(Color.WHITE);



    }
    //generem l'array de les parts del mapa i fem l'array d'enters per configurar l'ordre d'aparició
    public void generateMapa(){
        map = ViewMapaHandler.generateMap();
        for (int i=0;i<map.size();i++){
            numMapes.add(i);
        }
        Collections.shuffle(numMapes);

    }


    //CONFIGUREM EL METRO
    private void configMetro(){
        accMetro = map.get(1).getVelX()*0.001666667f;
        metroAturat = false;
    }


    //INICIALITZACIO DE LES POSICIONS INICIALS DEL PERSONATGE
    private void configPersonatge(){



        //PERSONATGE
        prs.setAlcada(map.get(mapa).getAlcada() / 4);
        float amplada = ( prs.getAlcada() * CanvasUtils.calcularFactorEscalatge(prs.getImg()));
        prs.setAmplada( amplada);
        prs.setX(prs.getAmplada() / 2);
        prs.setY(map.get(mapa).getAlcada() * 0.6f);
        escalatgeXGif =  prs.getAmplada() / (float) prs.getImg().getWidth();
        prs.setGifX(prs.getX() / escalatgeXGif);
        escalatgeYGif =  prs.getAlcada() / (float) prs.getImg().getHeight();
        prs.setGifY(prs.getY() / escalatgeYGif);

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
            fons.add(CanvasUtils.escalaImatge(prs.getVides().get(1), alcadaPantalla*0.117f, amplaPantalla*0.28f));
        }
        else{
            fons.add(CanvasUtils.escalaImatge(prs.getVides().get(0), alcadaPantalla*0.117f, amplaPantalla*0.28f));

        }
    }

    //funcio que carrega el numero de vides en funcio de la dificultat
    private void carregaVides(){
        if (ViewHandlerMenu.difs.get("facil")<ViewHandlerMenu.dificultat) {
            prs.setNumVides(1);
        }else{
            prs.setNumVides(2);
        }
    }

    //funció per reiniciar els valors de tots els objectes del mapa
    public void restartValuesMap(){
        clearCanvasObjects(0);
        paint = new Paint();
        pPuntuacio = new Paint();
        pTemps = new Paint();
        mostraPreguntes=false;
        if (primeraVegadaThread) {
            auxiliarNumMapes = cloneArray(numMapes);
            primeraVegadaThread = false;
            carregaVides();
        } else {
            if (prs.getNumVides() == 0) {
                numMapes = cloneArray(auxiliarNumMapes);
                carregaVides();
            }
        }
        if(numMapes.size()==0){
            finalJoc=true;
            String difi="";
            for(Map.Entry<String,Integer> e : ViewHandlerMenu.difs.entrySet()) {
                if(e.getValue()==ViewHandlerMenu.dificultat){
                    difi = e.getKey();
                }
            }
            if(puntuacio<0){
                puntuacio=0;
            }
            ViewMapaHandler.guardarPuntuacio(difi,puntuacio,prs.getNom());
        }

        if (numMapes.size() > 0) {

            mapa = numMapes.remove(0);
            if(numMapes.size()>0) {
                frase = map.get(numMapes.get(0)).getTextTrans();
            }
            pText.setTextSize((amplaPantalla / (frase.length()*0.25f)));


        }





        if (ViewHandlerMenu.dificultat == ViewHandlerMenu.difs.get("dificil")){
            maxTemps = 10;
        }else if(ViewHandlerMenu.dificultat == ViewHandlerMenu.difs.get("moltDificil")){
            maxTemps = 5;
        }
        mostraTemps = false;

        fons = new ArrayList<Bitmap>();
        if(!finalJoc) {

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

        }else{
            finalFons = ((BitmapDrawable) cnt.getResources().getDrawable(R.drawable.pantallafinal)).getBitmap();
            fons.add(CanvasUtils.escalaImatge(finalFons,alcadaPantalla , amplaPantalla));
            recycle(finalFons);
        }
        pantallaNegra = false;
        prsVisio = true;
        mostraPreguntes=false;
        finalPantalla = 0;

    }

    //carreguem els tics
    private void loadTics(){
        //CARREGUEM LES IMATGES DEL TIC I LA CREU
        recycle(creu);
        recycle(tic);
        creu=null;
        tic=null;
        creu = CanvasUtils.loadBitmapFromString(cnt, "cruzroja");
        tic = CanvasUtils.loadBitmapFromString(cnt, "tic");
    }

    /*******************************************************************************************
     * FUNCIONS DE RETORN I CARREGA
     *******************************************************************************************/
//retornem el personatge emprat
    public Personatge getPersonatge(){
        return prs;
    }

    public void setPersonatge(Personatge prs){
        this.prs = prs;
    }

    public void setContext(Context cnt){
        this.cnt = cnt;
    }

    /*******************************************************************************************
     *  DRAW
     ******************************************************************************************/
    public void pintar(Canvas c) {
        if (!finalJoc) {
            if (!pantallaNegra) {
                drawMapa(c);


            } else {
                pText.setColor(Color.WHITE);
                pText.setTextSize((amplaPantalla / (frase.length() * 0.25f)));

                pintaNegra(c);

            }
        } else {
            mostraFinal(c);
        }
    }

    //funció que mostra la pantalla final del joc
    private void mostraFinal(Canvas c){
        c.drawBitmap(fons.get(fons.size() - 1), 0, 0, null);

    }

    //FUNCIÓ QUE PINTA LA PUNTUACIÓ
    private void pintaPuntuacio(Canvas c){
        pPuntuacio.setTextSize(amplaPantalla * 0.2f);
        pPuntuacio.setColor(Color.BLACK);
        if(puntuacio>0) {
            c.drawText(String.valueOf(puntuacio), amplaPantalla * 0.4f, alcadaPantalla * 0.95f, pPuntuacio);
        }
        else{
            c.drawText("0", amplaPantalla * 0.4f, alcadaPantalla * 0.95f, pPuntuacio);

        }
    }

    //FUNCIO PER PINTAR LA PANTALLA NEGRE QUAN S'HA ACAVAT EL NIVELL
    private void pintaNegra(Canvas c){
        if (finalPantalla++< 2) {
            drawMapa(c);
        }

        if (alfa < 100) alfa++;
        paint.setAlpha(alfa);
        paint.setColor(Color.BLACK);
        c.drawARGB(alfa, 0, 0, 0);
        if(numMapes.size()>0) {
            if (alfa == 100) {
                if (alfaText < 100) alfaText++;
                pText.setAlpha(alfaText);
                String frase1 = frase.substring(0,(frase.length()/2)+1);
                String frase2 = frase.substring((frase.length()/2)+1,frase.length()-1);
                pintaText(c, frase1, 5, (alcadaPantalla / 2) - (frase.length()*2),pText);
                pintaText(c,frase2, 5, (alcadaPantalla / 2),pText);
                if(alfaText==100) try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void pintaText(Canvas c, String text, float x,float y, Paint p){
        c.drawText(text, x, y, p);
    }


    public void drawMapa(Canvas c){

        //MAPA
        c.drawBitmap(fons.get(0), map.get(mapa).getX(), map.get(mapa).getY(), null);



        if (mapa == 1 && accMetro<0 && metroEnPantalla && prsVisio){
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



        if (!metroEnPantalla && prsVisio) {
            pintarObstaclePersonatge(c);
            comprovarTouchMapa();
        }


        //MIREM SI EL CLIC ESTA DINS DE LES POSSIBLES SOLUCIONS

        comptaTempsTic(2, c);
        pintaPuntuacio(c);

        //vides
        c.drawBitmap(fons.get(map.get(mapa).getObjects().size() + 4), amplaPantalla * 0.7f, alcadaPantalla * 0.85f, null);
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
                    mostraPreguntes=false;
                }
            }

        }
    }

    //PINTA TIC O CREU
    public void pintaTic(int index,int x,int y,Canvas c){
        c.drawBitmap(fons.get(index), x, y, null);
        mostraTemps = false;

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

    //CONTROLA LA RESPOSTA
    private void controlarResposta(){
        if (resposta != -1){
            clickTime = System.currentTimeMillis();
            passaTemps=true;

            if (resposta == map.get(mapa).getObstacles().getRespostaCorrecte()){
                correcte = true;
                puntuacio+=20;
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
                puntuacio-=30;
                prs.setNumVides(prs.getNumVides() - 1);
                index = map.get(mapa).getObjects().size()+2;
                if(prs.getNumVides()<2){
                    carregaUnaVida();
                }

            }
        }
    }

    //RECICLA LA IMATGE DE DOS VIDES I CARREGA LA DE UNA VIDA
    private void carregaUnaVida(){
        if (!loadImage) {
            recycle(fons.get(map.get(mapa).getObjects().size() + 4));
            fons.set(map.get(mapa).getObjects().size() + 4, CanvasUtils.escalaImatge(prs.getVides().get(0), alcadaPantalla*0.117f, amplaPantalla*0.28f));
            loadImage = true;
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
            c.scale(escalatgeXGif, escalatgeYGif);
            //dibuixem el GIF
            prs.getGifPrs().getMovie().draw(c, prs.getGifX(), prs.getGifY());

            c.scale(1/escalatgeXGif, 1/escalatgeYGif);


        } else if(mostraPreguntes) {//si el personatge no és mou
            if (!primeraVegadaEntra) {
                ViewMapaHandler.setClic(true);
                carregaRespostes(c);
                tempsInici = System.currentTimeMillis();
                pTemps.setColor(Color.BLACK);
                pTemps.setTextSize(amplaPantalla * 0.2f);
                mostraTemps=true;
            }

            primeraVegadaEntra = true;
            tempsActual = System.currentTimeMillis();

            if (mostraTemps) {
                if (ViewHandlerMenu.dificultat > ViewHandlerMenu.difs.get("moderat")) {
                    int t = maxTemps - (int) ((tempsActual - tempsInici) / 1000);
                    c.drawText(String.valueOf(t), amplaPantalla*0.1f, alcadaPantalla*0.95f, pTemps);
                    if (t < 4) {
                        pTemps.setColor(Color.RED);
                    }
                    if(t==0){
                        prs.setNumVides(0);
                        puntuacio-=30;
                    }
                }
            }


            c.drawBitmap(fons.get(1), prs.getX(), prs.getY() + 5, null);


            c.drawBitmap(fons.get(map.get(mapa).getObjects().size() + 5), 0, 0, null);
            pintarMissatgesPreguntes(c);
        }
    }

    public void pintarMissatgesPreguntes(Canvas c){
        String msg ="";
        Paint pMsg = new Paint();
        pMsg.setColor(Color.BLACK);
        pMsg.setStyle(Paint.Style.STROKE);
        pMsg.setStrokeJoin(Paint.Join.ROUND);
        pMsg.setStrokeMiter(10.0f);
        pMsg.setStrokeWidth(3);

        switch (mapa){
            case 0:
                pMsg.setTextSize((amplaPantalla / (frase.length() * 0.4f)));

                msg = cnt.getResources().getString(R.string.prRampa2);
                pintaMissatgeAmbFadeInFadeOut(c,msg,pMsg);
                msg = cnt.getResources().getString(R.string.prRampa);
                pintaMissatgeAmbFadeInFadeOut(c, msg,(alcadaPantalla/2)-msg.length()*2,pMsg);

                break;
            case 1:
                pMsg.setTextSize((amplaPantalla / (frase.length() * 0.3f)));

                msg = cnt.getResources().getString(R.string.prMetro2);
                pintaMissatgeAmbFadeInFadeOut(c,msg,(alcadaPantalla/2)+((msg.length()*4)+msg.length()*3f),pMsg);
                msg = cnt.getResources().getString(R.string.prMetro);
                pintaMissatgeAmbFadeInFadeOut(c,msg,(alcadaPantalla/2)+(msg.length()*4),pMsg);
                break;
            case 2:

                pMsg.setTextSize((amplaPantalla / (frase.length() * 0.35f)));

                msg = cnt.getResources().getString(R.string.prPorta3);
                pintaMissatgeAmbFadeInFadeOut(c,msg,(alcadaPantalla/2)+msg.length()*2,pMsg);
                msg = cnt.getResources().getString(R.string.prPorta2);
                pintaMissatgeAmbFadeInFadeOut(c,msg,pMsg);
                msg = cnt.getResources().getString(R.string.prPorta);
                pintaMissatgeAmbFadeInFadeOut(c,msg,(alcadaPantalla/2)-msg.length()*2,pMsg);
                break;
        }

    }
//animacio fade in fadeout al mig de la pantalla
    private void pintaMissatgeAmbFadeInFadeOut(Canvas c,String txt, Paint p){
        int t=9999999;
        if(alfaMissatge<100 && !ocultaText)alfaMissatge++;

        if(correcte && alfaMissatge > 0)alfaMissatge--;
        p.setAlpha(alfaMissatge);
        pintaText(c,txt,5,(alcadaPantalla/2),p);
    }
//animacio fade in fade out amb la y parametritzada
    private void pintaMissatgeAmbFadeInFadeOut(Canvas c,String txt,float y, Paint p){
        int t=9999999;
        if(alfaMissatge<200 && !ocultaText)alfaMissatge++;

        if(correcte && alfaMissatge > 0)alfaMissatge--;
        p.setAlpha(alfaMissatge);
        pintaText(c,txt,5,y,p);
    }

    //CARREGA EL BITMAP DE LA RESPOSTA DE LA PART DEL MAPA CORRESPONENT
    private void carregaRespostes(Canvas c) {
        loadBitmap(map.get(mapa).getObstacles().getRespostes(), alcadaPantalla, amplaPantalla, 8);
    }

    //CARREGA UN BITMAP
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

    /*******************************************************************************************
     *  UPDATES
     *****************************************************************************************/

    public void updateMapa(){
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
                if (alfaText == 100 || (numMapes.size()==0 && alfa==100)) {
                    restartValuesMap();

                }

            }
        }
    }


    private void updateMapa1(){
        moviment = false;

        //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
        if (map.get(0).getX()> map.get(0).getAmplada()*-0.5){
            endevant();
        }

        if(!correcte && !moviment){
            mostraPreguntes=true;
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
                    prsVisio=false;

                }
            }
        }
    }


    private void updateMapa2() {


        if(!correcte && !moviment){
            mostraPreguntes=true;
        }

        if (!correcte) {
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
                    metroAturada();
                }

            } else if (map.get(1).getX() > map.get(1).getAmplada() * -0.5) {
                endevant();

            } else {
                moviment = false;

            }
        } else {
            //UN COP HAS TRIAT LA RESPOSTA CORRECTE EL MAPA AVANÇA FINS A:

            if (map.get(1).getX() > map.get(1).getAmplada() * -0.59f) {
                endevant();
            } else {
                moviment = true;
                prs.setGifX(prs.getGifX() + prs.getVelX());

                prs.setCoords(prs.getCoords() + prs.getVelX());

                if (!prsVisio) pantallaNegra = true;

                if (prs.getCoords() > map.get(1).getAmplada() * 0.85f) {
                    prsVisio = false;
                }

            }
        }
    }


    private void updateMapa3() {
        moviment = false;

        //if (x > amplaPantalla - map.getAmplada() + (prs.getAmplada() / 2)) {
        if (map.get(2).getX() > map.get(2).getAmplada() * -0.52) {
            endevant();
            map.get(2).getObjects().get(0).setX(map.get(2).getObjects().get(0).getX() - map.get(2).getObjects().get(0).getVelX());


        }


        if(!correcte && !moviment){
            mostraPreguntes=true;
        }

        if (correcte) {

            if (map.get(2).getX() > map.get(2).getAmplada() * -0.58) {
                endevant();

            } else {

                moviment = true;
                prs.setGifX(prs.getGifX() + prs.getVelX());

                prs.setCoords(prs.getCoords() + prs.getVelX());

                if (!prsVisio) pantallaNegra = true;

                if (prs.getCoords() > map.get(2).getAmplada() * 0.8f) {
                    prsVisio = false;


                }
            }
        }
    }

    private void endevant(){
        moviment = true;
        //moviment del mapa
        map.get(mapa).setX( (map.get(mapa).getX() - map.get(mapa).getVelX()));
        //moviment dels objectes
        for (int i = 0; i<map.get(mapa).getObjects().size(); i++){
            map.get(mapa).getObjects().get(i).setX(map.get(mapa).getObjects().get(i).getX()-map.get(mapa).getObjects().get(i).getVelX());
        }
        //posicio del personatge respecte al mapa
        prs.setCoords(prs.getCoords() + map.get(mapa).getVelX());
    }

    //FUNCIO PER GESTIONAR L'ARRIBADA DEL METRO
    private void arribaMetro(){
        if (map.get(1).getObjects().get(2).getVelX() > 0) {
            map.get(1).getObjects().get(2).setVelX(map.get(1).getObjects().get(2).getVelX() - accMetro);
            map.get(1).getObjects().get(2).setX(map.get(1).getObjects().get(2).getX() + (map.get(1).getObjects().get(2).getVelX()));
        }else{
            metroAturat = true;
        }
    }

    //FUNCIO PER GESTIONAR L'ARRANCADA DEL METRO
    private void metroAturada(){
        metroAturat = false;
        accMetro = map.get(1).getVelX()*-0.02f;
        map.get(1).getObjects().get(2).setVelX(1);
    }


    //FUNCIO PER MOURE L'EIX Y DEL PERSONATGE MENTRE ESTA SORTINT EL METRO
    private void personatgeSurtMetro(){
        float yPersonatge = 0.5f;


        if ((prs.getGifY()+(prs.getAlcada()*0.35f))>alcadaPantalla*yPersonatge){
            prs.setGifY(prs.getGifY() - prs.getVelY()*2);
            prs.setY(prs.getGifY()*escalatgeYGif);
        }
    }

    /*******************************************************************************************
     * FUNCIONS DE GESTIÓ DE MEMÒRIA
     *******************************************************************************************/
    //funcio per reciclar  bitmaps
    public void recycle(Bitmap bm){
        if(bm != null){
            bm.recycle();
            bm=null;
        }
    }

    //reciclem tots els bitmaps
    public void clearCanvasObjects(int fijoc) {
        recycleImatgesFons();
        fons=null;
        if(fijoc==1){
            recycle(prs.getImg());
            recycleVides();
            recycle(finalFons);

        }
    }

    //netejem els bitmaps de l'array fons
    private void recycleImatgesFons(){
        //netejem imatges fons
        if(fons!=null) {
            for (int i = 0; i < fons.size(); i++) {
                recycle(fons.get(i));
            }
        }
    }

    //netejem imatges vida
    private void recycleVides(){
        for(int i = 0;i<prs.getVides().size();i++){
            recycle(prs.getVides().get(i));
        }
    }

    //CARREGA LA SEGUENT IMATGE DE LA PART DEL MAPA CORRESPONENT
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

}
