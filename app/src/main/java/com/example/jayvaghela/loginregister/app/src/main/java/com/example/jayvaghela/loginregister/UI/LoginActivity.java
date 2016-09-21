package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.LoginRequest;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    Button bLogin;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         etUsername = (EditText) findViewById(R.id.etUsername);
         etPassword = (EditText) findViewById(R.id.etPassword);
         bLogin = (Button) findViewById(R.id.bLogin);
         registerLink = (TextView) findViewById(R.id.tvRegisterHere);

         bLogin.setOnClickListener(this);
         registerLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bLogin:

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                LoginRequest lr = new LoginRequest(this);
                lr.execute(new String[]{username, password});
                break;

            case R.id.tvRegisterHere:

                Intent takeUserToRegistration = new Intent(this, RegisterActivity.class );
                startActivity(takeUserToRegistration);
                break;
        }
    }
}

