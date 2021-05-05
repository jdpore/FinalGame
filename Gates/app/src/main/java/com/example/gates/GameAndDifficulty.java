package com.example.gates;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameAndDifficulty extends AppCompatActivity {
    Button game1, game2, game3, intro;
    RelativeLayout buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_and_difficulty);
    }
    public void game1(View view) {
        anim();
    }
    public void game2(View view) {
        anim();
    }

    public void game3(View view) {
        anim();
    }

    public void game4(View view) {
        anim();
    }
    public void anim(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide);
        buttons = findViewById(R.id.buttons);
        buttons.startAnimation(animation);
        /*game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);
        intro = findViewById(R.id.intro);
        game1.startAnimation(animation);
        game2.startAnimation(animation);
        game3.startAnimation(animation);
        intro.startAnimation(animation);*/
    }

}