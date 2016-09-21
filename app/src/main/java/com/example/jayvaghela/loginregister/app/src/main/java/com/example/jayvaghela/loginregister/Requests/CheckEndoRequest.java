package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;


public class CheckEndoRequest extends AsyncTask<String, Void, String> {
    String url = "/endo/checkendo?";

    Context context;
    String username;
    ImageButton imgBtn;

    SharedPreference sp;

    public CheckEndoRequest(Context context, ImageButton imgBtn){
        this.context = context;
        this.imgBtn = imgBtn;
    }

    @Override
    protected String doInBackground(String... params) {
        // username = params[0];
         sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();
        username = user.get(SharedPreference.username);

        String moduser = params[0];
        String module = params[1];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("endouser="+username+"&moduser="+moduser+"&module="+module);
        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equalsIgnoreCase("Available")) {



        } else {
            imgBtn.setFocusable(false);
            imgBtn.setVisibility(View.INVISIBLE);
          //  Toast.makeText(context, "Sorry, you have already endorsed", Toast.LENGTH_SHORT).show();
        }
    }
}


