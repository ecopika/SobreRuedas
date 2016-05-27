package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.utilitats.CanvasUtils;

public class MenuActivity extends Activity {
    private ViewHandlerMenu mn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //això obliga a que la pantalla sempre es mostri en portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //això treu la barra de la part superior de la pantalla
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);


        ViewHandlerMenu.setContext(this);

        mn.loadPreferences();
        //CanvasUtils.esborrarBaseDades(this);
        Log.i("dificultat",String.valueOf(ViewHandlerMenu.dificultat));
    }



    public void seleccioPersonatge(View view) {
        ViewMapaHandler.setActivity(this);
        ViewMapaHandler.setContext(this);
        ViewMapaHandler.generateBD();
        ViewMapaHandler.generaJoc();

        startActivity(new Intent(getApplicationContext(), seleccioPersonatgeActivity.class));
        finish();
    }

    public void seleccioOpcions(View view) {
        startActivity(new Intent(getApplicationContext(), OpcionsActivity.class));
    }


    public void seleccioPerfil(View view) {

        Intent intent =  new Intent(getApplicationContext(), seleccioPersonatgeActivity.class);
        intent.putExtra("Perfil","noJugar");
        startActivity(intent);

    }

    public void taulaPuntuacio(View view) {
        startActivity(new Intent(getApplicationContext(), TaulaPuntuacioActivity.class));


    }

    public void Credits(View view) {
        startActivity(new Intent(getApplicationContext(), CreditsActivity.class));

    }


}