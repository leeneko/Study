package com.leeneko.study;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotionEventActivity extends AppCompatActivity {

    Map<Integer, String> mEvent = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);

        mEvent.put(MotionEvent.ACTION_DOWN, "DOWN");
        mEvent.put(MotionEvent.ACTION_UP, "UP");
        mEvent.put(MotionEvent.ACTION_POINTER_DOWN, "PNTR DOWN");
        mEvent.put(MotionEvent.ACTION_POINTER_UP, "PNTR UP");
        mEvent.put(MotionEvent.ACTION_MOVE, "MOVE");

        ConstraintLayout myLayout = findViewById(R.id.LayoutBg);
        myLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch(event);
                return true;
            }

            private void handleTouch(MotionEvent event) {
                TextView tv1 = findViewById(R.id.tvOne);
                TextView tv2 = findViewById(R.id.tvTwo);

                int pointerCount = event.getPointerCount();
                // Log.d("ptCount", String.valueOf(pointerCount)); // 1 or 2

                for (int i = 0; i < pointerCount; i++) {
                    int x = (int) event.getX(i);
                    int y = (int) event.getY(i);
                    int id = event.getPointerId(i);
                    int action = event.getActionMasked();
                    int actionIndex = event.getActionIndex();

                    // String touchStatus = "Action: " + arData.get(action) + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
                    String touchStatus = String.format("Action : %s Index: %d ID: %d X: %d Y: %d", mEvent.get(action), actionIndex, id, x, y);

                    if (id == 0)
                        tv1.setText(touchStatus);
                    else
                        tv2.setText(touchStatus);
                }
            }
        });
    }
}
