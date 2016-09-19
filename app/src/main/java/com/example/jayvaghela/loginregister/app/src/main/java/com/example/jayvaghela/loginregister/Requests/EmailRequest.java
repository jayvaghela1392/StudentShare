package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;


public class EmailRequest extends AsyncTask<String, Void, String> {
    String url = "/user/requests";

    Context context;

    SharedPreference sp;

    String requestedusr;

    Button button;
    public EmailRequest(Context context, Button button){
        this.context = context;

        this.button = button;
    }


    @Override
    protected String doInBackground(String... params) {

        requestedusr = params[0];

        sp = new SharedPreference(context);

        HashMap<String, String> user = sp.getUserDetails();

        String username = user.get(SharedPreference.username);

        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("username="+username+"&requestedusr="+requestedusr);

        String response = http_methods.PUT(url , parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s !=null) {
            sp.request(s);
            button.setText("Waiting for user to Accept");

        } else {
            Toast.makeText(context, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }
}


