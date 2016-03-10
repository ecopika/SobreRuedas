package edu.ub.pis2016.dperezgu12alumnes.sobreruedas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.newdawn.slick.SlickActivity;

public class MenuActivity extends SlickActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu);
        start(newGame(),850,480);
    }
}
