package com.example.gates;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class activity_game_proper extends AppCompatActivity {
    TextView title;
    int game, difficulty;
    String[] games = new String[] {"lesson", "game1", "game2", "game3"};
    String[] difficulties = new String[] {"novice", "intermediate", "advance"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            game = extras.getInt("game");
            difficulty = extras.getInt("difficulty");
            String str = games[game] + " " + difficulties[difficulty];
            title.setText(str);
        }
    }
}