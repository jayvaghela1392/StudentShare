package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.ModulesAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.EmailRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;
import java.util.List;

public class SearchProfile extends AppCompatActivity implements View.OnClickListener {

    Bundle bundle;

    TextView welcomeMsg;
    TextView tvUni;
    Button requestEmail;
    ListView lv;

    String username;

    SharedPreference sp;

    ImageView btnBackMatchesFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profile);

        btnBackMatchesFound = (ImageButton) findViewById(R.id.btnBackMatchesFound);
        btnBackMatchesFound.setOnClickListener(this);


        Intent intent = getIntent();
        bundle = intent.getExtras();

        sp = new SharedPreference(this);
        HashMap<String, String> user = sp.getUserDetails();

        String requests = user.get(SharedPreference.requests);

        welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        tvUni = (TextView) findViewById(R.id.tvUni);
        requestEmail = (Button) findViewById(R.id.btnRequestEmailAddress);
        lv = (ListView) findViewById(R.id.listView3);

        requestEmail.setOnClickListener(this);

        extractInfo();

        if (requestEmail.getText().toString().equalsIgnoreCase("Waiting for user to Accept")) {
            requestEmail.setClickable(false);
            requestEmail.setFocusable(false);
        }
        if (requests != null) {
            if (requests.contains(username)) {

                requestEmail.setClickable(false);
                requestEmail.setFocusable(false);
                requestEmail.setText("Waiting for user to Accept");
            }
        }
    }

    @Override
    public void onClick(View v) {

        EmailRequest er = new EmailRequest(this, requestEmail);

        er.execute(new String[]{username});

    }


    private void extractInfo(){

        XMLParser parser = new XMLParser();

        String moduleResponse = bundle.getString("modulesresponse");
        String userResponse = bundle.getString("userresponse");

        List<Modules> modules = parser.parseModules(moduleResponse);

        Student user = parser.parseUser(userResponse);

        HashMap<String, String> hashUser = sp.getUserDetails();

        String connections = hashUser.get(SharedPreference.connection);


        username = user.getUsername();


        if (connections!= null) {
            if (connections.contains(username)) {
                requestEmail.setText(user.getEmail());
            }
        }

        welcomeMsg.setText("welcome to " + username + "'s User Profile");
        tvUni.setText(user.getUni());

        lv.setAdapter(new ModulesAdapter(this, modules, connections));




    }
}
