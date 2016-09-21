package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class ModulesRequest extends AsyncTask<String, Void, String> {

    String url = "/modules/modulesearch?";

    Context context;

    public ModulesRequest(Context context)
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

            SharedPreference sp = new SharedPreference(context);

            sp.modules(s);

        } else {
            Toast.makeText(context, "Sorry, no matching Modules were found. Please search again.", Toast.LENGTH_SHORT).show();
        }
    }
}
