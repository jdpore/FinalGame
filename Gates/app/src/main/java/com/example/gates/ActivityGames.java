package com.example.gates;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ActivityGames extends AppCompatActivity {
    Button lesson, game1, game2, game3,
            novice, intermediate, advance;
    ConstraintLayout buttonsGame, buttonsDifficulty;
    int game, difficulty;
    MediaPlayer music_pick_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        buttonsGame = findViewById(R.id.games_layout);
        music_pick_game = MediaPlayer.create(ActivityGames.this,R.raw.music_pick_game);

    }

    public void setLesson(View view) {
        anim(buttonsGame);
        startActivity(new Intent(ActivityGames.this, ActivityLesson.class));
        music_pick_game.start();
    }

    public void setGame1(View view) {
        game = 0;
        nextActivity();
    }

    public void setGame2(View view) {
        game = 1;
        nextActivity();
    }

    public void setGame3(View view) {
        game = 2;
        nextActivity();
    }

    public void anim(ConstraintLayout out){
        Animation animOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
        //Animation animIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        out.startAnimation(animOut);
        //in.startAnimation(animIn);

        //out.setVisibility(View.GONE);
        //in.setVisibility(View.VISIBLE);
    }

    public void nextActivity() {
        Intent intent = new Intent(this, ActivityGameProper.class);
        intent.putExtra("game", game);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent.putExtra("difficulty", difficulty);
                anim(buttonsGame);
                startActivity(intent);
                music_pick_game.start();
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