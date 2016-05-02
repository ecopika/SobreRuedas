package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Calendar;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class Obstacles {

    private int pos;
    private int id;
    private Bitmap mapaSolucioImg;
    private ArrayList<Bitmap> respostes;


    public Obstacles(int id,int pos, String nomBitmap, ArrayList<String> resp, Context cnt){
        this.pos = pos;
        this.mapaSolucioImg = CanvasUtils.loadBitmapFromString(cnt,nomBitmap);
        this.respostes = new ArrayList<Bitmap>();
        for(int i = 0;i<resp.size();i++){
            respostes.add(CanvasUtils.loadBitmapFromString(cnt,resp.get(i)));
        }
    }

    public Bitmap getSolutionImg(){
        return this.mapaSolucioImg;
    }

    public Bitmap getRespostes(){
        if(respostes.size()>0)return respostes.get(0);
        else return null;
    }


}
