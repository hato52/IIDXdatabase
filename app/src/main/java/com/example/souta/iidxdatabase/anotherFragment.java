package com.example.souta.iidxdatabase;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class anotherFragment extends Fragment {

    private Realm realm;
    private SubActivity act;

    @BindView(R.id.another_list) ListView lv;

    @OnItemClick(R.id.another_list)
    void clickListItem(AdapterView<?> parent, int posision){
        act.clickList(parent, posision, 2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_another, container, false);

        realm = Realm.getDefaultInstance();

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        RealmResults<Score> result;
        RealmResults<Score> sortedResult;
        ScoreAdapter_a adapter;

        RealmQuery<Score> query = realm.where(Score.class);
        query.equalTo("mode", bundle.getInt("MODE"));

        int code = bundle.getInt("CODE");
        switch(code){
            case 0:
                String series = bundle.getString("SELECT_SERIES");
                result = query.equalTo("series", series).notEqualTo("difficulty_a", 0).findAll();
                sortedResult = result.sort("difficulty_a");
                break;

            case 1:
                int level = Integer.parseInt(bundle.getString("SELECT_LEVEL"));
                result = query.equalTo("difficulty_a", level).notEqualTo("difficulty_a", 0).findAll();
                sortedResult = result.sort("title");
                break;

            case 2:
                String clear = bundle.getString("SELECT_CLEARTYPE");
                result = query.equalTo("clear_a", clear).notEqualTo("difficulty_a", 0).findAll();
                sortedResult = result.sort("difficulty_a");
                break;

            case 3:
                String[] terms = bundle.getStringArray("SELECT_TERMS");
                if(terms[0] != null) query.equalTo("series", terms[0]);
                if(terms[1] != null) query.equalTo("difficulty_a", Integer.parseInt(terms[1]));
                if(terms[2] != null) query.equalTo("clear_a", terms[2]);
                if(terms[3] != null) query.equalTo("djlevel_a", terms[3]);
                query.notEqualTo("difficulty_a", 0);
                result = query.findAll();
                sortedResult = result.sort("difficulty_a");
                break;

            default:
                result = realm.where(Score.class).findAll();
                sortedResult = result.sort("difficulty_a");
                break;
        }

        adapter = new ScoreAdapter_a(this.getContext(), sortedResult);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        act = (SubActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroyView(){
        realm.close();
        super.onDestroyView();
    }
}
