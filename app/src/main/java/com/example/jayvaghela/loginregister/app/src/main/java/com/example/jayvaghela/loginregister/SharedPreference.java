package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by jayvaghela on 17/09/2016.
 */
public class SharedPreference {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context context;

    public static final String username = "username";
    public static final String response = "response";


    private HashMap<String, String> user = new HashMap<String, String>();

    int mode = 0;


    public SharedPreference(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("pref", mode);
        editor = pref.edit();

    }


    public void response(String xmlresponse){

        editor.putString(response, xmlresponse);

        editor.commit();
    }
    public HashMap<String, String> getUserDetails() {
        user.put(username, pref.getString(username, null));
        user.put(response, pref.getString(response, null));

        return user;

    }

    public void saveUsername(String name){
        editor.putString(username, name);

        editor.commit();
    }
}