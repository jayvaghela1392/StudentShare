package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;

import java.util.List;

/**
 * Created by samsonaiyegbusi on 14/08/16.
 */
public class ResultsProfileAdapter extends BaseAdapter {

    Context context;

    List<Modules> modules;

    public ResultsProfileAdapter(Context context, List<Modules> modules){

        this.context = context;
        this.modules = modules;
    }



    @Override
    public int getCount() {
        return modules.size();
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
            view = inflator.inflate(R.layout.results_item, null);
        }

        TextView tvusername = (TextView) view.findViewById(R.id.etUsername);
        TextView tvEndorsement = (TextView) view.findViewById(R.id.tvEndorsements);

        String username = modules.get(position).getUsername();
        String endorsement = Integer.toString(modules.get(position).getEndorsements());

        tvusername.setText(username);
        tvEndorsement.setText(endorsement);

        return view;
    }
}
