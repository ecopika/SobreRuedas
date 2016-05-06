package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.GifMovieView;

/**
 * Created by ecopika on 22/03/16.
 */
public abstract class Personatge {

    //atributs informatius
    protected int edat;
    protected String colorPref;
    protected String animalPref;
    protected String menjarPref;
    protected String hobby;
    protected String idol;
    protected String pasions;
    protected String habits;
    protected String noSoporta;
    protected String frase;
    protected String somni;
    protected String nom;

    //atributs del personatge al mapa
    protected float x;
    protected float y;
    protected float gifX;
    protected float gifY;
    protected int amplada;
    protected int alcada;
    protected int velX;
    protected int velY;
    protected boolean movX;
    protected boolean movY;
    protected int profunditat;
    protected Bitmap img;
    protected String nomImatge;
    protected Movie gif;
    protected String nomGif;
    protected Context cnt;
    protected int numVides;
    protected ArrayList<Bitmap> vides;

//config gif
    private long tempsInici;
    private GifMovieView gifPrs;
    private InputStream stream;




    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Personatge(String nom, int edat, String colorPref, String animalPref, String menjarPref, String hobby, String idol, String pasions, String habits, String noSoporta, String frase, String somni, String nomImatge, Context cnt, String nomGif) {
        this.nom = nom;
        this.edat = edat;
        this.colorPref = colorPref;
        this.animalPref = animalPref;
        this.menjarPref = menjarPref;
        this.hobby = hobby;
        this.idol = idol;
        this.pasions = pasions;
        this.habits = habits;
        this.noSoporta = noSoporta;
        this.frase = frase;
        this.somni = somni;
        this.alcada = CanvasUtils.getHeightScreen()/3;
        this.amplada = this.alcada/2;
        this.y= 0;
        this.x = 0;
        this.gifX = 0;
        this.gifY=0;
        this.velX = 0;
        this.velY = 0;
        this.movX = false;
        this.movY = false;
        this.profunditat = 10;
        this.nomImatge = nomImatge;
        this.nomGif = nomGif;
        this.numVides = 0;
        vides = new ArrayList<Bitmap>();


        this.cnt = cnt;
        loadImage();
        loadGif();


    }

    public void loadImage(){
        //int resourceId = cnt.getResources().getIdentifier(nomImatge, "drawable", cnt.getPackageName());

        this.img = BitmapFactory.decodeResource(cnt.getResources(), R.drawable.nuriafotograma);
    }

    public void loadGif(){
        setStream(null);
        try {
            setStream(cnt.getAssets().open(nomGif));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gifPrs = new GifMovieView(cnt,stream);

    }

    public String getNomGif() {
        return nomGif;
    }

    public void setNomGif(String nomGif) {
        this.nomGif = nomGif;
    }

    public Movie getGif() {
        return gif;
    }

    public void setGif(Movie gif) {
        this.gif = gif;
    }

    public long getTempsInici() {
        return tempsInici;
    }

    public void setTempsInici(long tempsInici) {
        this.tempsInici = tempsInici;
    }

    public GifMovieView getGifPrs() {
        return gifPrs;
    }

    public void setGifPrs(GifMovieView gifPrs) {
        this.gifPrs = gifPrs;
    }

    public InputStream getStream(){
        return this.stream;
    }

    public void setStream(InputStream s){
        this.stream = s;
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

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getGifX() {
        return gifX;
    }

    public void setGifX(float gifX) {
        this.gifX = gifX;
    }

    public float getGifY() {
        return gifY;
    }

    public void setGifY(float gifY) {
        this.gifY = gifY;
    }

    public float getY() {

        return y;
    }

    public void setY(float y) {
        this.y = y;
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


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getColorPref() {
        return colorPref;
    }

    public void setColorPref(String colorPref) {
        this.colorPref = colorPref;
    }

    public String getAnimalPref() {
        return animalPref;
    }

    public void setAnimalPref(String animalPref) {
        this.animalPref = animalPref;
    }

    public String getMenjarPref() {
        return menjarPref;
    }

    public void setMenjarPref(String menjarPref) {
        this.menjarPref = menjarPref;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIdol() {
        return idol;
    }

    public void setIdol(String idol) {
        this.idol = idol;
    }

    public String getPasions() {
        return pasions;
    }

    public void setPasions(String pasions) {
        this.pasions = pasions;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getNoSoporta() {
        return noSoporta;
    }

    public void setNoSoporta(String noSoporta) {
        this.noSoporta = noSoporta;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getSomni() {
        return somni;
    }

    public void setSomni(String somni) {
        this.somni = somni;
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

    public int getNumVides() {
        return numVides;
    }

    public void setNumVides(int numVides) { this.numVides = numVides; }

    public ArrayList<Bitmap> getVides() { return vides; }

    public void setVides(Bitmap imgVides) { vides.add(imgVides); }

}
