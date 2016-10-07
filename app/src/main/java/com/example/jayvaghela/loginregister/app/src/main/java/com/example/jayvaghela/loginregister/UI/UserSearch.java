package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.CourseSearchRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.GetCourseRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.GetModulesRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ModulesRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ResultsRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UsernameSearchRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserSearch extends AppCompatActivity implements View.OnClickListener{
     Button btnsm;
     Button btnsu;
     Button btnsc;
     Button btnUserProfile;



    EditText etCourse;
    EditText etModule ;
    EditText etUsername;

    AutoCompleteTextView listCourses;
    AutoCompleteTextView listUsername;
    AutoCompleteTextView listModules;

    SharedPreference sp;
    List<String> courseList ;
    List<String> usernameList ;
    List<String> modulesList ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        listCourses =(AutoCompleteTextView) findViewById(R.id.acSearchCourse);
        listUsername =(AutoCompleteTextView) findViewById(R.id.auSearchUsername);
        listModules =(AutoCompleteTextView) findViewById(R.id.acSearchModule);
        GetCourseRequest cr = new GetCourseRequest(this);
        GetModulesRequest mr = new GetModulesRequest(this);
         courseList = new ArrayList();
         usernameList = new ArrayList();
        modulesList = new ArrayList();

        try {


            List<Student> allStudents = cr.execute(new String[]{}).get();
            List<Modules> allModules = mr.execute(new String[]{}).get();
            for (Student student : allStudents)
            {

                if(!courseList.contains(student.getCourse()))
                {

                    courseList.add(student.getCourse());
                }

            }

            for (Student student : allStudents)
            {

                if(!usernameList.contains(student.getUsername()))
                {

                    usernameList.add(student.getUsername());
                }

            }

            for (Modules modules : allModules)
            {

                if(!modulesList.contains(modules.getModule()))
                {

                    modulesList.add(modules.getModule());
                }

            }

            ArrayAdapter<String> courseListAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, courseList);

            ArrayAdapter<String> usernameListAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, usernameList);

            ArrayAdapter<String> modulesListAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, modulesList);

            listCourses.setAdapter(courseListAdapter);
            listUsername.setAdapter(usernameListAdapter);
            listModules.setAdapter(modulesListAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //set buttons
        etModule = (EditText) findViewById(R.id.acSearchModule);

        //search buttons

        btnsm = (Button) findViewById(R.id.btnsm);
        btnsm.setOnClickListener(this);

        btnsu = (Button) findViewById(R.id.btnsu);
        btnsu.setOnClickListener(this);

        btnsc = (Button) findViewById(R.id.btnsc);
        btnsc.setOnClickListener(this);

        btnUserProfile = (Button) findViewById(R.id.btnUserProfile);
        btnUserProfile.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsm:

                String module = listModules.getText().toString();
                String username = "";

                if (modulesList.contains(module)) {
                    ResultsRequest rq = new ResultsRequest(this);
                    rq.execute(new String[]{username, module});
                } else {

                    Toast.makeText(this, "Sorry, that module does not exist", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btnsu:

                String module_ = "";
                String username_ = listUsername.getText().toString();

                if (usernameList.contains(username_)) {


                    ModulesRequest mq = new ModulesRequest(this);
                    try {
                        String modulesResponse = mq.execute(new String[]{username_, module_}).get();
                        if (modulesResponse != null) {
                            UsernameSearchRequest usr = new UsernameSearchRequest(this, modulesResponse);
                            usr.execute(new String[]{username_});
                        } else {

                            Toast.makeText(this, "Sorry, that user does not have any modules listed", Toast.LENGTH_SHORT).show();

                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }else {

                    Toast.makeText(this, "Sorry, that username doesnt seem to exist", Toast.LENGTH_SHORT).show();

                }

                    break;

                    case R.id.btnsc:

                        String course = listCourses.getText().toString();

                        if (courseList.contains(course)) {

                            CourseSearchRequest csr = new CourseSearchRequest(this);
                            csr.execute(new String[]{course});
                        } else {
                            Toast.makeText(this, "You have not selected a course that is available", Toast.LENGTH_SHORT).show();
                        }


                        break;

                    case R.id.btnUserProfile:

                        sp = new SharedPreference(this);

                        HashMap<String, String> user = sp.getUserDetails();

                        String usrname = user.get(SharedPreference.username);
                        String mod = "";

                        ModulesRequest mq_ = new ModulesRequest(this);
                        Bundle bundle = new Bundle();
                        try {
                            String modulesResponse = mq_.execute(new String[]{usrname, mod}).get();
                            bundle.putString("moduleresponse", modulesResponse);


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        Intent takeUserToProfile = new Intent(this, UserProfileActivity.class);
                        takeUserToProfile.putExtras(bundle);
                        startActivity(takeUserToProfile);
                }
        }


        }







