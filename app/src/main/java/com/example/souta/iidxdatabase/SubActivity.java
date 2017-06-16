package com.example.souta.iidxdatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class SubActivity extends AppCompatActivity {

    //private Realm realm;

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(SubActivity.this, WebViewActivity.class);
        startActivity(intent);
        startActivity(intent);
        return true;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        getSupportActionBar().setElevation(0); //Action Barとタブの間の影を消す

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, SearchActivity.class);
                intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));
                startActivity(intent);
            }
        });*/

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_sub);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager_sub);
        viewPager.setAdapter(new SubActivity.ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;
            //Bundle bundle_code = new Bundle();
            Bundle bundle = new Bundle();

            int code = getIntent().getIntExtra("CODE", -1);     //どのタブから選択されたかを判別
            bundle.putInt("CODE", code);

            int mode = getIntent().getIntExtra("MODE", -1);
            bundle.putInt("MODE", mode);

            switch(code){       //fragmentに送る用の値をセット
                case 0:
                    bundle.putString("SELECT_SERIES", getIntent().getStringExtra("SELECT_SERIES"));
                    break;
                case 1:
                    bundle.putString("SELECT_LEVEL", getIntent().getStringExtra("SELECT_LEVEL"));
                    break;
                case 2:
                    bundle.putString("SELECT_CLEARTYPE", getIntent().getStringExtra("SELECT_CLEARTYPE"));
                    break;
                case 3:
                    bundle.putStringArray("SELECT_TERMS", getIntent().getStringArrayExtra("SELECT_TERMS"));
                default:
                    break;
            }

            switch (position){
                case 0:
                    fragment = new normalFragment();
                    //fragment.setArguments(bundle_code);
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    fragment = new hyperFragment();
                    //fragment.setArguments(bundle_code);
                    fragment.setArguments(bundle);
                    break;
                case 2:
                    fragment = new anotherFragment();
                    //fragment.setArguments(bundle_code);
                    fragment.setArguments(bundle);
                    break;
                default:
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount(){
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "NORMAL";
                case 1:
                    return "HYPER";
                case 2:
                    return "ANOTHER";
            }
            return null;
        }
    }

    void clickList(AdapterView<?> parent, int posision, int code){      //codeを用いてどのfragmentから呼び出されたか判別
        final Score score = (Score)parent.getItemAtPosition(posision);
        final int call = code;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(String.valueOf(score.getTitle()));

        switch (call){
            case 0:
                builder.setMessage("NORMAL: " + score.getDifficulty_n() + "\n\n" + score.getClear_n() + "\n" + score.getDjlevel_n());
                break;
            case 1:
                builder.setMessage("HYPER: " + score.getDifficulty_h() + "\n\n" + score.getClear_h() + "\n" + score.getDjlevel_h());
                break;
            case 2:
                builder.setMessage("ANOTHER: " + score.getDifficulty_a() + "\n\n" + score.getClear_a() + "\n" + score.getDjlevel_a());
                break;
        }

        builder.setPositiveButton("更新", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(SubActivity.this, DataAppdateActivity.class);
                intent.putExtra("CODE", call);
                intent.putExtra("SCORE_ID", score.getId());
                intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));
                startActivity(intent);
            }
        });

        builder.setNegativeButton("詳細", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(SubActivity.this, DataViewActivity.class);
                intent.putExtra("CODE", call);
                intent.putExtra("SCORE_ID", score.getId());
                intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));
                startActivity(intent);
            }
        });

        builder.setNeutralButton("閉じる", null);
        builder.show();
    }
}
