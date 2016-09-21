package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.LoginRequest;

public class UpdateDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSubmitModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);


        btnSubmitModule = (Button) findViewById(R.id.btnSubmitModule);
        btnSubmitModule.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnSubmitModule:

                Intent btnSubmitModule = new Intent(this, UserProfileActivity.class );
                startActivity(btnSubmitModule);
                break;
        }
    }
}
