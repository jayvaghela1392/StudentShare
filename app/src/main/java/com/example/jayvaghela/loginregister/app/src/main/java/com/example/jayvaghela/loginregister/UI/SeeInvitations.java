package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.RequestsAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.AcceptRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.DeclineRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeeInvitations extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_invitations);

        lv = (ListView) findViewById(R.id.listView3);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String response = bundle.getString("userresponse");

        XMLParser parser = new XMLParser();

        Student student = parser.parseUser(response);

        if (student.getInvitations() != null) {
            List<String> invites = new ArrayList(Arrays.asList(student.getInvitations().replace(" ", "").split(",")));

            lv.setAdapter(new RequestsAdapter(this, invites));
            lv.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        final CharSequence[] items = {"Accept", "Decline"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invite Options");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        builder.setItems(items, new DialogInterface.OnClickListener() {

            TextView tv = (TextView) findViewById(R.id.etUsername);

            String inviter = tv.getText().toString();
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].toString().equalsIgnoreCase("Accept"))
                {

                    AcceptRequest ar = new AcceptRequest(SeeInvitations.this);
                    ar.execute(new String[]{inviter});
                    Intent takeUserHome = new Intent(SeeInvitations.this, UserProfileActivity.class);
                    startActivity(takeUserHome);


                } else if(items[item].toString().equalsIgnoreCase("Decline"))
                {
                    DeclineRequest dr = new DeclineRequest(SeeInvitations.this);
                    dr.execute(new String[]{inviter});

                    Intent takeUserHome = new Intent(SeeInvitations.this, UserProfileActivity.class);
                    startActivity(takeUserHome);

                }
            }
        });
        builder.show();


    }
}
