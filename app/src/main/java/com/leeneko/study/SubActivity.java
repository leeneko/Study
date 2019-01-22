package com.leeneko.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    String msg;
    TextView tvMessage;
    int weak;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        tvMessage = findViewById(R.id.tvMessage);

        setMessage(2);

        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() { // 마이너스 버튼 클릭 리스너
            public void onClick(View v) {
                if (weak > 0) setMessage(--weak);
            }
        });

        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() { // 플러스 버튼 클릭 리스너
            public void onClick(View v) {
                setMessage(++weak);
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() { // NEXT 버튼 클릭 리스너
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
    }

    void setMessage(int i) {
        msg = "안드로이드 학습기간이 "+ i + "주인데 괜찮나요?";
        tvMessage.setText(msg);
    }
}
