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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class levelFragment extends Fragment {

    private MainActivity act;

    @BindView(R.id.level_list) ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level, container, false);
        ButterKnife.bind(this, view);

        String[] levelString = getResources().getStringArray(R.array.level);

        /*String[] levelString = {
                "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12"
        };*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, levelString);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        act = (MainActivity) context;
        super.onAttach(context);
    }

    @OnItemClick(R.id.level_list)
    void clickListItem(AdapterView<?> parent, int posision){
        ListView lv = (ListView)parent;
        String select = (String) lv.getItemAtPosition(posision);

        act.clickList(1, select);
    }
}
