package com.leeneko.study;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    String msg;
    TextView tvMessage;
    int week = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Log.d("sub", "onCreate");

        tvMessage = findViewById(R.id.tvMessage);
        setMessage(week);

        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() { // 마이너스 버튼 클릭 리스너
            public void onClick(View v) {
                if (week > 0) setMessage(--week);
            }
        });

        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() { // 플러스 버튼 클릭 리스너
            public void onClick(View v) {
                setMessage(++week);
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() { // NEXT 버튼 클릭 리스너
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, ImageActivity.class);
                intent.putExtra("week", week); // 데이터 전송
                intent.putExtra("msg", msg);
                startActivity(intent);
            }
        });
    }

    void setMessage(int i) {
        // String.format("안드로이드 학습기간이 %d주인데 괜찮나요?", week); // Java String format
        msg = "안드로이드 학습기간이 "+ i + "주인데 괜찮나요?";
        tvMessage.setText(msg);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("sub", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("sub", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("sub", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("sub", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("sub", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("sub", "onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("sub", "onConfigurationChanged");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("sub", "onRestoreInstanceState");
        week = savedInstanceState.getInt("week");
        setMessage(week);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("sub", "onSaveInstanceState");
        outState.putInt("week", week);
    }
}
