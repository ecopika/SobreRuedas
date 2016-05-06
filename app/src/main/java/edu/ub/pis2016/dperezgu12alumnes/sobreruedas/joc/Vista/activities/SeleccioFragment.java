package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.annotation.TargetApi;
import android.graphics.Color;
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
import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewMapaHandler;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

/**
 * Created by JRomario on 27/03/2016.
 */
public class SeleccioFragment extends Fragment{


    public static final String IMAGE = "image";

    static int[] img = {R.drawable.nuriafotograma, R.drawable.motoesq, R.drawable.motodret};

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();



        ViewMapaHandler.generatePersonatge();

        View rootView = inflater.inflate(R.layout.activity_seleccio_personatge, container, false);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView);
        image1.setImageResource(img[args.getInt(IMAGE)]);
        TextView text = (TextView) rootView.findViewById(R.id.textView);
        text.setText(readFile(args.getInt(IMAGE)));
        Button buttonL = (Button) rootView.findViewById(R.id.buttonL);
        buttonL.setText("Izquierda");
        buttonL.setTextColor(Color.WHITE);
        buttonL.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));
        Button buttonR = (Button) rootView.findViewById(R.id.buttonR);
        buttonR.setText("Derecha");
        buttonR.setTextColor(Color.WHITE);
        buttonR.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));
        Button btnJugar = (Button) rootView.findViewById(R.id.buttonPlay);
        btnJugar.setText("Jugar");
        btnJugar.setTextColor(Color.parseColor("#996699"));
        btnJugar.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));
        
        if(args.getInt("noJugar")==0){
            btnJugar.setText("Editar Perfil");
            btnJugar.setTextSize(19.3f);

        }
        return rootView;
    }


    private String readFile(int i){
        String text ="";
        switch (i){
            case 0:
                InputStream inputStream = getResources().openRawResource(R.raw.texto2);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int j;
                try{
                    j = inputStream.read();
                    while(j != -1){
                        byteArrayOutputStream.write(j);
                        j = inputStream.read();
                    }
                    inputStream.close();
                }catch (IOException e) {

                }
                text = byteArrayOutputStream.toString();
                break;
            case 1:
                InputStream inputStream2 = getResources().openRawResource(R.raw.texto1);
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                //int i;
                try{
                    j = inputStream2.read();
                    while(j != -1){
                        byteArrayOutputStream2.write(j);
                        j = inputStream2.read();
                    }
                    inputStream2.close();
                }catch (IOException e) {

                }
                text = byteArrayOutputStream2.toString();
                break;
            case 2:
                InputStream inputStream3 = getResources().openRawResource(R.raw.texto3);
                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                //int i;
                try{
                    j = inputStream3.read();
                    while(j != -1){
                        byteArrayOutputStream3.write(j);
                        j = inputStream3.read();
                    }
                    inputStream3.close();
                }catch (IOException e) {

                }
                text =byteArrayOutputStream3.toString();
                break;
        }
        return text;
    }
}
