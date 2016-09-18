package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.jayvaghela.loginregister.R;

public class UserProfileActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpdateConnections:

                final CharSequence[] items = {"View Requests", "View Connections"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("View options");
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

                        } else if(items[item].toString().equalsIgnoreCase("View Connections"))
                        {

                        }
                    }
                });
                builder.show();

            break;
            case R.id.btnUpdateModules:
                Intent takeUserToUpdateModules = new Intent(this, UpdateDetailsActivity.class );
                startActivity(takeUserToUpdateModules);
                break;

        }
    }
}
