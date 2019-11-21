package com.danito.p_peliculasencartelera;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Xml;

import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;

import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class LeerXML extends AsyncTask<Void, Void, Void> {
    private final WeakReference<RecyclerView> recycler;
    private URL url;
    private WeakReference<Dialog> dialog;
    private ArrayList<Pelicula> peliculas;

    public LeerXML(Dialog dialog, RecyclerView recyclerView) {
        this.dialog = new WeakReference<>(dialog);
        this.recycler = new WeakReference<RecyclerView>(recyclerView);
        try {
            this.url = new URL("http://rss.sensacine.com/cine/encartelera?format=xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        recycler.get().setAdapter(new Adaptador(peliculas));
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Pelicula pelicula = null;
        XmlPullParser parser = Xml.newPullParser();
        boolean empezarLectura = false;
        try {
            URLConnection conn = url.openConnection();
            parser.setInput(conn.getInputStream(), "UTF-8");
            int evento = parser.getEventType();
            while (evento != XmlPullParser.END_DOCUMENT) {
                switch (evento) {
                    case XmlPullParser.START_DOCUMENT:
                        peliculas = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        if (!empezarLectura) {
                            empezarLectura = parser.getName().equals("item");
                        }
                        if (empezarLectura) {
                            switch (parser.getName()) {
                                case "item":
                                    pelicula = new Pelicula();
                                    break;
                                case "title":
                                    pelicula.setTitulo(parser.nextText());
                                    break;
                                case "description":
                                    pelicula.setDescripcion(parser.nextText());
                                    break;
                                case "enclosure":
                                    pelicula.setUrl(parser.getAttributeValue(null, "url"));
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            peliculas.add(pelicula);
                        }
                        break;
                }
                evento = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.get().cancel();
        return null;
    }
}
