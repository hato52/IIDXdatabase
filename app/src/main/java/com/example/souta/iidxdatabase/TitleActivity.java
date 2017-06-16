package com.example.souta.iidxdatabase;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleActivity extends AppCompatActivity {

    @OnClick(R.id.select_sp)
    void click_sp(){
        Intent intent = new Intent(TitleActivity.this, MainActivity.class);
        intent.putExtra("MODE", 0);
        startActivity(intent);
    }

    @OnClick(R.id.select_dp)
    void click_dp(){
        Intent intent = new Intent(TitleActivity.this, MainActivity.class);
        intent.putExtra("MODE", 1);
        startActivity(intent);
    }

    @OnClick(R.id.select_setdata)
    void click_setdata(){
        Intent intent = new Intent(TitleActivity.this, WebViewActivity.class);
        intent.putExtra("MODE", 0);
        startActivity(intent);
    }

    @OnClick(R.id.select_setdata2)
    void click_setdata2(){
        Intent intent = new Intent(TitleActivity.this, WebViewActivity.class);
        intent.putExtra("MODE", 1);
        startActivity(intent);
    }

    @OnClick(R.id.select_license)
    void click_license(){
        //WebView webview = (WebView) findViewById(R.id.webView);
        WebView webview = new WebView(this);
        webview.loadUrl("file:///android_asset/license.html");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ライセンス");
        builder.setView(webview);
        //builder.setMessage("hoge");
        builder.setNeutralButton("閉じる", null);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ButterKnife.bind(this);
    }
}
