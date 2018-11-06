package com.example.grift.flaxgt_individual_project_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelsScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_screen);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.level_1_easy_btn, R.id.level_2_easy_btn, R.id.level_3_easy_btn, R.id.level_1_hard_btn,
              R.id.level_2_hard_btn, R.id.level_3_hard_btn})
    protected void move_to_game_screen(View view){
        int id = ((ImageView)view).getId();
        LevelType levelType;
        switch(id) {
            case R.id.level_1_easy_btn:
                levelType = LevelType.easy_1;
                break;
            case R.id.level_2_easy_btn:
                levelType = LevelType.easy_2;
                break;
            case R.id.level_3_easy_btn:
                levelType = LevelType.easy_3;
                break;
            case R.id.level_1_hard_btn:
                levelType = LevelType.hard_1;
                break;
            case R.id.level_2_hard_btn:
                levelType = LevelType.hard_2;
                break;
            case R.id.level_3_hard_btn:
                levelType = LevelType.hard_3;
                break;
            default:
                levelType = LevelType.DEFAULT;
        }

        if(!levelType.equals(LevelType.DEFAULT))
        {
            Intent intent = new Intent(LevelsScreenActivity.this, GameActivity.class);
            intent.putExtra("levelType", levelType);
            startActivity(intent);
        }
    }
}
