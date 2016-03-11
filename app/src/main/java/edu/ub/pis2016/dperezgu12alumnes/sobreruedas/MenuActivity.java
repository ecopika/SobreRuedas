package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import org.newdawn.slick.

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void seleccioPersonatge(View view) {
        startActivity(new Intent(getApplicationContext(), seleccioPersonatgeActivity.class));
    }

    public void seleccioOpcions(View view) {
        startActivity(new Intent(getApplicationContext(), OpcionsActivity.class));
    }


    public void seleccioPerfil(View view) {
        startActivity(new Intent(getApplicationContext(), PerfilActivity.class));

    }

    public void taulaPuntuacio(View view) {
        startActivity(new Intent(getApplicationContext(), TaulaPuntuacioActivity.class));

    }

    public void Credits(View view) {
        startActivity(new Intent(getApplicationContext(), CreditsActivity.class));

    }


}