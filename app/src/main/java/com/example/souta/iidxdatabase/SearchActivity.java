package com.example.souta.iidxdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    protected boolean flag[] = {true, true, true, true};

    @BindView(R.id.search_spinner1) Spinner spinner1;
    @BindView(R.id.search_sppiner2) Spinner spinner2;
    @BindView(R.id.search_spinner3) Spinner spinner3;
    @BindView(R.id.search_spinner4) Spinner spinner4;

    @BindView(R.id.checkBox) CheckBox cb1;
    @BindView(R.id.checkBox2) CheckBox cb2;
    @BindView(R.id.checkBox3) CheckBox cb3;
    @BindView(R.id.checkBox4) CheckBox cb4;

    @OnClick(R.id.search_button)
    void clickButton(){
        Intent intent = new Intent(SearchActivity.this, SubActivity.class);
        intent.putExtra("CODE", 3);
        intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));

        String[] terms = new String[4];
        if(flag[0] == true) terms[0] = (String) spinner1.getSelectedItem();
        if(flag[1] == true) terms[1] = (String) spinner2.getSelectedItem();
        if(flag[2] == true) terms[2] = (String) spinner3.getSelectedItem();
        if(flag[3] == true) terms[3] = (String) spinner4.getSelectedItem();
        intent.putExtra("SELECT_TERMS", terms);

        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    spinner1.setEnabled(true);
                    flag[0] = true;
                }else{
                    spinner1.setEnabled(false);
                    flag[0] = false;
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    spinner2.setEnabled(true);
                    flag[1] = true;
                }else{
                    spinner2.setEnabled(false);
                    flag[1] = false;
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    spinner3.setEnabled(true);
                    flag[2] = true;
                }else{
                    spinner3.setEnabled(false);
                    flag[2] = false;
                }
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    spinner4.setEnabled(true);
                    flag[3] = true;
                }else{
                    spinner4.setEnabled(false);
                    flag[3] = false;
                }
            }
        });
    }
}
