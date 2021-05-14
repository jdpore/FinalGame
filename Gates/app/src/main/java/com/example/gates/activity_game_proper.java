package com.example.gates;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class activity_game_proper extends AppCompatActivity {
    TextView title;
    LinearLayout gameGrid;
    int game, difficulty;
    String[] games = new String[] {"Lesson", "Game One", "Game Two", "Game Three"};
    String[] difficulties = new String[] {"novice", "intermediate", "advance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);
        gameGrid = findViewById(R.id.game_grid);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            game = extras.getInt("game");
            String str = games[game];
            title.setText(str);
        }

        //gridInit();
    }

    public void gridInit() {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();

        params.weight = 1;
        layout.setLayoutParams(params);
        gameGrid.addView(layout);
    }
}