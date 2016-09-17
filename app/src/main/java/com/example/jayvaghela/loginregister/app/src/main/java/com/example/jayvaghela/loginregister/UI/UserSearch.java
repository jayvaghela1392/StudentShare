package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ResultsRequest;

public class UserSearch extends AppCompatActivity implements View.OnClickListener{
     Button btnsm;
     Button btnsu;

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




    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnsm:

                //String university = etUniversity.getText().toString();
                String module = etModule.getText().toString();
                String username = "";


                ResultsRequest rq = new ResultsRequest(this);
                rq.execute(new String[]{username, module});

                break;

            case R.id.btnsu:

                //String university = etUniversity.getText().toString();
                String module_ = "";
                String username_ = etUsername.getText().toString();


                ResultsRequest rq_ = new ResultsRequest(this);
                rq_.execute(new String[]{username_, module_});

                break;


        }
    }
}
