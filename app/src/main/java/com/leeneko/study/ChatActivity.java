package com.leeneko.study;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    String hostname = "http://wik.iptime.org:8080";
    String mNickname;
    List<ChatData> arData = new ArrayList<>();
    ChatAdapter adapter;
    int lastID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        /*(R.id.~~~~)이건 int 형으로 관리하고 있음 */

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            mNickname = id;
        }

        adapter = new ChatAdapter(this,R.layout.list_item_chat, arData);
        /*dao 역할 중간 통로 역활(화면(ui)와 data를 연결하는/ )을 함 이게 있어야 입력을 하고 출력을함 */
        ListView lvItems = findViewById(R.id.lvMsgs);
        lvItems.setAdapter(adapter);
        /*listview를 사용할떄 위에 이 4줄이면 됨 */

        final EditText et = findViewById(R.id.etMsg);
        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = et.getText().toString().trim();
                /*trim() 사용하면 문장 앞뒤 공백을 잘라줌*/
                if(item == null || item.isEmpty())
                    return; /*이 조건이 된다면 밑에 줄들이 실행 안되고 넘어간다*/

                AsyncDownThread thread = null;
                try {
                    thread = new AsyncDownThread( String.format("%s/cmsgs/%s/%s",
                        hostname, mNickname, URLEncoder.encode(item, "UTF-8")), mNullHandler);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                thread.start();

                /*arData.add(new ChatData("test", item));
                adapter.notifyDataSetChanged();
                이게 있어야 출력이됨 add만 하면 db에는 입력이 되있지만 출력이 안됨 이걸 써줘야 출력이됨
                초기화 시킬때 ( 따끝내고 이거 사용하면됨)*/
                et.setText("");
            }
        });
        // mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeMessages(0);
    }

    Handler mNullHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mHandler.removeMessages(0);
            mHandler.sendEmptyMessage(0);
        }
    };

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            AsyncDownThread thread = new AsyncDownThread(String.format("%s/cmsgs/list/%d.json", hostname, lastID+1), mAfterDown);
            thread.start();
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    Handler mAfterDown = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;

            try {
                JSONArray ja = new JSONArray(json);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject chat = ja.getJSONObject(i);
                    String user_Nickname = chat.getString("user_nickname");
                    String userMsg = chat.getString("user_msg");
                    lastID = chat.getInt("id");
                    if (user_Nickname.equals(mNickname)) {
                        arData.add(new ChatData("", "", userMsg));
                    } else {
                        arData.add(new ChatData(user_Nickname, userMsg, ""));
                    }

                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
