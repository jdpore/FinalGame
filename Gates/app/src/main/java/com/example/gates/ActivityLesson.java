package com.example.gates;

import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityLesson extends AppCompatActivity {
    LinearLayout[] rows =  new LinearLayout[2];
    int[] gates = new int[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        rows[0] = findViewById(R.id.row1);
        rows[1] = findViewById(R.id.row2);

        gatesInit();
        lessonsInit();
    }

    public void lessonsInit() {
        int cntr = 0;
        for(LinearLayout row: rows) {
            for(int i = 0; i < 4; i++) {
                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f));
                btn.setForeground(getDrawable(gates[cntr]));
                row.addView(btn);
                cntr++;
            }
        }
    }

    public void gatesInit() {
        gates[0] = R.drawable.not;
        gates[1] = R.drawable.not;
        gates[2] = R.drawable.and;
        gates[3] = R.drawable.or;
        gates[4] = R.drawable.nand;
        gates[5] = R.drawable.nor;
        gates[6] = R.drawable.xor;
        gates[7] = R.drawable.xnor;
    }
}