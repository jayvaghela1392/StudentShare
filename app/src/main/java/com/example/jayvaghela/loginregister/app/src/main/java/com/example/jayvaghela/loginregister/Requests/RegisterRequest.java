package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI.LoginActivity;

/**
 * Created by jayvaghela on 23/04/2016.
 */
public class RegisterRequest extends AsyncTask<String, Void, String> {

    String url = "/user/register";

    Context context;
    public RegisterRequest(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String password = params[0];
        String university = params[0];
        String course = params[0];
        String email = params[0];

        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&password="+password+"&university="+university+"&course="+course+"&email="+email);

        String response = http_methods.POST(url, parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equalsIgnoreCase("Success")) {
            Intent takeUserToLogin = new Intent(context, LoginActivity.class);
            Toast.makeText(context, "You have successfully made an account", Toast.LENGTH_SHORT).show();
            context.startActivity(takeUserToLogin);

        } else {
            Toast.makeText(context, "This username already exist", Toast.LENGTH_SHORT).show();
        }
    }
}
