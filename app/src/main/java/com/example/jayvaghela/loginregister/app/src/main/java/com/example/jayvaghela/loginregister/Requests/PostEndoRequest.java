package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;


public class PostEndoRequest extends AsyncTask<String, Void, String> {
    String url = "/endo/postendo?";

    Context context;
    String username;

    SharedPreference sp;

    public PostEndoRequest(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {


        sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();
        username = user.get(SharedPreference.username);

        String moduser = params[0];
        String module = params[1];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("endouser="+username+"&moduser="+moduser+"&module="+module);
        String response = http_methods.POST(url, parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equalsIgnoreCase("Success")) {

            Toast.makeText(context, "You have successfully endorsed this module", Toast.LENGTH_SHORT).show();

        } else {

        }
    }
}


