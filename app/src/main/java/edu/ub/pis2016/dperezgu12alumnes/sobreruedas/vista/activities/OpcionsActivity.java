package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.vista.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;


public class OpcionsActivity extends Activity {
    public static final String PREFS_NAME = "SobreRuedasPref";
    public static final String  KEY_DIFICULTAT = "Dificultat";
    public static final Integer  FACIL = R.id.radioButtonFacil;
    public static final Integer  MODERADO = R.id.radioButtonModerat;
    public static final Integer  DIFICIL = R.id.radioButtonDificil;
    public static final Integer  MUYDIFICIL = R.id.radioButtonMoltDificil;
    public static final String KEY_SO = "So";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcions);

        CheckBox so_chk = (CheckBox) findViewById(R.id.cbDesactivarSo);
        RadioGroup nivell_rg = (RadioGroup) findViewById(R.id.radioGroup);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        so_chk.setChecked(settings.getBoolean(KEY_SO, false));
        Integer rb =  settings.getInt(KEY_DIFICULTAT, -1);
        if (rb != -1) {
            RadioButton nivell_rb = (RadioButton) findViewById(rb);
            nivell_rb.setChecked(true);
        }

    }

    public void guardarPreferencies(View view){
        CheckBox so_chk = (CheckBox) findViewById(R.id.cbDesactivarSo);
        RadioGroup nivell_rg = (RadioGroup) findViewById(R.id.radioGroup);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_SO, so_chk.isChecked());
        editor.putInt(KEY_DIFICULTAT, nivell_rg.getCheckedRadioButtonId());

        // Commit the edits!
        editor.commit();
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
