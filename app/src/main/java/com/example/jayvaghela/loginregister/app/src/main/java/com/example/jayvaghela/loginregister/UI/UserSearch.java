package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.Results;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UserSearchRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserSearch extends AppCompatActivity implements View.OnClickListener{
     Button Qsearch;

    EditText etUniversity;
    EditText etModule ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        //set buttons
        etUniversity = (EditText) findViewById(R.id.etUniversity);
        etModule = (EditText) findViewById(R.id.etModule);

        //search buttons

        Qsearch = (Button) findViewById(R.id.Qsearch);
        Qsearch.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Qsearch:

               final  String university = etUniversity.getText().toString();
                final String module = etModule.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                               // String name = jsonResponse.getString("name");
                               // int age = jsonResponse.getInt("age");

                                //Intent intent = new Intent(LoginActivity.this, Welcome.class);
                                Intent intent = new Intent(UserSearch.this, Results.class);

                                intent.putExtra("university", university);
                                intent.putExtra("module", module);

                                UserSearch.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserSearch.this);
                                builder.setMessage("Search Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                };

                UserSearchRequest usersearchrequest = new UserSearchRequest(university, module, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserSearch.this);
                queue.add(usersearchrequest);


                break;


        }
    }
}
