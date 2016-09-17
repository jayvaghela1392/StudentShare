package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI.Results;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class ResultsRequest extends AsyncTask<String, Void, String> {

    String url = "/modules/modulesearch?";

    Context context;

    public ResultsRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String module = params[1];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&module="+module);

        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            Bundle bundle = new Bundle();
            bundle.putString("response", s);

            Intent takeUserToResults = new Intent(context, Results.class);
            takeUserToResults.putExtras(bundle);
            context.startActivity(takeUserToResults);

        } else {
            Toast.makeText(context, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }
}
