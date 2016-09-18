/*
package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class UserSearchRequest extends StringRequest {

    private static final String SEARCH_REQUEST_URL = "http://studentshareapp.comxa.com/Search.php";
    private Map<String, String> params;

    public UserSearchRequest(String university, String module, Response.Listener<String> listener) {
        super(Method.GET, SEARCH_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("university", university);
        params.put("module", module);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

*/
