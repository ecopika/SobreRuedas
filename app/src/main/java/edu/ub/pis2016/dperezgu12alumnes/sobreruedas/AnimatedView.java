package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.jar.Attributes;
import android.os.Handler;

/**
 * Created by dperezgu12.alumnes on 14/03/16.
 */
public class AnimatedView extends ImageView {

    private Context meuContext;
    int x = -1;
    int y = -1;
    private int velocitatX = 10;
    private int velocitatY = 5;
    private Handler ctrl;
    private final int FRAM_RATE = 30;

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context,attrs);
        meuContext = context;
        ctrl = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c){
        BitmapDrawable nuvolB = (BitmapDrawable)meuContext.getResources().getDrawable(R.drawable.blanca);
        
    }
}
