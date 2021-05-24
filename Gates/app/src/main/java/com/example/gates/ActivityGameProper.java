package com.example.gates;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ActivityGameProper extends AppCompatActivity {
    TextView title, round;
    LinearLayout gameGrid, choices;
    ConstraintLayout popUp;
    Button crntButton;
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Integer> gameList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    int game,
            difficulty;

    String[] games = new String[] {"True or False: Output", "True or False: Input", "Gate Guess"};
    int[][] control, typeArray;

    LinearLayout[] columns;
    int[] gridTypeStatus;
    Drawable[] bg;
    Random rand = new Random();
    TextView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);
        round = findViewById(R.id.round);
        popUp = findViewById(R.id.pop_up);
        gameGrid = findViewById(R.id.game_grid);
        notification = findViewById(R.id.notif);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .1f));
        round.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .08f));

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            game = extras.getInt("game");
            String str = games[game];
            title.setText(str);
        }

        bgInit();
        gridTypeInit();
        gameInit();

        popUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
                for(int i = 0; i < 7; i++) {
                    columns[i].removeAllViews();
                }
                gameInit();
            }
        });
    }

    public void gameInit() {
        int key = rand.nextInt(gameList.size());
        GameBank bank = new GameBank();

        if(gameList.size() == 1) {
            difficulty++;
            gameList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        }

        control = bank.getProblem(game, gameList.get(key) + (difficulty * 4));
        gameList.remove(key);
        Log.e("DEBUG: size  ", String.valueOf(gameList.size()));
        Log.e("DEBUG: key  ", String.valueOf(key));
        layoutInit();
        choicesInit();
    }

    public void layoutInit() {
        columnInit();
        gameGrid.setBackground(bg[control[0][0]]);
        gridTypeStatus = new int[control[1].length];

        int cntrGrid = 0;
        int cntrType = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Button btn = new Button(this);
                int type = 0,
                        state = 0;

                btn.setEnabled(false);
                btn.setBackground(getDrawable(typeArray[0][0]));
                btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                        1.0f));

                //check if Active Grid
                for(int n: control[1]) {
                    if(n == cntrGrid) {
                        type = control[2][cntrType];
                        switch (game) {
                            case 0:
                                if(type == 4) {
                                    state = 2;
                                } else {
                                    state = rand.nextInt(2);
                                }
                                btn.setBackground(getDrawable(typeArray[type][state]));
                                gridTypeStatus[cntrType] = state;
                                if (cntrType == control[1].length - 1) {
                                    btn.setEnabled(true);
                                }
                                break;
                            case 1:
                                if(type == 4) {
                                    state = rand.nextInt(2);
                                } else {
                                    state = 2;
                                }
                                btn.setBackground(getDrawable(typeArray[type][state]));
                                gridTypeStatus[cntrType] = state;
                                if (cntrType != control[1].length) {
                                    btn.setEnabled(true);
                                }
                                break;
                            default:
                                break;
                        }

                        cntrType++;
                    }
                }

                int tempType = type,
                        tempState = state,
                        tempCntr = cntrType - 1;

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setButton(btn, tempCntr, tempType);
                    }
                });

                columns[i].addView(btn);
                cntrGrid++;
            }
        }
    }

    public void choicesInit() {
        if(game == 3) {
            choices = findViewById(R.id.choices);
            for(int i = 0; i < 6; i++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f));
                //experiment
                btn.setBackground(getDrawable(R.drawable.notification));
                btn.setForeground(getDrawable(typeArray[5][i]));
                choices.addView(btn);
            }
        }
    }

    public void setButton(Button btn, int cntrType, int type) {
        if(difficulty < 2) {
            if(gridTypeStatus[cntrType] == 1) {
                btn.setBackground(getDrawable(typeArray[type][0]));
                gridTypeStatus[cntrType] = 0;
            } else {
                btn.setBackground(getDrawable(typeArray[type][1]));
                gridTypeStatus[cntrType] = 1;
            }
        }
    }

    public void check(View view) {
        int length = control[1].length;
        boolean[] types = new boolean[length];

        if(game == 0 || game == 1) {
            //convert from int to boolean
            for(int i = 0; i < length; i++) {
                types[i] = gridTypeStatus[i] == 1;
            }
            checkQ(types);
        }
    }



    public void bgInit() {
        bg = new Drawable[8];
        bg[0] = getDrawable(R.drawable.circuit1);
        bg[1] = getDrawable(R.drawable.circuit2);
        bg[2] = getDrawable(R.drawable.circuit3);
        bg[3] = getDrawable(R.drawable.circuit4);
        bg[4] = getDrawable(R.drawable.circuit5);
        bg[5] = getDrawable(R.drawable.circuit6);
        bg[6] = getDrawable(R.drawable.circuit7);
        bg[7] = getDrawable(R.drawable.circuit8);
    }

    public void gridTypeInit() {
        typeArray = new int[6][6];
        typeArray[0][0] = R.drawable.blank;
        typeArray[1][0] = R.drawable.a_false;
        typeArray[1][1] = R.drawable.a_true;
        typeArray[1][2] = R.drawable.a_blank;
        typeArray[2][0] = R.drawable.b_false;
        typeArray[2][1] = R.drawable.b_true;
        typeArray[2][2] = R.drawable.b_blank;
        typeArray[3][0] = R.drawable.c_false;
        typeArray[3][1] = R.drawable.c_true;
        typeArray[3][2] = R.drawable.c_blank;
        typeArray[4][0] = R.drawable.q_false;
        typeArray[4][1] = R.drawable.q_true;
        typeArray[4][2] = R.drawable.q_blank;
        typeArray[5][0] = R.drawable.and;
        typeArray[5][1] = R.drawable.or;
        typeArray[5][2] = R.drawable.nand;
        typeArray[5][3] = R.drawable.nor;
        typeArray[5][4] = R.drawable.xor;
        typeArray[5][5] = R.drawable.xnor;
    }

    public void columnInit() {
        columns  = new LinearLayout[7];
        columns[0] = findViewById(R.id.column_one);
        columns[1] = findViewById(R.id.column_two);
        columns[2] = findViewById(R.id.column_three);
        columns[3] = findViewById(R.id.column_four);
        columns[4] = findViewById(R.id.column_five);
        columns[5] = findViewById(R.id.column_six);
        columns[6] = findViewById(R.id.column_seven);
    }

    public void checkQ(boolean[] logic) {
        int inputs = logic.length - 1;
        int numbGates = control[3][0];
        int[][] instructions = new int[numbGates][];
        ArrayList<Boolean> states = new ArrayList<>();

        //get input states
        for(int i = 0; i < inputs; i++) {
            states.add(logic[i]);
        }

        //get Control Numbers for Gate Instructions
        //numbGates -> number of gates = number of instructions
        for(int i = 0; i < numbGates; i++) {
            instructions[i] = control[i+4];
        }

        for(int[] i: instructions) {
            int gate = i[0];
            switch (gate) {
                case 0: states.add(states.get(i[1]) && states.get(i[2]));
                    break;
                case 1: states.add(states.get(i[1]) || states.get(i[2]));
                    break;
                case 2: states.add(!(states.get(i[1]) && states.get(i[2])));
                    break;
                case 3: states.add(!(states.get(i[1]) || states.get(i[2])));
                    break;
                case 4: states.add((states.get(i[1]) || states.get(i[2])) &&
                        (!states.get(i[1]) || !states.get(i[2])));
                    break;
                case 5: states.add((states.get(i[1]) && states.get(i[2])) ||
                        (!states.get(i[1]) && !states.get(i[2])));
                    break;
                case 6: states.add(!states.get(i[1]));
                    break;
                default:
                    break;
            }
        }

        popUp.setVisibility(View.VISIBLE);

        if(states.get(states.size()-1) == logic[logic.length-1]) {
            notification.setText(String.valueOf("Right"));
        } else {
            notification.setText(String.valueOf("Wrong"));
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