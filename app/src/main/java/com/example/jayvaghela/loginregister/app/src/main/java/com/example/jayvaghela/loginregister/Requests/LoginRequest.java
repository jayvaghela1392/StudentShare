package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI.Welcome;


public class LoginRequest extends AsyncTask<String, Void, String> {
    String url = "/user/login?";

    Context context;
    String username;

    SharedPreference sp;

    public LoginRequest(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
         username = params[0];
        String password = params[1];


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&password="+password);

        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equalsIgnoreCase("Valid")) {
            Intent takeUserToWelcome = new Intent(context, Welcome.class);
            Toast.makeText(context, "welcome " + username, Toast.LENGTH_SHORT).show();

            sp = new SharedPreference(context);
            sp.saveUsername(username);
            context.startActivity(takeUserToWelcome);

        } else {
            Toast.makeText(context, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }
}


