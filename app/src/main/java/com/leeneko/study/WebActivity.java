package com.leeneko.study;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        findViewById(R.id.btnURL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncDownThread thread = new AsyncDownThread("http://wik.iptime.org:8080/cmsgs/list/0.json", mAfterDown);
                thread.start();
            }
        });
    }

    Handler mAfterDown = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            String log = "";

            try {
                JSONArray ja = new JSONArray(json);
                for (int i = 0; i < ja.length(); i++){
                    JSONObject chat = ja.getJSONObject(i);
                    String userMsg = chat.getString("user_msg");
                    String userNickname = chat.getString("user_nickname");
                    log += "nickname: " + userNickname + ", msg" + userMsg + "Wn";

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TextView tv = findViewById(R.id.tvLog);
            tv.setText(json);
        }
    };
}
