package com.example.souta.iidxdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class DataAppdateActivity extends AppCompatActivity {

    private Realm realm;
    private int code;
    private int mode;

    @BindView(R.id.back_button) Button jumpButton;
    @BindView(R.id.appdate_button) Button appdateButton;

    @BindView(R.id.spinner_clearType) Spinner clearSpinner;
    @BindView(R.id.spinner_djLevel) Spinner djlevelSpinner;

    @BindView(R.id.change_titleText) TextView title;
    @BindView(R.id.change_diff) TextView difficulty;
    @BindView(R.id.change_diffRank) TextView diffRank;
    @BindView(R.id.change_mode) TextView modetext;

    @OnClick(R.id.back_button)
    void clickJump(){
        finish();
    }

    @OnClick(R.id.appdate_button)
    void clickAppdate(){
        long scoreId = getIntent().getLongExtra("SCORE_ID", -1);
        RealmResults<Score> result = realm.where(Score.class).equalTo("id", scoreId).equalTo("mode", mode).findAll();
        Score score = result.first();

        realm.beginTransaction();
        switch(code){
            case 0:
                score.setClear_n((String) clearSpinner.getSelectedItem());
                score.setDjlevel_n((String) djlevelSpinner.getSelectedItem());
                break;
            case 1:
                score.setClear_h((String) clearSpinner.getSelectedItem());
                score.setDjlevel_h((String) djlevelSpinner.getSelectedItem());
                break;
            case 2:
                score.setClear_a((String) clearSpinner.getSelectedItem());
                score.setDjlevel_a((String) djlevelSpinner.getSelectedItem());
                break;
            default:
                break;
        }
        realm.commitTransaction();

        Toast.makeText(this, "更新しました", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_appdate);

        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);

        code = getIntent().getIntExtra("CODE", -1);
        long scoreId = getIntent().getLongExtra("SCORE_ID", -1);
        mode = getIntent().getIntExtra("MODE", -1);

        RealmResults<Score> result = realm.where(Score.class).equalTo("id", scoreId).equalTo("mode", mode).findAll();
        Score score = result.first();

        String clearType;
        String djLevel;

        title.setText(score.getTitle());

        switch(mode){
            case 0:
                modetext.setText("SP");
                break;

            case 1:
                modetext.setText("DP");
                break;

            default:
                break;
        }

        switch(code){
            case 0:
                difficulty.setText("NORMAL");
                diffRank.setText(String.valueOf(score.getDifficulty_n()));
                clearType = score.getClear_n();
                djLevel = score.getDjlevel_n();
                break;
            case 1:
                difficulty.setText("HYPER");
                diffRank.setText(String.valueOf(score.getDifficulty_h()));
                clearType = score.getClear_h();
                djLevel = score.getDjlevel_h();
                break;
            case 2:
                difficulty.setText("ANOTHER");
                diffRank.setText(String.valueOf(score.getDifficulty_a()));
                clearType = score.getClear_a();
                djLevel = score.getDjlevel_a();
                break;
            default:
                clearType = "NO PLAY";
                djLevel = "---";
                break;
        }

        switch(clearType){
            case "NO PLAY":
               clearSpinner.setSelection(0);
                break;
            case "FAILED":
                clearSpinner.setSelection(1);
                break;
            case "ASSIST CLEAR":
                clearSpinner.setSelection(2);
                break;
            case "EASY CLEAR":
                clearSpinner.setSelection(3);
                break;
            case "CLEAR":
                clearSpinner.setSelection(4);
                break;
            case "HARD CLEAR":
                clearSpinner.setSelection(5);
                break;
            case "EX HARD CLEAR":
                clearSpinner.setSelection(6);
                break;
            case "FULLCOMBO CLEAR":
                clearSpinner.setSelection(7);
                break;
        }

        switch(djLevel){
            case "---":
                djlevelSpinner.setSelection(0);
                break;
            case "F":
                djlevelSpinner.setSelection(1);
                break;
            case "E":
                djlevelSpinner.setSelection(2);
                break;
            case "D":
                djlevelSpinner.setSelection(3);
                break;
            case "C":
                djlevelSpinner.setSelection(4);
                break;
            case "B":
                djlevelSpinner.setSelection(5);
                break;
            case "A":
                djlevelSpinner.setSelection(6);
                break;
            case "AA":
                djlevelSpinner.setSelection(7);
                break;
            case "AAA":
                djlevelSpinner.setSelection(8);
                break;
        }
    }
}
