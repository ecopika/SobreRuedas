package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;


import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;


/**
 * Created by dperezgu12.alumnes on 06/04/16.
 */
public class Mapa  {

    private float alcada;
    private float amplada;
    private int x;
    private int y;
    private Bitmap fons;
    private Bitmap fons2;
    private int id;
    private float velX;
    private float factVelX;
    private ArrayList<ObjecteJoc> obj;
    private String nomImg;
    private String nomImg2;
    private String txtTrans;



    private float factorEscalatge;

    private Obstacles obstacles;

    public Mapa(float amplada, float alcada){
        x = 0;
        y = 0;
        this.amplada = amplada;
        this.alcada = alcada;
        this.factorEscalatge=1.5f;
        this.factVelX=0.0025f;



    }

    public void carregaObjectesIFactors(){
        obj = ViewMapaHandler.generateObjecteJoc(id);
        generateFactors();
    }

    public void loadFons1(){
        this.setFons(CanvasUtils.loadBitmapFromString(ViewMapaHandler.getContext(), this.getNomImg()));

    }

    public void loadFons2(){
        this.setFons2(CanvasUtils.loadBitmapFromString(ViewMapaHandler.getContext(), this.getNomImg2()));

    }

    public float getFactVelX() {
        return factVelX;
    }

    public void setFactVelX(float factVelX) {
        this.factVelX = factVelX;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public float getAlcada() {
        return alcada;
    }

    public void generateFactors(){
        ArrayList<Float> facts = ViewMapaHandler.getFactors(id);
        int k = 0;
        for(int i = 0;i<obj.size();i++){
                obj.get(i).setFactorAlcada(facts.get(k));
                obj.get(i).setFactorAmplada(facts.get(k+1));
                obj.get(i).setFactorY(facts.get(k+2));
                obj.get(i).setFactorX(facts.get(k+3));

            k+=4;
        }


    }

    public void calcularVelocitat(){
        this.velX =  (this.factVelX *this.amplada);
    }

    public ArrayList<ObjecteJoc> getObjects(){
        return this.obj;
    }

    public void setTextTrans(String txt){
        this.txtTrans = txt;
    }

    public String getTextTrans(){
        return this.txtTrans;
    }


    public void setVelX(float vel){
    this.velX=vel;
}

    public float getVelX(){
        return this.velX;
    }
    public void setAlcada(float alcada) {
        this.alcada = alcada;
    }

    public float getAmplada() {
        return amplada;
    }

    public void setAmplada(float amplada) {
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


    public float getFactorEscalatge() {
        return factorEscalatge;
    }

    public void setFactorEscalatge(float factorEscalatge) {
        this.factorEscalatge = factorEscalatge;
    }



    public Bitmap getFons() {
        return fons;
    }

    public void setFons(Bitmap fons) {
        this.fons = fons;
    }

    public Bitmap getFons2() {
        return fons2;
    }

    public void setFons2(Bitmap fons2) {
        this.fons2 = fons2;
    }

    public Obstacles getObstacles() {
        return obstacles;
    }

    public void setObstacles(Obstacles obstacles) {
        this.obstacles = obstacles;
    }

    public String getNomImg() {
        return nomImg;
    }

    public void setNomImg(String nomImg) {
        this.nomImg = nomImg;
    }

    public String getNomImg2() {
        return nomImg2;
    }

    public void setNomImg2(String nomImg2) {
        this.nomImg2 = nomImg2;
    }
}
