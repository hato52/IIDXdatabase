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


public class clearTypeFragment extends Fragment {

    private MainActivity act;

    @BindView(R.id.clear_type_list) ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clear_type, container, false);
        ButterKnife.bind(this, view);

        String[] clearString = getResources().getStringArray(R.array.clear_type);

        /*String[] clearString = {
                "NO PLAY", "FAILED", "ASSIST CLEAR", "EASY CLEAR", "CLEAR", "HARD CLEAR", "EX HARD CLEAR", "FULLCOMBO CLEAR"
        };*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, clearString);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        act = (MainActivity) context;
        super.onAttach(context);
    }

    @OnItemClick(R.id.clear_type_list)
    void clickListItem(AdapterView<?> parent, int posision){
        ListView lv = (ListView)parent;
        String select = (String) lv.getItemAtPosition(posision);

        act.clickList(2, select);
    }
}
