package com.leeneko.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String id;
    EditText temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        temp = findViewById(R.id.etInputId);
        SharedPreferences pref = getSharedPreferences("study", MODE_PRIVATE);
        temp.setText(pref.getString("id", "아이디를 입력해주세요"));

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 값 저장하기
                SharedPreferences sp = getSharedPreferences("study", MODE_PRIVATE); // 파일 생성
                SharedPreferences.Editor editor = sp.edit(); // 파일 수정할 객체(에디터)

                id = temp.getText().toString();
                editor.putString("id", id); // 에디터를 통해 값 입력
                editor.commit(); // 파일 저장

                if (id.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_LONG);
                } else {
                    Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }
}
