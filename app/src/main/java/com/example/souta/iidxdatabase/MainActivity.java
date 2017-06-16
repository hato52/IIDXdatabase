package com.example.souta.iidxdatabase;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IntegerRes;
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
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
        return true;
    }*/

    void clickList(int code, String select){

        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        if(getIntent().getIntExtra("MODE", -1) == 0){
            intent.putExtra("MODE", 0);
        }else{
            intent.putExtra("MODE", 1);
        }

        switch (code){
            case 0:
                intent.putExtra("CODE", 0);
                intent.putExtra("SELECT_SERIES", select);
                break;
            case 1:
                intent.putExtra("CODE", 1);
                intent.putExtra("SELECT_LEVEL", select);
                break;
            case 2:
                intent.putExtra("CODE", 2);
                intent.putExtra("SELECT_CLEARTYPE", select);
                break;
            default:
                Toast.makeText(this, "なにかがおかしいよ", Toast.LENGTH_SHORT).show();
                break;
        }

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0); //Action Barとタブの間の影を消す

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("MODE", getIntent().getIntExtra("MODE", -1));
                startActivity(intent);
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = new seriesFragment();
                    break;
                case 1:
                    fragment = new levelFragment();
                    break;
                case 2:
                    fragment = new clearTypeFragment();
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
                    return "SERIES";
                case 1:
                    return "LEVEL";
                case 2:
                    return "CLEAR TYPE";
            }
            return null;
        }
    }
}
