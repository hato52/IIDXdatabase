package com.example.souta.iidxdatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmResults;


public class seriesFragment extends Fragment {

    private MainActivity act;

    @BindView(R.id.series_list) ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, view);

        String[] seriesString = getResources().getStringArray(R.array.series);

       /* String[] seriesString = {
                "1st&substream", "2nd style", "3rd style", "4th style", "5th style", "6th style",
                "7th style", "8th style", "9th style", "10th style", "IIDX RED", "HAPPY SKY",
                "DistorteD", "GOLD", "DJ TROOPERS", "EMPRESS", "SIRIUS", "Resort Anthem",
                "Lincle", "tricoro", "SPADA", "PENDUAL", "copula", "SINOBUZ"
        };*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, seriesString);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        act = (MainActivity) context;
        super.onAttach(context);
    }

    @OnItemClick(R.id.series_list)
    void clickListItem(AdapterView<?> parent, int posision){
        ListView lv = (ListView)parent;
        String select = (String) lv.getItemAtPosition(posision);

        act.clickList(0, select);
    }
}
