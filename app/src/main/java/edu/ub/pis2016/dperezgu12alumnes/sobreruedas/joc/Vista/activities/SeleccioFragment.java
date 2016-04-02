package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

/**
 * Created by JRomario on 27/03/2016.
 */
public class SeleccioFragment extends Fragment{

    private ViewHandlerMenu ctrl;//controlador del menu
    private Personatge nuria;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ctrl= new ViewHandlerMenu(this.getContext());
        nuria = ctrl.generatePersonatge();

        View rootView = inflater.inflate(R.layout.activity_seleccio_personatge, container, false);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView);
        image1.setImageResource(R.mipmap.nuriafotograma);
        TextView text = (TextView) rootView.findViewById(R.id.textView);
        text.setText(readFile());
        Button buttonL = (Button) rootView.findViewById(R.id.buttonL);
        buttonL.setText("LEFT");
        buttonL.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/calibriz.ttf"));
        Button buttonR = (Button) rootView.findViewById(R.id.buttonR);
        buttonR.setText("RIGHT");
        buttonR.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/calibriz.ttf"));

        return rootView;
    }

    private String readFile(){
        /*File file = new File(Environment.getExternalStorageDirectory() + "/texto1.txt");
        if(!file.exists()){
            String line = "Don't exist.";
            return line;
        }
        String line = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
        }catch (IOException e){
        }
        return line;*/
        InputStream inputStream = getResources().openRawResource(R.raw.texto2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try{
            i = inputStream.read();
            while(i != -1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        }catch (IOException e) {

        }
        return byteArrayOutputStream.toString();
    }
}
