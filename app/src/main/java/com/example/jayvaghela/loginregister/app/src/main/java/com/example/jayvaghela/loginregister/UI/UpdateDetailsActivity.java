package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ModulesAddRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.ModulesRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.SharedPreference;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class UpdateDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSubmitModule;
    EditText etAddModule;
    EditText etExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);


        btnSubmitModule = (Button) findViewById(R.id.btnSubmitModule);
        btnSubmitModule.setOnClickListener(this);

        etAddModule = (EditText) findViewById(R.id.etAddModule);
        etExp = (EditText) findViewById(R.id.etExperienceLevel);
    }


    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnSubmitModule:


                String mod = etAddModule.getText().toString();
                String exp = etExp.getText().toString();



                 if (exp.equalsIgnoreCase(""))
                {
                    Toast.makeText(this, "You have not entered a figure", Toast.LENGTH_SHORT).show();
                    break;
                } else if (mod.equalsIgnoreCase(""))
                {
                    Toast.makeText(this, "You have not entered a module", Toast.LENGTH_SHORT).show();
                    break;
                }

                int expInt = Integer.parseInt(exp);


                if (expInt > 10)
                 {
                     Toast.makeText(this, "It seems you have entered a number above the given range", Toast.LENGTH_SHORT).show();
                     break;
                 } else if (expInt < 1)
                 {
                     Toast.makeText(this, "It seems you have entered a number below the given range", Toast.LENGTH_SHORT).show();
                     break;
                 }


                ModulesAddRequest mar = new ModulesAddRequest(this);
                mar.execute(new String[]{mod, exp});


               SharedPreference sp = new SharedPreference(this);

                HashMap<String, String> user = sp.getUserDetails();

                String usrname = user.get(SharedPreference.username);

                ModulesRequest mq_ = new ModulesRequest(this);
                Bundle bundle = new Bundle();
                try {
                    String modulesResponse = mq_.execute(new String[]{usrname, mod}).get();
                    bundle.putString("moduleresponse", modulesResponse);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Intent takeUserToProfile= new Intent(this, UserProfileActivity.class );
                takeUserToProfile.putExtras(bundle);
                startActivity(takeUserToProfile);
                break;
        }
    }
}
