package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.ModulesAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;

import java.util.List;

public class SearchProfile extends AppCompatActivity implements View.OnClickListener{

    Bundle bundle;

    TextView welcomeMsg;
    TextView tvUni;
    Button requestEmail;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profile);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        tvUni = (TextView) findViewById(R.id.tvUni);
        requestEmail = (Button) findViewById(R.id.btnRequestEmailAddress);
        lv = (ListView) findViewById(R.id.listView3);

        requestEmail.setOnClickListener(this);

        extractInfo();
    }

    @Override
    public void onClick(View v) {

    }


    private void extractInfo(){

        XMLParser parser = new XMLParser();

        String moduleResponse = bundle.getString("modulesresponse");
        String userResponse = bundle.getString("userresponse");

        List<Modules> modules = parser.parseModules(moduleResponse);

        Student user = parser.parseUser(userResponse);

        welcomeMsg.setText("Welcome to " + user.getUsername() + "'s User Profile");
        tvUni.setText(user.getUni());

        lv.setAdapter(new ModulesAdapter(this, modules));

    }
}
