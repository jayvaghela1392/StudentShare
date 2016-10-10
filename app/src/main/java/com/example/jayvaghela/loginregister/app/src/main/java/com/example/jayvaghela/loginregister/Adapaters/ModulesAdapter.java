package com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Adapaters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jayvaghela.loginregister.R;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Objects.Modules;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.CheckEndoRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.PostEndoRequest;
import com.example.jayvaghela.loginregister.app.src.main.java.com.example.jayvaghela.loginregister.Requests.updateEndoRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModulesAdapter extends BaseAdapter implements View.OnClickListener{

    List<Modules> modules;
    Context context;
    String modUser;
    View v;
    TextView module;
    TextView endorsements;
    ImageButton endo;
    String connection;

   public ModulesAdapter(Context context, List<Modules> modules, String connection){

       this.modules = modules;
       this.context = context;
       modUser = modules.get(0).getUsername();
       this.connection = connection;
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
        notifyDataSetChanged();
        if (view == null){
            LayoutInflater inflator = LayoutInflater.from(context);
            view = inflator.inflate(R.layout.modules_item, null);
            this.v = view;
        }


         module = (TextView) view.findViewById(R.id.etModule_);
        TextView rating = (TextView) view.findViewById(R.id.etRating);
         endorsements = (TextView) v.findViewById(R.id.etEndorsments);

        String modUser = modules.get(position).getUsername();
        String modules_ = modules.get(position).getModule();

        endo = (ImageButton) view.findViewById(R.id.btnEndorse);
        endo.setVisibility(View.INVISIBLE);

        if (connection != null) {
            if (connection.contains(modUser)) {
                endo.setVisibility(View.VISIBLE);
                endo.setOnClickListener(this);
                CheckEndoRequest er = new CheckEndoRequest(context, endo);
                er.execute(new String[]{modUser, modules_});
            }
        }


        module.setText(modules.get(position).getModule());
        rating.setText(Integer.toString(modules.get(position).getRating()));
        endorsements.setText(Integer.toString(modules.get(position).getEndorsements()));

        return view;
    }

    @Override
    public void onClick(View view) {


        String mod = module.getText().toString();

        PostEndoRequest per = new PostEndoRequest(context);
        per.execute(new String[]{modUser, mod});

        updateEndoRequest uer = new updateEndoRequest(context);
        try {
            String update = uer.execute(new String[]{modUser, mod}).get();
            endorsements.setText(update);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        endo.setVisibility(v.INVISIBLE);

    }
}

