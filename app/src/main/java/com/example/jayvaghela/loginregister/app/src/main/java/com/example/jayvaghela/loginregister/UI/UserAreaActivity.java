package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.UserRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserAreaActivity extends AppCompatActivity {

    SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //final EditText etName = (EditText) findViewById(R.id.etName);
        //final EditText etAge = (EditText) findViewById(R.id.etAge);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        sp = new SharedPreference(this);
        HashMap<String, String> user = sp.getUserDetails();
        String username = user.get(SharedPreference.username);


        //String username = intent.getStringExtra("username");
        //int age = intent.getIntExtra("age", -1);

        String message = "Welcome " + username + "!";
        welcomeMessage.setText(message);
        //etName.setText(name);
        //etUsername.setText(username);
        //etAge.setText(age + "");





        final EditText etUniversity = (EditText) findViewById(R.id.etUniversity);
        final EditText etModule = (EditText) findViewById(R.id.etModule);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);



        final Button bSubmit = (Button)findViewById(R.id.bSubmit);
        final Button bAlreadySearch = (Button)findViewById(R.id.bAlreadySearch);


        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String university = etUniversity.getText().toString();
                final String module = etModule.getText().toString();
                final String email = etEmail.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            //response=response.replaceAll("\n", "\\n" );
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(UserAreaActivity.this, UserSearch.class);
                                UserAreaActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                UserRequest userRequest = new UserRequest(university, module, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                queue.add(userRequest);

            }
        });

        bAlreadySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAreaActivity.this, UserSearch.class);
                UserAreaActivity.this.startActivity(intent);

            }
        });




    }
}













