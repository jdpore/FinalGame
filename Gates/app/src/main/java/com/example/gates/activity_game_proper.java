package com.example.gates;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
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
        popUp = findViewById(R.id.pop_up);

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
        round = findViewById(R.id.round);
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
}