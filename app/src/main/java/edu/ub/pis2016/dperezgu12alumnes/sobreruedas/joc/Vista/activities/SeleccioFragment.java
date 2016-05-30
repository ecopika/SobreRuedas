package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.activities;

import android.annotation.TargetApi;
import android.content.Context;
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

import java.util.ArrayList;

import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.R;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Controlador.ViewHandlerMenu;
import edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Model.objectesJoc.Personatge;

/**
 * Created by JRomario on 27/03/2016.
 */
public class SeleccioFragment extends Fragment{

    private ViewHandlerMenu menu;
    Context cnt;
    public static final String IMAGE = "image";
    private ArrayList<Personatge> pers;

    static int[] img = {R.drawable.nuriaalreves, R.drawable.motoesq, R.drawable.motodret};

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();


        menu = new ViewHandlerMenu((Context)cnt);
        pers = menu.getPersonatges();

        View rootView = inflater.inflate(R.layout.activity_seleccio_personatge, container, false);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.imageView);
        image1.setImageResource(img[args.getInt(IMAGE)]);

        TextView nom = (TextView) rootView.findViewById(R.id.textViewNom) ;
        nom.setText(pers.get(0).getNom());
        nom.setRotation(-15);
        nom.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));
        nom.setTextColor(Color.WHITE);

        TextView text = (TextView) rootView.findViewById(R.id.textView);
        String frase = pers.get(0).getFrase();
        String s1 = frase.substring(0,22);
        text.setText(s1+"\n"+"\t\t\t\t"+frase.substring(23));
        text.setRotation(-19);
        text.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/BrannbollFS_PERSONAL.ttf"));
        text.setTextColor(Color.WHITE);

        TextView text2 = (TextView) rootView.findViewById(R.id.textView2);
        text2.setText(getInfoPers());
        text2.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/calibri.ttf"));
        text2.setTextColor(Color.parseColor("#72449E"));

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
            //btnJugar.setTextSize(19.3f);

        }
        return rootView;
    }
    
    private String getInfoPers(){
        String texto = "Nombre: "+pers.get(0).getNom()+"\nEdad: "+pers.get(0).getEdat()+" años"+"\nColor Favorito: "+pers.get(0).getColorPref()+
                "\nAnimal Favorito: "+ pers.get(0).getAnimalPref()+"\nComida Favorita: "+pers.get(0).getMenjarPref()+"\nHobby: "+pers.get(0).getHobby()+
                "\nÍdolo: "+pers.get(0).getIdol()+"\nLe encanta "+pers.get(0).getPasions()+"\nAntes de irse dormir "+pers.get(0).getHabits()+
                "\nNo soporta "+pers.get(0).getNoSoporta()+ "\nSu sueño es "+pers.get(0).getSomni();
        return texto;
    }
}
