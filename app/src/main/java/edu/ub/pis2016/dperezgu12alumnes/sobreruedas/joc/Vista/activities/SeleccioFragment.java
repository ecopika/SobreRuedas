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
import android.widget.ImageButton;
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
    public static final String IMAGE = "image";

    static int[] img = {R.mipmap.nuriafotograma, R.drawable.coche10, R.drawable.coche13};

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ctrl= new ViewHandlerMenu(this.getContext());
        nuria = ctrl.generatePersonatge();
        Bundle args = getArguments();

        View rootView = inflater.inflate(R.layout.activity_seleccio_personatge, container, false);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView);
        image1.setImageResource(img[args.getInt(IMAGE)]);
        TextView text = (TextView) rootView.findViewById(R.id.textView);
        text.setText(readFile(args.getInt(IMAGE)));
        Button buttonL = (Button) rootView.findViewById(R.id.buttonL);
        buttonL.setText("LEFT");
        buttonL.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/calibriz.ttf"));
        Button buttonR = (Button) rootView.findViewById(R.id.buttonR);
        buttonR.setText("RIGHT");
        buttonR.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/calibriz.ttf"));
        ImageButton btnJugar = (ImageButton) rootView.findViewById(R.id.imageButton);


        if(args.getInt("noJugar")==0) btnJugar.setVisibility(View.INVISIBLE);
        

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
