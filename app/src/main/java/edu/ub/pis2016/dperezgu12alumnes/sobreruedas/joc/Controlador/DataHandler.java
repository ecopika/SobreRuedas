package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.BaseDades.BaseDades;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.PCadiraRodes;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

/**
 * Created by ecopika on 22/03/16.
 */
public class DataHandler  {

    Context cnt;
    BaseDades bd;
    SQLiteDatabase db;

    public DataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        bd = new BaseDades(context, name, factory, version);
        cnt=context;
        db = bd.getWritableDatabase();

    }

    public static int getNumPersonatges(){
        // TODO: 22/03/16
        return 0;
    }



    public ArrayList<Personatge> getPersonatges(){
        ArrayList<Personatge> prs = new ArrayList<Personatge>();

        Cursor c = db.rawQuery("SELECT * FROM personatge_ES",null);
        if(c.moveToFirst()){
            do{
                Personatge p = new PCadiraRodes(c.getString(1),c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13),cnt,c.getString(14));
                prs.add(p);
            }while(c.moveToNext());
        }
        return prs;
    }



}
