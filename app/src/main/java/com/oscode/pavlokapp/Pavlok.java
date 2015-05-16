package com.oscode.pavlokapp;

/**
 * Created by kluo on 5/16/15.
 */
/**
 * Created by tristan on 5/16/15.
 */
import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Pavlok {
    private class PavlokTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(urls[0]);
                request.setURI(website);
                HttpResponse response = httpclient.execute(request);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return "done";
        }
    }

    //Use syntax Pavlok.getInstance().action(value)

    public void beep(int value){
        new PavlokTask().execute("https://pavlok.herokuapp.com/api/LW1NraaGtC/beep/"
                .concat(Integer.toString(value)));
    }
    public void led(int value){
        new PavlokTask().execute("https://pavlok.herokuapp.com/api/LW1NraaGtC/led/"
                .concat(Integer.toString(value)));
    }
    public void vibrate(int value){
        new PavlokTask().execute("https://pavlok.herokuapp.com/api/LW1NraaGtC/vibrate/"
                .concat(Integer.toString(value)));
    }
    public void shock(int value){
        new PavlokTask().execute("https://pavlok.herokuapp.com/api/LW1NraaGtC/shock/"
                .concat(Integer.toString(value)));
    }

    static private Pavlok instance;

    public static Pavlok getInstance(){
        if(instance == null){
            instance = new Pavlok();
        }
        return instance;
    }
    private Pavlok(){
    }
}