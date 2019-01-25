package com.leeneko.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int week = bundle.getInt("week");
            String msg= bundle.getString("msg");
            TextView tv = findViewById(R.id.tvWeak);
            tv.setText(week + "" + msg);
        }

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(ImageActivity.this, MainActivity.class);
            startActivity(intent);
            }
        });
    }
}
