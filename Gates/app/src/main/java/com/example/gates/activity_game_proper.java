package com.example.gates;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class activity_game_proper extends AppCompatActivity {
    TextView title, round;
    LinearLayout columnOne;
    ConstraintLayout popUp;
    Button[] btnGrp = new Button[7];
    int game, difficulty;
    String[] games = new String[] {"Lesson", "Game One", "Game Two", "Game Three"};
    String[] difficulties = new String[] {"novice", "intermediate", "advance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);
        round = findViewById(R.id.round);
        popUp = findViewById(R.id.pop_up);

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

        gridInit();

        popUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
            }
        });
    }

    public void gridInit() {
        columnOne = findViewById(R.id.column_one);
        for(int i = 0; i < 7; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,
                            1.0f));
            btn.setHeight(0);
            btn.setText(String.valueOf(i));
            final int j = i;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    round.setText(String.valueOf(j));
                }
            });
            columnOne.addView(btn);
        }
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