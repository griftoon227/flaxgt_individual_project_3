package com.example.grift.flaxgt_individual_project_3;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {
    @BindView(R.id.game_image) ImageView gameMap;
    @BindView(R.id.character) ImageView character;
    @BindView(R.id.Btn1) Button btn1;
    @BindView(R.id.Btn2) Button btn2;
    @BindView(R.id.Btn3) Button btn3;
    @BindView(R.id.Btn4) Button btn4;

    LevelType levelType = LevelType.DEFAULT;

    MyStartDraggingListener myStartDraggingListener;
    MyEndDragListener myEndDragListener;

    int[][] winningGamePaths;
    double[][] amountTranslating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extrasBundle = intent.getExtras();
        if(!(extrasBundle == null || extrasBundle.isEmpty()))
            levelType = (LevelType)extrasBundle.getSerializable("levelType");

        if(levelType != null) {
            if (levelType.equals(LevelType.easy_1))
                gameMap.setImageResource(R.drawable.map_easy_1);
            else if (levelType.equals(LevelType.easy_2))
                gameMap.setImageResource(R.drawable.map_easy_2);
            else if (levelType.equals(LevelType.easy_3))
                gameMap.setImageResource(R.drawable.map_easy_3);
            else if (levelType.equals(LevelType.hard_1))
                gameMap.setImageResource(R.drawable.map_hard_1);
            else if (levelType.equals(LevelType.hard_2))
                gameMap.setImageResource(R.drawable.map_hard_2);
            else if (levelType.equals(LevelType.hard_3))
                gameMap.setImageResource(R.drawable.map_hard_3);
        }

        //determine if the level is of type easy or hard, and have the appropriate # of boxes to accommodate
        if(levelType.toString().contains("easy"))
            btn4.setVisibility(View.INVISIBLE);


        //instantiate our listeners
        myStartDraggingListener = new MyStartDraggingListener();
        myEndDragListener = new MyEndDragListener();

        //set the listeners
        findViewById(R.id.upBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.downBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.leftBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.rightBtn).setOnLongClickListener(myStartDraggingListener);

        btn1.setOnDragListener(myEndDragListener);
        btn2.setOnDragListener(myEndDragListener);
        btn3.setOnDragListener(myEndDragListener);
        btn4.setOnDragListener(myEndDragListener);

        //instantiate the winning paths array
        winningGamePaths = new int[][]{
                {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
                {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
                {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp},
                {R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_left_black_24dp, R.drawable.ic_arrow_upward_black_24dp},
                {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_upward_black_24dp, R.drawable.ic_arrow_right_black_24dp},
                {R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp, R.drawable.ic_arrow_downward_black_24dp, R.drawable.ic_arrow_right_black_24dp}
        };

        //instantiate the amount translating array
        amountTranslating = new double[][]{
                {.5, 1, .5},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1, 1},
                {.5, .5, .5, .5},
                {.25, .5, .25, .5}
        };
    }

    @OnClick(R.id.start_button)
    protected void startGame(View view){
        boolean isCompleted = false;
        int levelId = levelType.ordinal();

        //instantiate translate animation class
        Translate translate = new Translate();

        //check if the user properly completed the level depending on its difficulty
        if(levelType.toString().contains("easy"))
        {
                if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][0]).getConstantState()) &&
                        (btn2.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][1]).getConstantState()) &&
                        (btn3.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][2]).getConstantState())){
                    isCompleted = true;
                }
        }
        else
        {
            if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][0]).getConstantState()) &&
                    (btn2.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][1]).getConstantState()) &&
                    (btn3.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][2]).getConstantState()) &&
                    (btn4.getBackground().getConstantState() == getDrawable(winningGamePaths[levelType.ordinal()][3]).getConstantState())){
                isCompleted = true;
            }
        }

        //show the translation animation for the character
        if(isCompleted) {
            for (int i = 0; i < winningGamePaths[levelType.ordinal()].length; i++) {
                switch (winningGamePaths[levelType.ordinal()][i]) {
                    case R.drawable.ic_arrow_left_black_24dp:
                    case R.drawable.ic_arrow_right_black_24dp:
                        translate.translateRightOrLeft(amountTranslating[levelType.ordinal()][i]);
                        break;
                    case R.drawable.ic_arrow_upward_black_24dp:
                    case R.drawable.ic_arrow_downward_black_24dp:
                        translate.translateUpOrDown(amountTranslating[levelType.ordinal()][i]);
                        break;
                }
            }
            translate.beginAnimation();

            SharedPreferences sharedPreferences = getSharedPreferences("log_file", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            switch(levelType)
            {
                case easy_1:
                    editor.putBoolean("Level_1_Easy", true);
                    break;
                case easy_2:
                    editor.putBoolean("Level_2_Easy", true);
                    break;
                case easy_3:
                    editor.putBoolean("Level_3_Easy", true);
                    break;
                case hard_1:
                    editor.putBoolean("Level_1_Hard", true);
                    break;
                case hard_2:
                    editor.putBoolean("Level_2_Hard", true);
                    break;
                case hard_3:
                    editor.putBoolean("Level_3_Hard", true);
                    break;
            }

            editor.commit();
        }
    }

    @OnClick(R.id.go_back_button)
    protected void goToPreviousPage(View view){
        startActivity(new Intent(GameActivity.this, LevelsScreenActivity.class));
    }

    private class MyStartDraggingListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            WithDragShadow shadow = new WithDragShadow(view);
            view.startDrag(null,shadow,view,0);
            return false;
        }
    }

    private class MyEndDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            if(dragEvent.getAction()==DragEvent.ACTION_DROP)
                view.setBackground(((Button)dragEvent.getLocalState()).getBackground());
            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder{
        public WithDragShadow(View v){
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas){
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }

    private class Translate{
        AnimationSet animationSet = new AnimationSet(true);
        private int startOffset = 0;

        public void translateRightOrLeft(double howMuchToTranslate){
            float toX = (getMapWidth()*(float)howMuchToTranslate);
            TranslateAnimation animation = new TranslateAnimation(getCharacterCoords()[0], toX, 0, 0);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            animation.setStartOffset(startOffset);
            animationSet.addAnimation(animation);
            startOffset += ((int)animation.getDuration() + 300);
        }

        public void translateUpOrDown(double howMuchToTranslate){
            float toY = (getMapHeight()*(float)howMuchToTranslate);
            TranslateAnimation animation = new TranslateAnimation(0, 0, getCharacterCoords()[1], toY);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            animation.setStartOffset(startOffset);
            animationSet.addAnimation(animation);
            startOffset += ((int)animation.getDuration() + 300);
        }

        public void beginAnimation(){
            character.startAnimation(animationSet);
        }

        private int[] getCharacterCoords(){
            int[] coords = new int[2];
            character.getLocationOnScreen(coords);
            return coords;
        }

        private int getMapWidth(){
            return gameMap.getWidth();
        }

        private int getMapHeight(){
            return gameMap.getHeight();
        }
    }
}
