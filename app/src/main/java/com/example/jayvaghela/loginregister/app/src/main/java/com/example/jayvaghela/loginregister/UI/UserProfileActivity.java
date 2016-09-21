package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters.ModulesAdapter;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Student;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Parsers.XMLParser;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UserRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserProfileActivity extends Activity implements View.OnClickListener {


    Button btnModules;
    Button btnConnections;
    ImageButton btnBackUserPro;
    ListView lv;

    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        btnBackUserPro = (ImageButton) findViewById(R.id.btnBackUserPro);
        btnBackUserPro.setOnClickListener(this);

        btnModules = (Button) findViewById(R.id.btnModules);
        btnModules.setOnClickListener(this);

        btnConnections = (Button) findViewById(R.id.btnConnections);
        btnConnections.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.listView2);

        sp = new SharedPreference(this);

        HashMap<String, String> user = sp.getUserDetails();
        String modulesResponse = user.get(SharedPreference.module);

        XMLParser parser =new XMLParser();

        List<Modules> modules = parser.parseModules(modulesResponse);

        lv.setAdapter(new ModulesAdapter(this, modules, "nothing"));

        UserRequest ur = new UserRequest(UserProfileActivity.this);
        try {
            String userResponse = ur.execute(new String[]{}).get();
            Student student = parser.parseUser(userResponse);

            sp.request(student.getRequests());
            sp.connection(student.getConnections());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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

                            UserRequest ur = new UserRequest(UserProfileActivity.this);
                            try {
                                String userResponse = ur.execute(new String[]{}).get();
                                Bundle bundle = new Bundle();
                                bundle.putString("userresponse", userResponse);

                                Intent takeUserToRequests = new Intent(UserProfileActivity.this, SeeRequests.class);
                                takeUserToRequests.putExtras(bundle);
                                startActivity(takeUserToRequests);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }



                        } else if(items[item].toString().equalsIgnoreCase("View Connections"))
                        {

                            UserRequest ur = new UserRequest(UserProfileActivity.this);
                            try {
                                String userResponse = ur.execute(new String[]{}).get();
                                Bundle bundle = new Bundle();
                                bundle.putString("userresponse", userResponse);

                                Intent takeUserToConnections = new Intent(UserProfileActivity.this, SeeConnections.class );
                                takeUserToConnections.putExtras(bundle);
                                startActivity(takeUserToConnections);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }



                        } else if(items[item].toString().equalsIgnoreCase("View Invitations"))
                        {


                            UserRequest ur = new UserRequest(UserProfileActivity.this);
                            try {
                                String userResponse = ur.execute(new String[]{}).get();
                                Bundle bundle = new Bundle();
                                bundle.putString("userresponse", userResponse);

                                Intent takeUserToInvitations = new Intent(UserProfileActivity.this, SeeInvitations.class );
                                takeUserToInvitations.putExtras(bundle);
                                startActivity(takeUserToInvitations);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }



                        }
                    }
                });
                builder.show();

            break;
            case R.id.btnModules:
                Intent takeUserToUpdateModules = new Intent(this, UpdateDetailsActivity.class );
                startActivity(takeUserToUpdateModules);
                break;

            case R.id.btnBackUserPro:
                Intent takeUserToHome = new Intent(this, SearchProfile.class );
                startActivity(takeUserToHome);
                break;
        }
    }

}
