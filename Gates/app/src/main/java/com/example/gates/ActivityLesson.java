package com.example.gates;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityLesson extends AppCompatActivity {
    ImageView imgLesson;
    Button nextPrev;
    LinearLayout[] rows =  new LinearLayout[2];
    int lessonMonitor = 0;
    int[] gates = new int[8], btnStates = new int[8];
    int[][] image = new int[16][2];
    Button[] btnGroup = new Button[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        rows[0] = findViewById(R.id.row1);
        rows[1] = findViewById(R.id.row2);
        imgLesson = findViewById(R.id.img_lesson);
        nextPrev = findViewById(R.id.nextPrev);
        //prev = findViewById(R.id.prev);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        nextPrev.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .04f));
        //prev.setTextSize(TypedValue.COMPLEX_UNIT_PX, (width * .05f));

        imageInit();
        gatesInit();
        choicesInit();
    }

    public void choicesInit() {
        int count = 0;
        for(LinearLayout row: rows) {
            for(int i = 0; i < 4; i++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f));
                btn.setBackground(getDrawable(R.drawable.btn_unselected));
                btn.setForeground(getDrawable(gates[count]));
                int finalCount = count;
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        lessonInit(finalCount);
                        for(int i: btnStates) {
                            if (i == 1) {
                                for(Button btn: btnGroup) {
                                    btn.setBackground(getDrawable(R.drawable.btn_unselected));
                                }
                                break;
                            }
                        }
                        btnStates[finalCount] = 1;
                        btn.setBackground(getDrawable(R.drawable.btn));
                    }
                });
                btnStates[count] = 0;
                row.addView(btn);
                btnGroup[count] = btn;
                count++;
            }
        }
    }

    public void lessonInit(int count) {
        imgLesson.setBackgroundResource(image[count][0]);
        lessonMonitor = 0;

        nextPrev.setVisibility(View.VISIBLE);
        nextPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lessonMonitor == 0) {
                    imgLesson.setBackgroundResource(image[count][++lessonMonitor]);
                    nextPrev.setText(String.valueOf("Prev"));
                } else {
                    imgLesson.setBackgroundResource(image[count][--lessonMonitor]);
                    nextPrev.setText(String.valueOf("Next"));
                }
            }
        });
    }

    public void gatesInit() {
        gates[0] = R.drawable.truth;
        gates[1] = R.drawable.not;
        gates[2] = R.drawable.and;
        gates[3] = R.drawable.or;
        gates[4] = R.drawable.nand;
        gates[5] = R.drawable.nor;
        gates[6] = R.drawable.xor;
        gates[7] = R.drawable.xnor;
    }

    public void imageInit() {
        image[0][0] = R.drawable.lesson00;
        image[0][1] = R.drawable.lesson01;
        image[1][0] = R.drawable.lesson10;
        image[1][1] = R.drawable.lesson11;
        image[2][0] = R.drawable.lesson20;
        image[2][1] = R.drawable.lesson21;
        image[3][0] = R.drawable.lesson30;
        image[3][1] = R.drawable.lesson31;
        image[4][0] = R.drawable.lesson40;
        image[4][1] = R.drawable.lesson41;
        image[5][0] = R.drawable.lesson50;
        image[5][1] = R.drawable.lesson51;
        image[6][0] = R.drawable.lesson60;
        image[6][1] = R.drawable.lesson61;
        image[7][0] = R.drawable.lesson70;
        image[7][1] = R.drawable.lesson71;
    }
}