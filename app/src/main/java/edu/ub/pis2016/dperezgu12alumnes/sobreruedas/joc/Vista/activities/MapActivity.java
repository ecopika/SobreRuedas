package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.MapaView;

public class MapActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //això obliga a que la pantalla sempre es mostri en portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_map);
        MapaView mpVi = (MapaView)findViewById(R.id.MapView);
        ViewMapaHandler.init(this);
        mpVi.initGameThread(this);

    }

    @Override
    public void onBackPressed() {
        ViewMapaHandler.pauseThread();
        new AlertDialog.Builder(this)
            .setTitle("Tornar al menú")
                .setMessage("Quieres volver al menú principal?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ViewMapaHandler.finishThread();
                        //MapActivity.this.finish();
                        Intent inMain=new Intent(MapActivity.this, MenuActivity.class);
                        inMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inMain);

                    }

                } )
                .setNegativeButton("No",new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ViewMapaHandler.resumeThread();
                    }
                })
        .show();


        //Intent returnMain = new Intent("android.intent.action.MAIN");
        //startActivity(returnMain);
    }




}
