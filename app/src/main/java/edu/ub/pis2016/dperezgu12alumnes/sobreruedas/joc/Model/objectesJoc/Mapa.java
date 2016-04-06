package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class Mapa {

    private int alcada;
    private int amplada;
    private int x;
    private int y;
    private float iniciX;
    private float iniciY;
    private Bitmap fons;

    private ArrayList<Obstacles> obstacles;

    public Mapa(int amplada, int alcada){
        x = 0;
        y = 0;
        this.amplada = amplada;
        this.alcada = alcada;
        this.iniciX = 0;
        this.iniciY = 0;
    }

    public int getAlcada() {
        return alcada;
    }

    public void setAlcada(int alcada) {
        this.alcada = alcada;
    }

    public int getAmplada() {
        return amplada;
    }

    public void setAmplada(int amplada) {
        this.amplada = amplada;
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

    public float getIniciX() {
        return iniciX;
    }

    public void setIniciX(float iniciX) {
        this.iniciX = iniciX;
    }

    public float getIniciY() {
        return iniciY;
    }

    public void setIniciY(float iniciY) {
        this.iniciY = iniciY;
    }

    public Bitmap getFons() {
        return fons;
    }

    public void setFons(Bitmap fons) {
        this.fons = fons;
    }

    public ArrayList<Obstacles> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacles> obstacles) {
        this.obstacles = obstacles;
    }
}
