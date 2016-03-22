package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.PCadiraRodes;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesVista.NuvolView;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities.MenuActivity;

/**
 * Created by ecopika on 15/03/16.
 */
public class ViewHandlerMenu {


    private boolean show;
    private boolean show2;
    private NuvolView nBlanc;
    private NuvolView nMorat1;
    private NuvolView nMorat2;
    private  int voltes;
    private int sortida;
    private boolean puja;
    private Context meuContext;


//constructor del controlador de la vista
    public ViewHandlerMenu(Context cnt){
        meuContext = cnt;
        inits();
        sortida=0;

    }

//funció per incialitzar les variables necessàries
    public void inits(){
        show = false;
        show2 = false;
        puja = false;
        nBlanc = new NuvolView("blanca",meuContext,false);
        nMorat1 = new NuvolView("",meuContext,false);
        nMorat2 = new NuvolView("",meuContext,false);
<<<<<<< HEAD:app/src/main/java/edu/ub/pis2016/dperezgu12alumnes/sobreruedas/joc/Controlador/ViewHandlerMenu.java
=======
        ctrl = new Handler();
>>>>>>> cde79f6bad4a6abde025f1534298ca32f55667c0:app/src/main/java/edu/ub/pis2016/dperezgu12alumnes/sobreruedas/vista/entornsVista/AnimatedView.java
        //nBlanc = new NuvolView("blanca",meuContext);
        //nMorat1 = new NuvolView("",meuContext);


        nMorat1.setAmplada(300);
        nMorat2.setAmplada(300);
        nMorat1.setAlcada(100);
        nMorat2.setAlcada(100);


        voltes=0;
    }
/***********************************************************************************************************************************
+
+
+
+           funcionalitats de la part del menú dels crèidts
+
+
+*************************************************************************************************************************************/
//funcions de moviment per a l'eix y dels objectes per tal de que surtin de la pantalla uns per dalt i altres per a baix
    public void animacioSortida() {
        if(nBlanc.getY() +  (nBlanc.getAlcada() / 2) > 0) nBlanc.setY(nBlanc.getY() - 10);
        if(nMorat1.getY()< CanvasUtils.getHeightScreen())nMorat1.setY(nMorat1.getY()+10);
        if(nMorat2.getY()<CanvasUtils.getHeightScreen())nMorat2.setY(nMorat2.getY()+10);

        if(nBlanc.getY() + (nBlanc.getAlcada() / 2)  <= 0 && nMorat1.getY()>=CanvasUtils.getHeightScreen() && nMorat2.getY()>=CanvasUtils.getHeightScreen() ){
            inits();
            sortida++;
        }
    }
//mostra tots els objectes de l'animació
    public void mostraTotsElements(Canvas c){
        c.drawBitmap(CanvasUtils.escalaImatge(nMorat1.getImatge().getBitmap(), nMorat1.getAlcada(), nMorat1.getAmplada()), nMorat1.getX() - (nMorat1.getAmplada() / 2), nMorat1.getY(), null);
        c.drawBitmap(CanvasUtils.escalaImatge(nMorat2.getImatge().getBitmap(),nMorat2.getAlcada(),nMorat2.getAmplada()),nMorat2.getX(),nMorat2.getY() ,null);
        c.drawBitmap(CanvasUtils.escalaImatge(nBlanc.getImatge().getBitmap(), nBlanc.getAlcada(), nBlanc.getAmplada()), nBlanc.getX() - (nBlanc.getAmplada() / 2), nBlanc.getY() - (nBlanc.getAlcada() / 2), null);
        c.drawText(nMorat2.getText(), (nMorat2.getX()) + nMorat2.getAmplada() / 4, nMorat2.getY() + 100, nMorat2.getTextNuvol().getPaint());
        c.drawText(nBlanc.getText(), (nBlanc.getX() - (nBlanc.getAmplada() / 2)) + 80, nBlanc.getY() + 75, nBlanc.getTextNuvol().getPaint());
        c.drawText(nMorat1.getText(), (nMorat1.getX() - (nMorat1.getAmplada() / 2)) + nMorat1.getAmplada() / 4, nMorat1.getY() + 100, nMorat1.getTextNuvol().getPaint());

    }

    public void animacioEntrada(Canvas c){
        //per defecte inicialitzats a -1 tant la X com la Y de qualsevol nuvol
        if (nBlanc.getX() < 0 && nBlanc.getY() < 0){
            nBlanc.setX(CanvasUtils.getWidthScreen() / 2 - (nBlanc.getAmplada() / 2));
            nBlanc.setY(CanvasUtils.getHeightScreen()/2-(nBlanc.getAlcada()/2));

        }else{

            if(nBlanc.getAmplada()<350){
                nBlanc.setAmplada(nBlanc.getAmplada()+20);
            }
            if(nBlanc.getAlcada()<310){
                nBlanc.setAlcada(nBlanc.getAlcada()+20);
            }

            if(nBlanc.getAmplada()>=350 && nBlanc.getAlcada()>=300 && nBlanc.getY()-(nBlanc.getAlcada()/2)>200){
                nBlanc.showText(true);
                puja = true;
            }
            if(puja){
                nBlanc.setY(nBlanc.getY()-10);
            }
            if(nBlanc.getY()-(nBlanc.getAlcada()/2)<200){
                puja = false;
                nMorat1.showText(true);
                nMorat2.showText(true);
            }

        }
        if(!puja && nMorat1.isShowText() && nMorat2.isShowText()){
            //Nuvol morat 1
            //***************************************************************************************
            if(nMorat1.getAmplada()<350){
                nMorat1.setAmplada(nMorat1.getAmplada()+20);
            }
            if(nMorat1.getAlcada()<150){
                nMorat1.setAlcada(nMorat1.getAlcada()+20);
            }

            if(nMorat1.getAmplada()>=350 && nMorat1.getAlcada()>=300 && nMorat1.getY()-(nMorat1.getAlcada()/2)>200){
                nMorat1.showText(true);
            }
            nMorat1.setX((CanvasUtils.getWidthScreen()/2-(nMorat1.getAmplada()/2)));
            nMorat1.setY(((CanvasUtils.getHeightScreen() / 2 - (nMorat1.getAlcada() / 2))));
            c.drawBitmap(CanvasUtils.escalaImatge(nMorat1.getImatge().getBitmap(),nMorat1.getAlcada(),nMorat1.getAmplada()),nMorat1.getX()-(nMorat1.getAmplada()/2),nMorat1.getY() ,null);


            //Nuvol Morat 2
            //***************************************************************************************
            if(nMorat2.getAmplada()<350){
                nMorat2.setAmplada(nMorat2.getAmplada()+20);
            }
            if(nMorat2.getAlcada()<150){
                nMorat2.setAlcada(nMorat2.getAlcada()+20);
            }

            if(nMorat2.getAmplada()>=350 && nMorat2.getAlcada()>=300 && nMorat2.getY()-(nMorat2.getAlcada()/2)>200){
                nMorat2.showText(true);
            }
            nMorat2.setX((CanvasUtils.getWidthScreen() / 2 + (nMorat2.getAmplada() / 2)) - ((nMorat2.getAmplada() / 2)));
            nMorat2.setY(((CanvasUtils.getHeightScreen() / 2 - (nMorat2.getAlcada() / 2))));
            c.drawBitmap(CanvasUtils.escalaImatge(nMorat2.getImatge().getBitmap(),nMorat2.getAlcada(),nMorat2.getAmplada()),nMorat2.getX(),nMorat2.getY() ,null);



        }

        c.drawBitmap(CanvasUtils.escalaImatge(nBlanc.getImatge().getBitmap(), nBlanc.getAlcada(), nBlanc.getAmplada()), nBlanc.getX() - (nBlanc.getAmplada() / 2), nBlanc.getY() - (nBlanc.getAlcada() / 2), null);

        //Configuracio del text del nuvol blanc
        if (nBlanc.isShowText()){
            if(sortida==0 || sortida==1) nBlanc.setText(meuContext.getResources().getString(R.string.des));
            else nBlanc.setText(meuContext.getResources().getString(R.string.dis));
            c.drawText(nBlanc.getText(), (nBlanc.getX() - (nBlanc.getAmplada() / 2)) + 80, nBlanc.getY() + 75, nBlanc.getTextNuvol().getPaint());
        }
//Configuracio del text del nuvol morat 1
        if (!puja && nMorat1.isShowText()){
            if(sortida==0) nMorat1.setText(meuContext.getResources().getString(R.string.d3));
            else if(sortida==1)nMorat1.setText(meuContext.getResources().getString(R.string.d4));
            else nMorat1.setText(meuContext.getResources().getString(R.string.e2));
            c.drawText(nMorat1.getText(), (nMorat1.getX() - (nMorat1.getAmplada() / 2)) + nMorat1.getAmplada() / 4, nMorat1.getY() + 100, nMorat1.getTextNuvol().getPaint());
        }
//Configuracio del text del nuvol morat 2
        if (!puja && nMorat2.isShowText()){
            if(sortida==0) nMorat2.setText(meuContext.getResources().getString(R.string.d1));
            else if(sortida==1)nMorat2.setText(meuContext.getResources().getString(R.string.d2));
            else nMorat2.setText(meuContext.getResources().getString(R.string.e1));
            c.drawText(nMorat2.getText(), (nMorat2.getX()) + nMorat2.getAmplada() / 4, nMorat2.getY() + 100, nMorat2.getTextNuvol().getPaint());
            voltes++;
            //Log.i("Voltes",String.valueOf(voltes));

        }

    }

    public int getVoltes(){
        return this.voltes;
    }

    public int getSortida(){
        return this.sortida;
    }

    public void finalitzaPantallaCredits(){
        meuContext.startActivity(new Intent(meuContext,MenuActivity.class));

    }

    /********************************************************************************************************************************************************************
     *
     * Fi Crèdits
     *
     ********************************************************************************************************************************************************************/


    /************************************************************************
     *    Perfil personatge
     ***********************************************************************/

    public Personatge generatePersonatge(){
        PCadiraRodes nuria = new PCadiraRodes("Nuria","nuriafotograma.png", 10 ,"azul", "Pantera","Lasaña","Basquet","Mujer maravilla","pintar","leer comics de mortadelo y filemon","pyasos","no hay nada imposible para mi","llegar a ser una gran politica y cambiar las cosas");
        return nuria;
    }








}
