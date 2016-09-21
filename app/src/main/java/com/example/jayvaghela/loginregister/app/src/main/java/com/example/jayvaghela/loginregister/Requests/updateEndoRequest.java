package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class updateEndoRequest extends AsyncTask<String, Void, String> {

    String url = "/modules/updateendo";

    Context context;

    public updateEndoRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String module = params[1];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("moduser="+username+"&module="+module);

        String response = http_methods.PUT(url, parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

        } else {
            //Toast.makeText(context, "Nothing found", Toast.LENGTH_SHORT).show();
        }
    }
}
