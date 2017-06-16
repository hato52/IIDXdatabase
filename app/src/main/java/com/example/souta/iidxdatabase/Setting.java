package com.example.souta.iidxdatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class Setting extends AppCompatActivity {

    public static final int DATA_NUM = 27;
    private Realm realm;

    @BindView(R.id.setting_textview) TextView tv;

    private void EntryData(final String str, final String mode){
        new AsyncTask<Void, Void, Boolean>(){
            @Override
            protected Boolean doInBackground(Void... params){

                //str = getIntent().getStringExtra("STRING");
                String[] music_data = str.split("\r");

                //int count = 0;
                String[] data = new String[DATA_NUM];
                realm = Realm.getDefaultInstance();

                try{
                    Number maxId;
                    long nextId = 1;
                    Score score;

                    for(int i=1; i<music_data.length; i++){
                        maxId = realm.where(Score.class).max("id");
                        if(maxId != null){
                            nextId = maxId.longValue()+1;
                        }
                        data = music_data[i].split(",");

                        realm.beginTransaction();
                        score = realm.createObject(Score.class);

                        score.setId(nextId);
                        score.setSeries(data[0]);
                        score.setTitle(data[1]);
                        score.setGenre(data[2]);
                        score.setArtist(data[3]);
                        score.setPlaynum(Integer.parseInt(data[4]));

                        score.setDifficulty_n(Integer.parseInt(data[5]));
                        score.setExscore_n(Integer.parseInt(data[6]));
                        score.setPgreat_n(Integer.parseInt(data[7]));
                        score.setGreat_n(Integer.parseInt(data[8]));
                        score.setMiss_n(data[9]);
                        score.setClear_n(data[10]);
                        score.setDjlevel_n(data[11]);

                        score.setDifficulty_h(Integer.parseInt(data[12]));
                        score.setExscore_h(Integer.parseInt(data[13]));
                        score.setPgreat_h(Integer.parseInt(data[14]));
                        score.setGreat_h(Integer.parseInt(data[15]));
                        score.setMiss_h(data[16]);
                        score.setClear_h(data[17]);
                        score.setDjlevel_h(data[18]);

                        score.setDifficulty_a(Integer.parseInt(data[19]));
                        score.setExscore_a(Integer.parseInt(data[20]));
                        score.setPgreat_a(Integer.parseInt(data[21]));
                        score.setGreat_a(Integer.parseInt(data[22]));
                        score.setMiss_a(data[23]);
                        score.setClear_a(data[24]);
                        score.setDjlevel_a(data[25]);

                        score.setPlaydate(data[26]);

                        if(mode.equals("0")){
                            score.setMode(0);
                        }else{
                            score.setMode(1);
                        }

                        realm.commitTransaction();

                        //count++;
                    }
                }
                catch(Exception e){
                    realm.cancelTransaction();
                    realm.close();
                    e.printStackTrace();
                }

                return true;
            }

            @Override
            protected void onPostExecute(Boolean result){
                Toast.makeText(Setting.this, "データの登録が完了しました", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();
    }

    private void getCsvData(final String[] cookie, final String mode){
        if(mode.equals("0")){
            tv.setText("SPデータを取得中です...");
        }else if(mode.equals("1")){
            tv.setText("DPデータを取得中です...");
        }
        new AsyncTask<Void, Void, Document>(){
            @Override
            protected Document doInBackground(Void... params) {
                Document doc = new Document("");
                try{
                    doc = Jsoup.connect("http://p.eagate.573.jp/game/2dx/24/p/djdata/score_download.html")
                            .data("style", mode)
                            .cookie(cookie[0], cookie[1])
                            .post();
                    //System.out.println(doc.html());
                }
                catch(Exception e){
                    Toast.makeText(Setting.this, "アクセスに失敗しました", Toast.LENGTH_SHORT).show();
                    finish();
                    e.printStackTrace();
                }
                return doc;
            }

            @Override
            protected void onPostExecute(Document document){
                try {
                    Element csv_text = document.getElementById("score_data");
                    String str = csv_text.text().toString();

                    realm = Realm.getDefaultInstance();

                    RealmResults<Score> results = realm.where(Score.class).findAll();
                    realm.beginTransaction();
                    results.deleteAllFromRealm();
                    realm.commitTransaction();

                    realm.close();

                    tv.setText("データベースに登録中です...");
                    EntryData(str, mode);
                }
                catch (Exception e){
                    Toast.makeText(Setting.this, "データの取得に失敗しました", Toast.LENGTH_SHORT).show();
                    finish();
                    e.printStackTrace();
                }
            }

        }.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        String[] cookie = getIntent().getStringArrayExtra("COOKIE");

        getCsvData(cookie, String.valueOf(getIntent().getIntExtra("MODE", -1)));

    }
}
