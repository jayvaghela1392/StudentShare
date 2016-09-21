package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class ModulesAddRequest extends AsyncTask<String, Void, String> {

    String url = "/modules/addmodule?";

    Context context;
    SharedPreference sp;


    public ModulesAddRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String module = params[0];
        String rating = params[1];

        sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();
        String username = user.get(SharedPreference.username);


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&module="+module+"&rating="+rating);

        String response = http_methods.POST(url, parameters);
        if (response == null)
        {
            return null;
        } else {
            return response.replace("\n", "");
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            Toast.makeText(context, "You have added a module to your profile.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Sorry, You have already added this module", Toast.LENGTH_SHORT).show();
        }
    }
}
