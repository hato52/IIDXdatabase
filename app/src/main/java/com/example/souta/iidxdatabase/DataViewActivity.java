package com.example.souta.iidxdatabase;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class DataViewActivity extends AppCompatActivity {

    private Realm realm;

    @BindView(R.id.seriesText) TextView seriesText;
    @BindView(R.id.titleText) TextView titleText;
    @BindView(R.id.genreText) TextView genreText;
    @BindView(R.id.artistText) TextView artistText;

    @BindView(R.id.mode) TextView modeText;
    @BindView(R.id.diffRank) TextView diffRank;
    @BindView(R.id.difficulty) TextView difficulty;

    @BindView(R.id.clearTypeText) TextView clearTypeText;
    @BindView(R.id.djLevelText) TextView djLevelText;

    @BindView(R.id.exScoreText) TextView exScoreText;
    @BindView(R.id.pGreatText) TextView pGreatText;
    @BindView(R.id.greatText) TextView greatText;
    @BindView(R.id.missText) TextView missText;

    @BindView(R.id.playNumText) TextView playNumText;
    @BindView(R.id.playDateText) TextView playDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        ButterKnife.bind(this);

        int code = getIntent().getIntExtra("CODE", -1);
        long scoreId = getIntent().getLongExtra("SCORE_ID", -1);
        int mode = getIntent().getIntExtra("MODE", -1);

        if(scoreId == -1){
            Toast.makeText(this, "データが存在しません", Toast.LENGTH_SHORT).show();
        }else{
            realm = Realm.getDefaultInstance();
            RealmResults<Score> result = realm.where(Score.class)
                    .equalTo("id", scoreId)
                    .equalTo("mode", mode)
                    .findAll();

            Score score = result.first();

            seriesText.setText(score.getSeries());
            titleText.setText(score.getTitle());
            genreText.setText(score.getGenre());
            artistText.setText(score.getArtist());

            switch(mode){
                case 0:
                    modeText.setText("SP");
                    break;
                case 1:
                    modeText.setText("DP");
                    break;
                default:
                    break;
            }

            switch(code){
                case 0:
                    diffRank.setText("NORMAL");
                    difficulty.setText(String.valueOf(score.getDifficulty_n()));

                    clearTypeText.setText(score.getClear_n());
                    djLevelText.setText(score.getDjlevel_n());

                    exScoreText.setText(String.valueOf(score.getExscore_n()));
                    pGreatText.setText(String.valueOf(score.getPgreat_n()));
                    greatText.setText(String.valueOf(score.getGreat_n()));
                    missText.setText(String.valueOf(score.getMiss_n()));
                    break;

                case 1:
                    diffRank.setText("HYPER");
                    difficulty.setText(String.valueOf(score.getDifficulty_h()));

                    clearTypeText.setText(score.getClear_h());
                    djLevelText.setText(score.getDjlevel_h());

                    exScoreText.setText(String.valueOf(score.getExscore_h()));
                    pGreatText.setText(String.valueOf(score.getPgreat_h()));
                    greatText.setText(String.valueOf(score.getGreat_h()));
                    missText.setText(String.valueOf(score.getMiss_h()));
                    break;

                case 2:
                    diffRank.setText("ANOTHER");
                    difficulty.setText(String.valueOf(score.getDifficulty_a()));

                    clearTypeText.setText(score.getClear_a());
                    djLevelText.setText(score.getDjlevel_a());

                    exScoreText.setText(String.valueOf(score.getExscore_a()));
                    pGreatText.setText(String.valueOf(score.getPgreat_a()));
                    greatText.setText(String.valueOf(score.getGreat_a()));
                    missText.setText(String.valueOf(score.getMiss_a()));
                    break;

                default:
                    break;
            }

            playNumText.setText(String.valueOf(score.getPlaynum()));
            playDateText.setText(score.getPlaydate());

        }
    }
}
