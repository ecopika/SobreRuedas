package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista.DrawingPanel;

public class IntroActivity extends Activity {

    private static final long TEMPS_MOSTRA = 8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //això obliga a que la pantalla sempre es mostri en portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //això treu la barra de la part superior de la pantalla
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DrawingPanel pantallaInici = new DrawingPanel(this,getBaseContext().getResources().getDisplayMetrics());

        setContentView(pantallaInici);
        pantallaInici.requestFocus();
        //fem una tasca temporal pq transcorregut un temps es tanqui l'activity
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //iniciem la següent activity
                Intent mainIntent = new Intent().setClass(IntroActivity.this,MenuActivity.class);
                startActivity(mainIntent);

                //tanquem l'activity actual
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,TEMPS_MOSTRA);



    }

}
