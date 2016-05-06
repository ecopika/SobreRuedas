package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.BaseDades.BaseDades;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Mapa;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.ObjecteJoc;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Obstacles;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.PCadiraRodes;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

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

    public ArrayList<Mapa> getMapes(){
        ArrayList<Mapa> mps = new ArrayList<Mapa>();
        Cursor c = db.rawQuery("SELECT * FROM mapa",null);

        if(c.moveToFirst()){
            do{
                Mapa m = new Mapa(0,0);
                m.setId(c.getInt(0));
                m.setIniciX(c.getFloat(1));
                m.setIniciY(c.getFloat(2));
                m.setFons(CanvasUtils.loadBitmapFromString(cnt, c.getString(3)));
                m.setFons2(CanvasUtils.loadBitmapFromString(cnt, c.getString(4)));
                Obstacles o = getObstacle(m.getId());
                m.setObstacles(o);
                mps.add(m);
            }while (c.moveToNext());
        }
        return mps;
    }

//obtenim els objectes del joc(arbres, edificis, vehicles...) de la base de dades
    public ArrayList<ObjecteJoc> getObjectes(int idMapa){
        ArrayList<ObjecteJoc> obj = new ArrayList<ObjecteJoc>();

        Cursor c = db.rawQuery("SELECT o.ID,o.IMATGE,o.PROFUNDITAT,o.X,o.Y, m.IDMAPA FROM objectesmapa o JOIN mapa_object m ON m.IDMAPA=? AND m.IDOBJECTE = o.ID",new String[]{String.valueOf(idMapa)} );
        if(c.moveToFirst()){
            do{
                int id = c.getInt(0);
                ObjecteJoc p = new ObjecteJoc(c.getString(1),c.getInt(2),cnt);
                p.setX(c.getFloat(3));
                p.setY(c.getFloat(4));
                obj.add(p);
            }while(c.moveToNext());
        }
        return obj;
    }
//obtenim els obstacles de cada mapa a partir del seu id
    public Obstacles getObstacle(int idMap){
        Cursor c = db.rawQuery("SELECT * FROM obstacle WHERE MAP=?",new String[]{String.valueOf(idMap)});//això es fa per poder passar paràmetres
        Obstacles o = null;
        if(c.moveToFirst()){
            do{
                int id = c.getInt(0);
                int pos = c.getInt(1);
                String nomImg = c.getString(2);
                ArrayList<String> llisImgRes = new ArrayList<String>();

                Cursor cu = db.rawQuery("SELECT * FROM resposta WHERE OBSTACLE=?", new String[]{String.valueOf(id)});
                if(cu.moveToFirst()){
                    do {
                        llisImgRes.add(cu.getString(1));
                    }while(cu.moveToNext());
                }

                o=new Obstacles(id,pos,nomImg,llisImgRes,cnt);
            }while(c.moveToNext());
        }

        return o;

    }



    public ArrayList<Personatge> getPersonatges(){
        ArrayList<Personatge> prs = new ArrayList<Personatge>();

        Cursor c = db.rawQuery("SELECT * FROM personatge_ES",null);
        if(c.moveToFirst()){
            do{
                Personatge p = new PCadiraRodes(c.getString(1),c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13),cnt,c.getString(14));
                p.setVides((CanvasUtils.loadBitmapFromString(cnt, c.getString(15))));
                p.setVides(CanvasUtils.loadBitmapFromString(cnt, c.getString(16)));
                prs.add(p);
            }while(c.moveToNext());
        }
        return prs;
    }



}
