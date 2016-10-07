package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.List;

/**
 * Created by jayvaghela on 18/09/2016.
 */
public class GetCourseRequest extends AsyncTask<String, Void, List<Student>> {

    Context context;

    String url = "/user/";

    SharedPreference sp;


    public GetCourseRequest(Context context){

        this.context = context;
    }

    @Override
    protected List<Student> doInBackground(String... params) {


        HTTP_Methods http_methods = new HTTP_Methods();


        String response = http_methods.GET(url + "");
        XMLParser parseCourse = new XMLParser();


        List <Student> StudentList = parseCourse.parseCourse(response);
        return StudentList;

    }

    @Override
    protected void onPostExecute(List<Student> s) {

    }
}
