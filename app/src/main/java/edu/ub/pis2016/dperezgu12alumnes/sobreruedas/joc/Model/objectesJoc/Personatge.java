package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc;

import android.graphics.Bitmap;

/**
 * Created by ecopika on 22/03/16.
 */
public abstract class Personatge {


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
    protected String imatge;

    protected String nom;


    public Personatge(String nom,String img, int edat, String colorPref, String animalPref, String menjarPref, String hobby, String idol, String pasions, String habits, String noSoporta, String frase, String somni) {
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
        this.imatge = img;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
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


}
