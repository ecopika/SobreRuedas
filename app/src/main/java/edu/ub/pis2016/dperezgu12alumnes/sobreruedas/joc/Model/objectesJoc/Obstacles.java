package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class Obstacles {

    private int pos;
    private int id;
    private Bitmap mapaSolucioImg;
    private ArrayList<Bitmap> respostes;


    public Obstacles(int id,int pos, String nomBitmap, ArrayList<String> resp){
        this.pos = pos;
        this.mapaSolucioImg = loadImatge(nomBitmap);
        this.respostes = new ArrayList<Bitmap>();
        for(int i = 0;i<resp.size();i++){
            respostes.add(loadImatge(resp.get(i)));
        }
    }

    private Bitmap loadImatge(String nomImatge){


    }

}
