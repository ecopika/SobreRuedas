package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.objectesVista;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

/**
 * Created by ecopika on 15/03/16.
 */
public class NuvolView {

    private BitmapDrawable imatge;
    private int amplada;
    private int alcada;
    private int x;
    private int y;
    private Context mContext;
    private TextNuvol text;
    private boolean showText;

    public NuvolView(String color, Context con, boolean showText){
        this.showText=showText;
        mContext=con;
        if(color.equals("blanca")){
            imatge = (BitmapDrawable)mContext.getResources().getDrawable(R.drawable.blanca);

        }
        else{
            imatge = (BitmapDrawable)mContext.getResources().getDrawable(R.drawable.morada);

        }
        x = -1;
        y = -1;
        text = new TextNuvol();
        amplada=10;
        alcada = 10;
    }

    public void showText(boolean sh){
        this.showText = sh;
    }

    public boolean isShowText(){
        return this.showText;
    }

    public void setTextColor(int clr){
        this.text.setColor(clr);
    }

    public int getTextColor(){
       return this.text.getColor();
    }

    public void setTextSize(float sz){
        this.text.setSize(sz);
    }

    public float getTextSize(){
        return this.text.getSize();
    }

    public void setTextStrokeWidth(int wd){
        this.text.setLineWidth(wd);
    }

    public int getTextStrokeWidth(){
        return this.text.getLineWidth();
    }


    public BitmapDrawable getImatge() {
        return imatge;
    }

    public void setImatge(BitmapDrawable imatge) {
        this.imatge = imatge;
    }

    public int getAmplada() {
        return amplada;
    }

    public void setAmplada(int amplada) {
        this.amplada = amplada;
    }

    public int getAlcada() {
        return alcada;
    }

    public void setAlcada(int alcada) {
        this.alcada = alcada;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getText() {
        if(!showText) return "";
        else return text.getTextValue();
    }

    public void setText(String text) {
        this.text.setTextValue(text);
    }
}
