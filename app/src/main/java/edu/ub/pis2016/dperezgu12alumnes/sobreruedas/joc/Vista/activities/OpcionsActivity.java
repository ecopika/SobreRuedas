package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;


public class OpcionsActivity extends Activity {


    private final Integer  FACIL = R.id.radioButtonFacil;
    private final Integer  MODERADO = R.id.radioButtonModerat;
    private final Integer  DIFICIL = R.id.radioButtonDificil;
    private final Integer  MUYDIFICIL = R.id.radioButtonMoltDificil;

    private SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button = (Button) findViewById(R.id.btnOpcionsGuardar);
        button.setText("Guardar\n Opcions");
        button.setTextColor(Color.WHITE);
        button.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));

        CheckBox so_chk = (CheckBox) findViewById(R.id.cbDesactivarSo);
        RadioGroup nivell_rg = (RadioGroup) findViewById(R.id.radioGroup);
        settings = ViewHandlerMenu.getSettings();
        if (settings != null){
            so_chk.setChecked(settings.getBoolean(ViewHandlerMenu.KEY_SO, false));
            Integer rb =  settings.getInt(ViewHandlerMenu.KEY_DIFICULTAT, -1);

            if (rb != -1) {
                RadioButton nivell_rb = (RadioButton) findViewById(rb);
                nivell_rb.setChecked(true);
            }
        }


    }

    public void guardarPreferencies(View view){

        CheckBox so_chk = (CheckBox) findViewById(R.id.cbDesactivarSo);
        RadioGroup nivell_rg = (RadioGroup) findViewById(R.id.radioGroup);


        ViewHandlerMenu.guardarPreferencies(so_chk.isChecked(), nivell_rg.getCheckedRadioButtonId());

        ViewHandlerMenu.setSettings(settings);
        // Commit the edits!
        Intent in = new Intent(this,MenuActivity.class);

        ViewHandlerMenu.loadPreferences();

        finish();
    }

    public void Facil (View view){

    }

    public void Moderat (View view){

    }

    public void Dificil (View view){

    }

    public void mDificil (View view){

    }

}
