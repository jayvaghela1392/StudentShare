package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.RegisterRequest;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etCourse ;
    EditText etUni ;
    EditText etEmail;
    EditText etUsername;
    EditText etPassword ;

    Button bRegister;
    ImageButton btnBackRegister;

    RegisterRequest rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etCourse = (EditText) findViewById(R.id.etCourse);
        etUni = (EditText) findViewById(R.id.etUniversity);
        etEmail = (EditText) findViewById(R.id.etEmailAddress);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);


        bRegister = (Button)findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);

        btnBackRegister = (ImageButton)findViewById(R.id.btnBackRegister);
        btnBackRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.bRegister:

                 String course = etCourse.getText().toString();
                 String username = etUsername.getText().toString();
                 String password = etPassword.getText().toString();
                 String email = etEmail.getText().toString();
                 String uni = etUni.getText().toString();

                rq = new RegisterRequest(this);
                rq.execute(new String[]{username, password, uni, course, email});
                break;

            case R.id.btnBackRegister:
                Intent takeUserToLogin = new Intent(this, LoginActivity.class );
                startActivity(takeUserToLogin);
                break;


        }



    }
}
