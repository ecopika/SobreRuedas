package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesVista;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
        text = new TextNuvol(mContext);
        x = -1;
        y = -1;
        amplada=10;
        alcada = 10;
        if(color.equals("blanca")){
            imatge = (BitmapDrawable)mContext.getResources().getDrawable(R.drawable.blanca);
            text.setColor(Color.parseColor("#996699"));
            setTextStrokeWidth(55);
            setTextSize(60f);

        }
        else{
            imatge = (BitmapDrawable)mContext.getResources().getDrawable(R.drawable.morada);
            text.setColor(Color.WHITE);
            setTextStrokeWidth(50);
            setTextSize(40f);
        }

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

    public TextNuvol getTextNuvol(){
        return this.text;
    }

    public void regeneratePaint(){
        this.text = new TextNuvol(mContext);
    }

    public void setPaint(Paint pnt){
        this.text.setPaint(pnt);
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
