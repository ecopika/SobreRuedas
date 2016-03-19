package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;

public class CreditsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //això obliga a que la pantalla sempre es mostri en portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //això treu la barra de la part superior de la pantalla
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_credits);



    }
}
