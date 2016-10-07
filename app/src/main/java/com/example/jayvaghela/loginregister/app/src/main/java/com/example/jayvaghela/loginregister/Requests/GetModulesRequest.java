package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.List;

/**
 * Created by jayvaghela on 18/09/2016.
 */
public class GetModulesRequest extends AsyncTask<String, Void, List<Modules>> {

    Context context;

    String url = "/modules/";

    SharedPreference sp;


    public GetModulesRequest(Context context){

        this.context = context;
    }

    @Override
    protected List<Modules> doInBackground(String... params) {


        HTTP_Methods http_methods = new HTTP_Methods();


        String response = http_methods.GET(url + "");
        XMLParser parseCourse = new XMLParser();


        List <Modules> ModuleList = parseCourse.parseModules(response);
        return ModuleList;

    }

    @Override
    protected void onPostExecute(List<Modules> s) {

    }
}
