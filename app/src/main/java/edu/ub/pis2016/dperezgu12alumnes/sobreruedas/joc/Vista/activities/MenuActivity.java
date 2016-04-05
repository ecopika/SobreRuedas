package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

public class MenuActivity extends Activity {

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

        //ImageView jugar = (ImageView) findViewById(R.id.imageView);
        //jugar.setOnClickListener(new View.OnClickListener(){
           // public void onClick(View v){
             //   seleccioPersonatge();
            //}
        //});
    }

    public void seleccioPersonatge(View view) {

        startActivity(new Intent(getApplicationContext(), seleccioPersonatgeActivity.class));
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