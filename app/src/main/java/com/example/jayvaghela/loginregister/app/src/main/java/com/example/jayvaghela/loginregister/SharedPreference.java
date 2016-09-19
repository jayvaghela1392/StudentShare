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
    public static final String requests = "requests";
    public static final String connection = "connection";
    public static final String module = "module";


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
        user.put(requests, pref.getString(requests, null));
        user.put(module, pref.getString(module, null));
        user.put(connection, pref.getString(connection, null));

        return user;

    }

    public void request(String requestedusr){

        editor.putString(requests, requestedusr);

        editor.commit();
    }

    public void connection(String connections){

        editor.putString(connection, connections);

        editor.commit();
    }

    public void modules(String modules){

        editor.putString(module, modules);

        editor.commit();
    }

    public void saveUsername(String name){
        editor.putString(username, name);

        editor.commit();
    }
}