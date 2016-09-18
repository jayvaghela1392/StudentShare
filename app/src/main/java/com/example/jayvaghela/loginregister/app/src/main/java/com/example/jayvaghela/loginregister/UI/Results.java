package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.ResultsProfileAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ModulesRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UsernameSearchRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Results extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String response = bundle.getString("response");

        XMLParser parser = new XMLParser();
       List<Modules> modules = parser.parseUsers(response);
        lv = (ListView) findViewById(R.id.listView);

        lv.setAdapter( new ResultsProfileAdapter(this, modules));
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView tvUsername = (TextView)view.findViewById(R.id.etUsername);

        String username = tvUsername.getText().toString();
        String module = "";

        ModulesRequest mq = new ModulesRequest(this);
        try {
            String modulesResponse = mq.execute(new String[]{username, module}).get();
            UsernameSearchRequest usr = new UsernameSearchRequest(this, modulesResponse);
            usr.execute(new String[]{username});


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
