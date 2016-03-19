package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.objectesVista;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by ecopika on 15/03/16.
 */
public class TextNuvol {

    private Paint pnt;
    private int clr;
    private float size;
    private int lineWidth;
    private int x;
    private int y;
    private String textValue;

    public TextNuvol(){
        pnt = new Paint();
        textValue="";
    }



    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Paint getPaint() {
        return pnt;
    }

    public void setPaint(Paint pnt) {
        this.pnt = pnt;
    }

    public int getColor() {
        return clr;
    }

    public void setColor(int clr) {
        this.clr = clr;
        this.pnt.setColor(clr);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
        this.pnt.setTextSize(size);
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        this.pnt.setStrokeWidth(lineWidth);
    }
}
