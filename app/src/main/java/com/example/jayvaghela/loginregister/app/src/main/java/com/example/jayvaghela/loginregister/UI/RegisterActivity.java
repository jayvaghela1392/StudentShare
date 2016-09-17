package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jayvaghela.loginregister.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCourse ;
    EditText etUni ;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword ;

    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       // etCourse = (EditText) findViewById(R.id.etCourse);
        etUni = (EditText) findViewById(R.id.etUniversity);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);


        bRegister = (Button)findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.bRegister:

                // String name = etName.getText().toString();
                 String username = etUsername.getText().toString();
                 String password = etPassword.getText().toString();


                break;

        }
    }
}
