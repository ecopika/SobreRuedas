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
    private int x11Dolenta;
    private int x12Dolenta;
    private int x21Dolenta;
    private int x22Dolenta;
    private int x11Bona;
    private int x12Bona;
    private int y1;
    private int y2;


    public Obstacles(int id,int pos, String nomBitmap, ArrayList<String> resp, Context cnt){
        this.pos = pos;
        this.mapaSolucioImg = CanvasUtils.loadBitmapFromString(cnt,nomBitmap);
        this.respostes = new ArrayList<Bitmap>();
        for(int i = 0;i<resp.size();i++){
            respostes.add(CanvasUtils.loadBitmapFromString(cnt,resp.get(i)));
        }
        x12Bona =0;
        x11Bona =0;
        x21Dolenta =0;
        x22Dolenta =0;
        x11Dolenta =0;
        x12Dolenta =0;
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


    public int getX11Dolenta() {
        return x11Dolenta;
    }

    public void setX11Dolenta(int x11Dolenta) {
        this.x11Dolenta = x11Dolenta;
    }

    public int getX12Dolenta() {
        return x12Dolenta;
    }

    public void setX12Dolenta(int x12Dolenta) {
        this.x12Dolenta = x12Dolenta;
    }

    public int getX21Dolenta() {
        return x21Dolenta;
    }

    public void setX21Dolenta(int x21Dolenta) {
        this.x21Dolenta = x21Dolenta;
    }

    public int getX22Dolenta() {
        return x22Dolenta;
    }

    public void setX22Dolenta(int x22Dolenta) {
        this.x22Dolenta = x22Dolenta;
    }

    public int getX11Bona() {
        return x11Bona;
    }

    public void setX11Bona(int x11Bona) {
        this.x11Bona = x11Bona;
    }

    public int getX12Bona() {
        return x12Bona;
    }

    public void setX12Bona(int x12Bona) {
        this.x12Bona = x12Bona;
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
}
