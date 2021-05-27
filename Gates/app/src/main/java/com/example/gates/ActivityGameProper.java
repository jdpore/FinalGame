package com.example.gates;

import android.content.Intent;
import android.graphics.Color;
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
import pl.droidsonroids.gif.GifImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ActivityGameProper extends AppCompatActivity {
    TextView title, round, reminder, score;
    LinearLayout gameGrid, choices;
    ConstraintLayout popUp;
    Button crntButton;
    Button[] btnChoices;
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Integer> gameList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    GifImageView notifGIF;
    int game,
            difficulty = 0,
            btnStates,
            cntrRound = 1,
            cntrWin = 0;

    String[] games = new String[] {"True or False: Output", "True or False: Input", "Gate Guess"};
    int[][] control, gridTypes;

    LinearLayout[] columns;
    int[] gridIOStatus,
            gridGateStatus;
    Drawable[] bg;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_proper);

        title = findViewById(R.id.title);
        round = findViewById(R.id.round);
        reminder = findViewById(R.id.reminder);
        popUp = findViewById(R.id.pop_up);
        gameGrid = findViewById(R.id.game_grid);
        notifGIF = findViewById(R.id.notif_gif);
        choices = findViewById(R.id.choices);
        score = findViewById(R.id.score);

        crntButton = new Button(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .1f));
        round.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .08f));
        reminder.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .04f));
        score.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .06f));

        score.setTextColor(Color.WHITE);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            game = extras.getInt("game");
            String str = games[game];
            title.setText(str);
        }

        columnInit();
        bgInit();
        gridTypeInit();
        gameInit();

        popUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popUp.setVisibility(View.GONE);
                if(game < 2 && cntrRound == 10) {
                    startActivity(new Intent(ActivityGameProper.this, ActivityGames.class));
                } else if(game == 3) {

                }
                cntrRound++;
                Log.e("Debug: cntrGate        ", String.valueOf(cntrRound));
            }
        });
    }

    public void gameInit() {
        int key = rand.nextInt(gameList.size());
        GameBank bank = new GameBank();

        round.setText(String.valueOf("Round #"  + cntrRound));

        if(gameList.size() == 1) {
            difficulty++;
            gameList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        }

        if(game < 2) {
            control = bank.getProblem(game, gameList.get(key) + (difficulty * 4));
        } else {
            control = bank.getProblem(game, rand.nextInt(4));
        }
        gameList.remove(key);

        layoutInit();
    }

    public void layoutClear() {
        //clear layout
        if(Integer.parseInt(round.getText().toString().substring(7)) > 0) {
            for (LinearLayout n: columns) {
                n.removeAllViews();
            }
        }
    }

    public void layoutInit() {
        int length = control[1].length;
        int cntrGrid = 0;
        int cntrIO = 0;
        int cntrGates = 0;
        int type = 1,
                state = 0;
        gridIOStatus = new int[length];
        gridGateStatus = new int[control[control.length - 1].length];

        //clear choices Layout
        if(game == 2) {choices.removeAllViews();}
        layoutClear();
        gameGrid.setBackground(bg[control[0][0]]);
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                Button btn = new Button(this);

                btn.setEnabled(false);
                btn.setBackground(getDrawable(gridTypes[0][0]));
                btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,
                        1.0f));

                //check if Active Grid for Input Output
                for(int n: control[1]) {
                    if(n == cntrGrid) {
                        switch (game) {
                            case 0:
                                if(type == length) {
                                    type = 4;
                                    state = 2;
                                } else {
                                    state = rand.nextInt(2);
                                }
                                if (cntrIO == length - 1) {
                                    btn.setEnabled(true);
                                }
                                break;
                            case 1:
                                if(type == length) {
                                    type = 4;
                                    state = rand.nextInt(2);
                                } else {
                                    state = 2;
                                }
                                if (cntrIO != length - 1) {
                                    btn.setEnabled(true);
                                }
                                break;
                            case 2:
                                if(type == length) {type = 4;}
                                state = rand.nextInt(2);
                                break;
                            default:
                                break;
                        }
                        btn.setBackground(getDrawable(gridTypes[type++][state]));
                        gridIOStatus[cntrIO++] = state;
                    }
                }

                //check if Active Grid for Gates
                if(game == 2) {
                    for (int n: control[control.length - 1]) {
                        if(n == cntrGrid) {
                            btn.setBackground(getDrawable(gridTypes[0][1]));
                            btn.setEnabled(true);
                            gridGateStatus[cntrGates++] = 7;
                        }
                    }
                }

                int finalIO = cntrIO - 1,
                        finalGate = cntrGates - 1,
                        finalType = type - 1;
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setButton(btn, finalIO, finalGate, finalType);
                    }
                });

                columns[i].addView(btn);
                cntrGrid++;
            }
        }
    }

    public void setButton(Button btn, int cntrIO, int cntrGate, int IO) {
        if(game < 2) {
            if(gridIOStatus[cntrIO] == 1) {
                //change button display from true to false
                btn.setBackground(getDrawable(gridTypes[IO][0]));
                gridIOStatus[cntrIO] = 0;
            } else {
                //change button display from blank/false to true
                btn.setBackground(getDrawable(gridTypes[IO][1]));
                gridIOStatus[cntrIO] = 1;
            }
        } else {
            //change button (grid) background to selected mode
            btn.setBackground(getDrawable(gridTypes[0][2]));
            if(crntButton != btn) {
                //change button (grid) background to non-selected mode
                crntButton.setBackground(getDrawable(gridTypes[0][1]));
                //clear the buttons in choices Layout then create a new set of buttons
                choices.removeAllViews();
                choicesInit(cntrGate);
            }
            //assign current button for monitoring and ease of access
            crntButton = btn;
        }
    }

    public void choicesInit(int cntrGate) {
        btnChoices = new Button[6];
        btnStates = 0;
        for(int i = 0; i < 6; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            //experiment
            btn.setBackground(getDrawable(R.drawable.gates_bg));
            btn.setForeground(getDrawable(gridTypes[5][i]));

            int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    crntButton.setForeground(getDrawable(gridTypes[5][finalI]));
                    gridGateStatus[cntrGate] = finalI;
                    if(btnStates == 1) {
                        for(Button btn: btnChoices) {
                            btn.setBackground(getDrawable(R.drawable.gates_bg));
                        }
                    }
                    btn.setBackground(getDrawable(R.drawable.gates_selected));
                    btnStates = 1;
                }
            });

            choices.addView(btn);
            btnChoices[i] = btn;
        }

        if(gridGateStatus[cntrGate] != 7) {
            Log.e("Debug: cntrGate        ", String.valueOf(cntrGate));
            btnChoices[gridGateStatus[cntrGate]].setBackground(getDrawable(R.drawable.gates_selected));
        }
    }

    public void check(View view) {
        int length = control[1].length;

        popUp.setVisibility(View.VISIBLE);

        if(game == 0 && gridIOStatus[length-1] == 2) {
            notifGIF.setBackgroundResource(R.drawable.nice_try);
            nextGame();
            return;
        } else if(game == 1) {
            for(int i = 0; i < length-1; i++) {
                if(gridIOStatus[i] == 2) {
                    notifGIF.setBackgroundResource(R.drawable.nice_try);
                    nextGame();
                    return;
                }
            }
        } else if(game == 2) {
            for(int i: gridGateStatus) {
                if(i == 7) {
                    notifGIF.setBackgroundResource(R.drawable.nice_try);
                    nextGame();
                    return;
                }
            }
        }

        checkQ();
    }



    public void bgInit() {
        bg = new Drawable[16];
        bg[0] = getDrawable(R.drawable.circuit1);
        bg[1] = getDrawable(R.drawable.circuit2);
        bg[2] = getDrawable(R.drawable.circuit3);
        bg[3] = getDrawable(R.drawable.circuit4);
        bg[4] = getDrawable(R.drawable.circuit5);
        bg[5] = getDrawable(R.drawable.circuit6);
        bg[6] = getDrawable(R.drawable.circuit7);
        bg[7] = getDrawable(R.drawable.circuit8);
        bg[8] = getDrawable(R.drawable.circuit9);
        bg[9] = getDrawable(R.drawable.circuit10);
        bg[10] = getDrawable(R.drawable.circuit11);
        bg[11] = getDrawable(R.drawable.circuit12);
        bg[12] = getDrawable(R.drawable.circuit13);
        bg[13] = getDrawable(R.drawable.circuit14);
        bg[14] = getDrawable(R.drawable.circuit15);
        bg[15] = getDrawable(R.drawable.circuit16);
    }

    public void gridTypeInit() {
        gridTypes = new int[6][6];
        gridTypes[0][0] = R.drawable.blank;
        gridTypes[0][1] = R.drawable.blank_gate;
        gridTypes[0][2] = R.drawable.blank_selected;
        gridTypes[1][0] = R.drawable.a_false;
        gridTypes[1][1] = R.drawable.a_true;
        gridTypes[1][2] = R.drawable.a_blank;
        gridTypes[2][0] = R.drawable.b_false;
        gridTypes[2][1] = R.drawable.b_true;
        gridTypes[2][2] = R.drawable.b_blank;
        gridTypes[3][0] = R.drawable.c_false;
        gridTypes[3][1] = R.drawable.c_true;
        gridTypes[3][2] = R.drawable.c_blank;
        gridTypes[4][0] = R.drawable.q_false;
        gridTypes[4][1] = R.drawable.q_true;
        gridTypes[4][2] = R.drawable.q_blank;
        gridTypes[5][0] = R.drawable.and;
        gridTypes[5][1] = R.drawable.or;
        gridTypes[5][2] = R.drawable.nand;
        gridTypes[5][3] = R.drawable.nor;
        gridTypes[5][4] = R.drawable.xor;
        gridTypes[5][5] = R.drawable.xnor;
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

    public void checkQ() {
        int length = control[1].length;
        boolean[] logics = new boolean[length];
        int inputs = length - 1;
        int numbGates = control[2][0];
        int[][] instructions;
        ArrayList<Boolean> states = new ArrayList<>();

        //convert from int to boolean
        for(int i = 0; i <  length; i++) {
            logics[i] = gridIOStatus[i] == 1;
        }

        //get input states
        for(int i = 0; i < inputs; i++) {
            states.add(logics[i]);
        }

        //get Control Numbers for Gate Instructions
        //numbGates -> number of gates = number of instructions
        instructions = new int[numbGates][];
        for(int i = 0; i < numbGates; i++) {
            instructions[i] = control[i + 3];
            if(game == 2 && instructions[i][0] != 6) {
                instructions[i][0] = gridGateStatus[i];
            }
        }

        for(int[] i: instructions) {
            switch (i[0]) {
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

        Log.e("Debug: Q       ", String.valueOf(states.get(states.size()-1)));
        if(states.get(states.size()-1) == logics[length-1]) {
            //notification.setText(String.valueOf("Right"));
            cntrWin++;
            notifGIF.setBackgroundResource(R.drawable.good_job);
        } else {
            //notification.setText(String.valueOf("Wrong"));
            notifGIF.setBackgroundResource(R.drawable.nice_try);
        }

        nextGame();
    }

    public void nextGame() {
        if(game < 2 && cntrRound == 10) {
            score.setVisibility(View.VISIBLE);
            score.setText(String.valueOf("Final Score: " + cntrWin));
        } else {
            gameInit();
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