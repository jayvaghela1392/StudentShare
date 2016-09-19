package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.RequestsAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeeRequests extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_requests);

        lv = (ListView) findViewById(R.id.listView3);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String response = bundle.getString("userresponse");

        XMLParser parser = new XMLParser();

        Student student = parser.parseUser(response);

        if (student.getRequests() != null) {
            List<String> requests = new ArrayList(Arrays.asList(student.getRequests().replace(" ", "").split(",")));

            lv.setAdapter(new RequestsAdapter(this, requests));
        }
    }


}
