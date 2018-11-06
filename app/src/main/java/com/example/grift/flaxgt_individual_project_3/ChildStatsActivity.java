package com.example.grift.flaxgt_individual_project_3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChildStatsActivity extends AppCompatActivity {
    @BindView(R.id.child_stats_title) TextView title;
    @BindView(R.id.EZ_LVL_1_complete) TextView ez_lvl_1_complete;
    @BindView(R.id.EZ_LVL_2_complete) TextView ez_lvl_2_complete;
    @BindView(R.id.EZ_LVL_3_complete) TextView ez_lvl_3_complete;
    @BindView(R.id.HRD_LVL_1_complete) TextView hrd_lvl_1_complete;
    @BindView(R.id.HRD_LVL_2_complete) TextView hrd_lvl_2_complete;
    @BindView(R.id.HRD_LVL_3_complete) TextView hrd_lvl_3_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_stats);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("log_file", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("Level_1_Easy", false))
            ez_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("Level_2_Easy", false))
            ez_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("Level_3_Easy", false))
            ez_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("Level_1_Hard", false))
            hrd_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("Level_2_Hard", false))
            hrd_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("Level_3_Hard", false))
            hrd_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));
    }
}
