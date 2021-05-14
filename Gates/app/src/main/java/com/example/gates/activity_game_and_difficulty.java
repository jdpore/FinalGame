package com.example.gates;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class activity_game_and_difficulty extends AppCompatActivity {
    Button lesson, game1, game2, game3,
            novice, intermediate, advance;
    RelativeLayout buttonsGame, buttonsDifficulty;
    int game, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_and_difficulty);
        /*lesson = findViewById(R.id.lesson);
        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);
        novice = findViewById(R.id.novice);
        intermediate = findViewById(R.id.intermediate);
        advance = findViewById(R.id.advance);*/

        buttonsGame = findViewById(R.id.buttons_game);
        //buttonsDifficulty = findViewById(R.id.buttons_difficulty);

        //buttonsDifficulty.setVisibility(View.GONE);
    }

    public void setLesson(View view) {
        game = 0;
        anim(buttonsGame);
        nextActivity();
    }

    public void setGame1(View view) {
        game = 1;
        anim(buttonsGame);
        nextActivity();
    }

    public void setGame2(View view) {
        game = 2;
        anim(buttonsGame);
        nextActivity();
    }

    public void setGame3(View view) {
        game = 3;
        anim(buttonsGame);
        nextActivity();
    }

    public void setNovice(View view) {
        difficulty = 0;
        //anim(buttonsDifficulty, buttonsGame);
        nextActivity();
    }

    public void setIntermediate(View view) {
        difficulty = 1;
        //anim(buttonsDifficulty, buttonsGame);
        nextActivity();
    }

    public void setAdvance(View view) {
        difficulty = 2;
        //anim(buttonsDifficulty, buttonsGame);
        nextActivity();
    }

    public void anim(RelativeLayout out){
        Animation animOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
        //Animation animIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        out.startAnimation(animOut);
        //in.startAnimation(animIn);

        //out.setVisibility(View.GONE);
        //in.setVisibility(View.VISIBLE);
    }

    public void nextActivity() {
        Intent intent = new Intent(this, activity_game_proper.class);
        intent.putExtra("game", game);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        }, 200);
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