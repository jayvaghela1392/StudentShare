package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;

/**
 * Created by jayvaghela on 18/09/2016.
 */
public class DeclineRequest extends AsyncTask<String, Void, String> {

    Context context;

    String url = "/user/decline";

    SharedPreference sp;


    public DeclineRequest(Context context){

        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
       String inviter = params[0];

        sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();

        String username = user.get(SharedPreference.username);

        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&inviter="+inviter);

        String response = http_methods.PUT(url , parameters);
        return response.replace("\n", "");

    }

    @Override
    protected void onPostExecute(String s) {

    }
}
