package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesVista;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;

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
    private Typeface font;
    private Context mContext;

    public TextNuvol(Context cnt){
        pnt = new Paint();
        textValue="";
        mContext = cnt;
        initDefaultFont();

    }



    public String getTextValue() {
        return textValue;
    }

    public void initDefaultFont(){this.font=Typeface.createFromAsset(mContext.getAssets(),"fonts/BrannbollFS_PERSONAL.ttf");setTextFont(this.font);}

    public void setTextFont(Typeface font){this.font=font; this.pnt.setTypeface(font);}

    public Typeface getTextFont(){return this.font;}

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
