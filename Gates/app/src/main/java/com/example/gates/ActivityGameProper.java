package com.example.gates;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ActivityGameProper extends AppCompatActivity {
    TextView title, round;
    LinearLayout gameGrid;
    LinearLayout[] columns = new LinearLayout[7];
    ConstraintLayout popUp;
    Button[] btnGrp = new Button[7];
    int game,
            difficulty = 0;
    int[] coord = new int[2];

    String[] games = new String[] {"Lesson", "Game One", "Game Two", "Game Three"};
    String[] difficulties = new String[] {"novice", "intermediate", "advance"};

    Drawable[] bgArray = new Drawable[2],
            stateArray = new Drawable[2];
    int[][] control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);
        round = findViewById(R.id.round);
        //popUp = findViewById(R.id.pop_up);
        gameGrid = findViewById(R.id.game_grid);
        GameBank bank = new GameBank();
        control = bank.getProblem(difficulty, 0);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .14f));
        round.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .08f));

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            game = extras.getInt("game");
            String str = games[game];
            title.setText(str);
        }

        bgInit();
        statesInit();
        gridInit();
        layoutInit(control[2][0], control[1]);

        gameGrid.setBackground(bgArray[control[0][0]]);


        /*popUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
            }
        });*/
    }

    public void layoutInit(int defState, int[] active) {
        int counter = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                        1.0f));
                int temp = counter;
                btn.setOnClickListener(new View.OnClickListener() {
                    int crntState = defState;
                    public void onClick(View v) {
                        if(crntState == 0) {crntState = 1;}
                        else {crntState = 0;}
                        setButton(btn, temp, crntState);
                    }
                });
                btn.setBackground(checkVisibility(counter, active, defState));
                btn.setEnabled(checkEligibility(counter, active));
                columns[i].addView(btn);
                counter++;
            }
        }
    }


    public void setButton(Button btn, int coord, int state) {
        btn.setBackground(stateArray[state]);
        round.setText(String.valueOf(coord));
    }

    public Drawable checkVisibility(int key, int[] active, int stateIndex) {
        for(int n: active) {
            if(n == key) {
                return stateArray[stateIndex];
            }
        }
        return getDrawable(R.drawable.blank);
    }

    public boolean checkEligibility(int key, int[] active) {
        for(int n: active) {
            if(n == key) {
                return true;
            }
        }
        return false;
    }



    public void bgInit() {
        bgArray[0] = getDrawable(R.drawable.play_bg_01);
        bgArray[1] = getDrawable(R.drawable.play_bg_01);
    }

    public void statesInit() {
        stateArray[0] = getDrawable(R.drawable.and);
        stateArray[1] = getDrawable(R.drawable.xnor);
    }

    public void gridInit() {
        columns[0] = findViewById(R.id.column_one);
        columns[1] = findViewById(R.id.column_two);
        columns[2] = findViewById(R.id.column_three);
        columns[3] = findViewById(R.id.column_four);
        columns[4] = findViewById(R.id.column_five);
        columns[5] = findViewById(R.id.column_six);
        columns[6] = findViewById(R.id.column_seven);
    }





    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}