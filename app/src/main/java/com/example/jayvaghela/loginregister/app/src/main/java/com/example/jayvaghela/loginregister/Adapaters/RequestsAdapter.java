package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;

import java.util.List;
/**
 * Created by samsonaiyegbusi on 14/08/16.
 */
public class RequestsAdapter extends BaseAdapter {

    Context context;
    List<String> students;
    public RequestsAdapter(Context context, List<String> students){
        this.context = context;
        this.students = students;
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null){
            LayoutInflater inflator = LayoutInflater.from(context);
            view = inflator.inflate(R.layout.requests_item, null);
        }

        TextView tvusername = (TextView) view.findViewById(R.id.etUsername);
        String username = students.get(position);
        tvusername.setText(username);
        return view;
    }
}
