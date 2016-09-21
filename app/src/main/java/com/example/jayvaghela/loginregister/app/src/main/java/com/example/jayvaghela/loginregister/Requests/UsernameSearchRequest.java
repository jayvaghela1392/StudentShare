package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI.SearchProfile;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class UsernameSearchRequest extends AsyncTask<String, Void, String> {

    String url = "/user/searchuser?";

    Context context;

    String modulesResponse;

    public UsernameSearchRequest(Context context, String modulesresponse)
    {
        this.context = context;
        this.modulesResponse = modulesresponse;
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username);

        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            Bundle bundle = new Bundle();
            bundle.putString("userresponse", s);
            bundle.putString("modulesresponse", modulesResponse);

            Intent takeUserToResults = new Intent(context, SearchProfile.class);
            takeUserToResults.putExtras(bundle);
            context.startActivity(takeUserToResults);

        } else {
            Toast.makeText(context, "Sorry, no matching Usernames were found. Please search again.", Toast.LENGTH_SHORT).show();
        }
    }
}
