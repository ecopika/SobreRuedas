package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;

import java.io.IOException;
import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.GifMovieView;

/**
 * Created by ded on 13/04/2016.
 */
public class ObjecteJoc {

    //atributs del personatge al mapa
    private float x;
    private float y;
    private float amplada;
    private float alcada;
    private float velX;
    private float velY;
    private boolean movX;
    private boolean movY;
    private int profunditat;
    private Bitmap img;
    private String nomImatge;
    private Context cnt;
    private float factorX;
    private float factorY;
    private float factorAlcada;
    private float factorAmplada;






    public void setImg(Bitmap img) {
        this.img = img;
    }

    public ObjecteJoc( String nomImatge,int profunditat, Context cnt) {
        this.alcada = CanvasUtils.getHeightScreen()/3;
        this.amplada = this.alcada/2;
        this.y= 0;
        this.x = 0;
        this.velX = 0;
        this.velY = 0;
        this.movX = false;
        this.movY = false;
        this.profunditat = profunditat;
        this.nomImatge = nomImatge;
        this.cnt = cnt;
        loadImatge();


    }

    public void loadImatge(){
        this.img = CanvasUtils.loadBitmapFromString(cnt, nomImatge);

    }


    public float getFactorAlcada() {
        return factorAlcada;
    }

    public void setFactorAlcada(float factorAlcada) {
        this.factorAlcada = factorAlcada;
    }

    public float getFactorAmplada() {
        return factorAmplada;
    }

    public void setFactorAmplada(float factorAmplada) {
        this.factorAmplada = factorAmplada;
    }

    public float getFactorY() {
        return factorY;
    }

    public void setFactorY(float factorY) {
        this.factorY = factorY;
    }

    public float getFactorX() {
        return factorX;
    }

    public void setFactorX(float factorX) {
        this.factorX = factorX;
    }

    public int getProfunditat() {
        return profunditat;
    }

    public void setProfunditat(int profunditat) {
        this.profunditat = profunditat;
    }

    public void mouX(){
        this.x = this.x+this.velX;
    }

    public void mouY(){
        this.y = this.y+this.velY;
    }

    public boolean isMovX() {
        return movX;
    }

    public void setMovX(boolean movX) {
        this.movX = movX;
    }

    public boolean isMovY() {
        return movY;
    }

    public void setMovY(boolean movY) {
        this.movY = movY;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getAmplada() {
        return amplada;
    }

    public void setAmplada(float amplada) {
        this.amplada = amplada;
    }

    public float getAlcada() {
        return alcada;
    }

    public void setAlcada(float alcada) {
        this.alcada = alcada;
    }



    public String getNomImatge() {
        return nomImatge;
    }

    public void setNomImatge(String nomImatge) {
        this.nomImatge = nomImatge;
    }

    public Bitmap getImg() {
        return img;
    }
}
