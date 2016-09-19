
package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;


import android.content.Context;
import android.os.AsyncTask;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;

/**
 * Created by jayvaghela on 23/04/2016.
 */

public class UserRequest extends AsyncTask<String, Void, String> {

    String url = "/user/searchuser?";

    Context context;

    SharedPreference sp;


    public UserRequest(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();

        String username = user.get(SharedPreference.username);

        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username);

        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }


}
