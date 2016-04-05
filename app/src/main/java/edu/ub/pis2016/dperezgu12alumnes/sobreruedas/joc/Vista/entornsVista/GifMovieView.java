package edu.ub.pis2016.dperezgu12alumnes.sobreruedas.joc.Vista.entornsVista;

import android.content.Context;
import android.graphics.Movie;
import android.view.View;

import java.io.InputStream;

/**
 * Created by dperezgu12.alumnes on 04/04/16.
 */
public class GifMovieView extends View {

    private Movie movie;
    private InputStream mStream;

    public GifMovieView(Context context, InputStream stream) {
        super(context);

        mStream = stream;
        movie = Movie.decodeStream(mStream);

    }

    public Movie getMovie(){
        return movie;
    }


}
