package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.BaseDades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ecopika on 10/04/16.
 */
public class BaseDades extends SQLiteOpenHelper {

    BufferedReader lector=null;
    Context cnt;


    public BaseDades(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        cnt = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> sentencies = getSentenciesSQL();
        for(int i = 0;i<sentencies.size();i++) {
            if(i==1){
                String compt = "SELECT count(*) FROM personatge_ES";
                Cursor mcursor = db.rawQuery(compt, null);
                mcursor.moveToFirst();
                int icount = mcursor.getInt(0);
                if (icount == 0) {
                    db.execSQL(sentencies.get(i));
                }
            }
            else {
                db.execSQL(sentencies.get(i));
            }

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//carreguem cada linia de l'script sql per ser executada desde el programa
    private ArrayList<String> getSentenciesSQL(){
        ArrayList<String> sentencies = new ArrayList<String>();
        try{
            lector = new BufferedReader(new InputStreamReader(cnt.getAssets().open("db/generaBD.sql")));
            String linia;
            while((linia=lector.readLine())!=null){
                sentencies.add(linia);
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }finally {
            if(lector != null){
                try {
                    lector.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return sentencies;
    }
}
