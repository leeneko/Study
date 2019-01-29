package com.leeneko.study;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        countHandler.sendEmptyMessage(0);
    }

    Handler countHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TextView tv = findViewById(R.id.tvCount);
            tv.setText(++count + "");
            countHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };
}
