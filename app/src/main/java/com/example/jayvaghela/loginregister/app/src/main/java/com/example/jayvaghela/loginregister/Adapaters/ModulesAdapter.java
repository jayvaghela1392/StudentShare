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

public class ModulesAdapter extends BaseAdapter{

    List<Modules> modules;
    Context context;

   public ModulesAdapter(Context context, List<Modules> modules){

       this.modules = modules;
       this.context = context;
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
            view = inflator.inflate(R.layout.modules_item, null);
        }

        TextView module = (TextView) view.findViewById(R.id.etModule);
        TextView rating = (TextView) view.findViewById(R.id.etRating);
        TextView endorsements = (TextView) view.findViewById(R.id.etEndorsments);

        module.setText(modules.get(position).getModule());
        rating.setText(Integer.toString(modules.get(position).getRating()));
        endorsements.setText(Integer.toString(modules.get(position).getEndorsements()));

        return view;
    }
}

