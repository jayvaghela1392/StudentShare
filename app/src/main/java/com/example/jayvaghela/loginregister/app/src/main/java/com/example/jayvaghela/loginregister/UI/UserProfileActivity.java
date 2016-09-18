package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jayvaghela.loginregister.R;

public class UserProfileActivity extends Activity implements View.OnClickListener {


    Button btnModules;
    Button btnConnections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        btnModules = (Button) findViewById(R.id.btnModules);
        btnModules.setOnClickListener(this);

        btnConnections = (Button) findViewById(R.id.btnConnections);
        btnConnections.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnConnections:

                final CharSequence[] items = {"View Requests", "View Connections", "View Invitations"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("View Options");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].toString().equalsIgnoreCase("View Requests"))
                        {

                            Intent takeUserToRequests = new Intent(UserProfileActivity.this, SeeRequests.class);
                            startActivity(takeUserToRequests);


                        } else if(items[item].toString().equalsIgnoreCase("View Connections"))
                        {

                            Intent takeUserToConnections = new Intent(UserProfileActivity.this, SeeConnections.class );
                            startActivity(takeUserToConnections);


                        } else if(items[item].toString().equalsIgnoreCase("View Invitations"))
                        {

                            Intent takeUserToInvitations = new Intent(UserProfileActivity.this, SeeInvitations.class );
                            startActivity(takeUserToInvitations);

                        }
                    }
                });
                builder.show();

            break;
            case R.id.btnModules:
                Intent takeUserToUpdateModules = new Intent(this, UpdateDetailsActivity.class );
                startActivity(takeUserToUpdateModules);
                break;

        }
    }

}
