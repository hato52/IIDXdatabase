package com.example.souta.iidxdatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.realm.Realm;
import io.realm.RealmResults;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final WebView webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("https://p.eagate.573.jp/gate/p/login.html");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                Log.v("URL", url);
                if(!url.equals("https://p.eagate.573.jp/gate/p/login.html")) {
                    String[] cookie = CookieManager.getInstance().getCookie(url).split("=");
                    for(String cookie_value : cookie){
                        Log.v("cookie value is", cookie_value);
                    }

                    Intent intent = new Intent(WebViewActivity.this, Setting.class);
                    intent.putExtra("COOKIE", cookie);
                    intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));
                    startActivity(intent);
                    finish();
                    //getCsvData(cookie);
                }
            }
        });

    }
}
