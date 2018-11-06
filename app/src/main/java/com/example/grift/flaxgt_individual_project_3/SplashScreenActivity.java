package com.example.grift.flaxgt_individual_project_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_layout)
    public void move_to_intro_screen(View view) {
        startActivity(new Intent(SplashScreenActivity.this, IntroScreenActivity.class));
    }
}
