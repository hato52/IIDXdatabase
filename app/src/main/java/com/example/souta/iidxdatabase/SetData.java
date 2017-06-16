package com.example.souta.iidxdatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.IOException;

public class SetData extends AppCompatActivity {

    @BindView(R.id.data_text) EditText ed;
    @BindView(R.id.set_button) Button bt;
    @BindView(R.id.jump_button) Button bt2;

    private Realm realm;

    @OnClick(R.id.set_button)
    void clickSetButton(){
        realm = Realm.getDefaultInstance();

        RealmResults<Score> results = realm.where(Score.class).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();

        String str = ed.getText().toString();
        Intent intent = new Intent(SetData.this, Setting.class);
        intent.putExtra("STRING", str);
        startActivity(intent);

        realm.close();
        finish();
    }

    @OnClick(R.id.jump_button)
    void clickJumpButton() {
        Intent intent = new Intent(SetData.this, WebViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_data);

        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
}
