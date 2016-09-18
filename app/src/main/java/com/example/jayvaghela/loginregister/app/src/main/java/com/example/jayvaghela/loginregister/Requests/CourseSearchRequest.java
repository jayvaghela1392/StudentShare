package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI.Results;

/**
 * Created by jayvaghela on 23/04/2016.
 */
public class CourseSearchRequest extends AsyncTask<String, Void, String> {


    String url = "/user/coursesearch?";

    Context context;
    String course;


    public CourseSearchRequest(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        course = params[0].replaceAll(" ", "+");;


        HTTP_Methods http_methods = new HTTP_Methods();
        String parameters = ("course="+course);

        String response = http_methods.GET(url + parameters);
        return response.replace("\n", "");
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            Bundle bundle = new Bundle();
            bundle.putString("courseresponse", s);

            Intent takeUserToResults = new Intent(context, Results.class);
            takeUserToResults.putExtras(bundle);
            context.startActivity(takeUserToResults);

        } else {
            Toast.makeText(context, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }

}