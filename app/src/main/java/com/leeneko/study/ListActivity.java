package com.leeneko.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private int number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final EditText temp = findViewById(R.id.etItem);
        final List<String> arData = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arData);
        ListView lvItems = findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String item = temp.getText().toString().trim();
                if(item == null || item.isEmpty())
                    return;
                arData.add(++number + " " + item);
                adapter.notifyDataSetChanged(); // 데이터 값이 변경 시 어댑터에 알림을 주어 출력 값도 맞춰 변경됨
                temp.setText("");
            }
        });
    }
}
