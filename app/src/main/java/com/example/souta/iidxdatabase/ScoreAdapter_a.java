package com.example.souta.iidxdatabase;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by Souta on 2017/02/02.
 */

public class ScoreAdapter_a extends RealmBaseAdapter<Score> implements ListAdapter {

    private static class ViewHolder{
        ImageView clearRamp;
        TextView diffText;
        TextView titleText;
    }

    public ScoreAdapter_a(Context context, OrderedRealmCollection<Score> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertview == null){
            convertview = inflater.inflate(R.layout.list_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.clearRamp = (ImageView) convertview.findViewById(R.id.clearRamp);
            viewHolder.diffText = (TextView) convertview.findViewById(R.id.list_difficulty);
            viewHolder.titleText= (TextView) convertview.findViewById(R.id.list_title);
            convertview.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertview.getTag();
        }

        Score score = adapterData.get(position);
        String str = score.getClear_a();

        switch (str){
            case "NO PLAY":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_noplay);
                break;

            case "FAILED":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_failed);
                break;

            case "ASSIST CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_assist);
                break;

            case "EASY CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_easy);
                break;

            case "CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_normal);
                break;

            case "HARD CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_hard);
                break;

            case "EX HARD CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_exhard);
                break;

            case "FULLCOMBO CLEAR":
                viewHolder.clearRamp.setImageResource(R.drawable.ramp_fullcombo);
                break;
        }

        viewHolder.diffText.setTextColor(Color.parseColor("#F2575A"));
        viewHolder.diffText.setText(String.valueOf(score.getDifficulty_a()));
        viewHolder.titleText.setText(score.getTitle());

        return convertview;
    }
}
