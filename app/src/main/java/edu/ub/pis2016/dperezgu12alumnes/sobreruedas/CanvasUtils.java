package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

/**
 * Created by dperezgu12.alumnes on 14/03/16.
 */


//****************************************************
//      CLASSE AMB DIVERSES FUNCIONS ÚTILS PER A CANVAS
//****************************************************

public class CanvasUtils {


    private DisplayMetrics tamanyPantalla;

    //constructor que necessita la metrica de la pantalla
    public CanvasUtils(DisplayMetrics met){
        tamanyPantalla = met;
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


}
