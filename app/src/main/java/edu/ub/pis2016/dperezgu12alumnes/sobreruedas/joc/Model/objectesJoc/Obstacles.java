package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class Obstacles {

    private int pos;
    private int id;
    private Bitmap mapaSolucioImg;
    private ArrayList<Bitmap> respostes;
    private int x11;
    private int x12;
    private int x21;
    private int x22;
    private int x31;
    private int x32;
    private int y1;
    private int y2;
    private int respostaCorrecte;


    public Obstacles(int id,int pos, String nomBitmap, ArrayList<String> resp, Context cnt){
        this.pos = pos;
        this.mapaSolucioImg = CanvasUtils.loadBitmapFromString(cnt,nomBitmap);
        this.respostes = new ArrayList<Bitmap>();
        for(int i = 0;i<resp.size();i++){
            respostes.add(CanvasUtils.loadBitmapFromString(cnt,resp.get(i)));
        }
        x12 =0;
        x11 =0;
        x21 =0;
        x22 =0;
        x31 =0;
        x32 =0;
        y1 =0;
        y2 =0;
    }

    public Bitmap getSolutionImg(){
        return this.mapaSolucioImg;
    }

    public Bitmap getSolution(){
        if(respostes.size()>0)return respostes.get(0);
        else return null;
    }

    public Bitmap getRespostes(){
        return mapaSolucioImg;
    }


    public int getX32() {
        return x32;
    }

    public void setX32(int x32) {
        this.x32 = x32;
    }

    public int getX11() {
        return x11;
    }

    public void setX11(int x11) {
        this.x11 = x11;
    }

    public int getX12() {
        return x12;
    }

    public void setX12(int x12) {
        this.x12 = x12;
    }

    public int getX21() {
        return x21;
    }

    public void setX21(int x21) {
        this.x21 = x21;
    }

    public int getX22() {
        return x22;
    }

    public void setX22(int x22) {
        this.x22 = x22;
    }

    public int getX31() {
        return x31;
    }

    public void setX31(int x31) {
        this.x31 = x31;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setRespostaCorrecte(int r){
        this.respostaCorrecte = r;
    }

    public int getRespostaCorrecte(){
        return this.respostaCorrecte;
    }
}
