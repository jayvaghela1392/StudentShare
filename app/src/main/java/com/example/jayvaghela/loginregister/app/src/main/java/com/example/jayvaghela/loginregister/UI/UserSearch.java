package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ModulesRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ResultsRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UsernameSearchRequest;

import java.util.concurrent.ExecutionException;

public class UserSearch extends AppCompatActivity implements View.OnClickListener{
     Button btnsm;
     Button btnsu;
     Button btnsc;

    EditText etCourse;
    EditText etModule ;
    EditText etUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        //set buttons
        etCourse = (EditText) findViewById(R.id.etSearchCourse);
        etModule = (EditText) findViewById(R.id.etSearchModule);
        etUsername = (EditText) findViewById(R.id.etSearchUsername);
        //search buttons

        btnsm = (Button) findViewById(R.id.btnsm);
        btnsm.setOnClickListener(this);

        btnsu = (Button) findViewById(R.id.btnsu);
        btnsu.setOnClickListener(this);

        btnsc = (Button) findViewById(R.id.btnsc);
        btnsc.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnsm:

                String module = etModule.getText().toString();
                String username = "";


                ResultsRequest rq = new ResultsRequest(this);
                rq.execute(new String[]{username, module});

                break;

            case R.id.btnsu:

                String module_ = "";
                String username_ = etUsername.getText().toString();


                ModulesRequest mq = new ModulesRequest(this);
                try {
                    String modulesResponse = mq.execute(new String[]{username_, module_}).get();
                    UsernameSearchRequest usr = new UsernameSearchRequest(this, modulesResponse);
                    usr.execute(new String[]{username_});


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnsc:

                String course = etCourse.getText().toString();

                break;


        }
    }
}
