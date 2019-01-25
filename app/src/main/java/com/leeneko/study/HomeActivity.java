package com.leeneko.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    Map<String, Class<?>> btnActs = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnActs.put("Main", MainActivity.class);
        btnActs.put("Image", ImageActivity.class);
        btnActs.put("Intent", SubActivity.class);
        btnActs.put("ListView", ListActivity.class);
        btnActs.put("Custom ListView", ChatActivity.class);

        final List<String> arData = new ArrayList<>();
        for (String key: btnActs.keySet()) {
            arData.add(key);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arData);
        ListView lv = findViewById(R.id.lvMenu);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lv = (ListView) parent;
                String item = (String) lv.getItemAtPosition(position);
                Intent intent = new Intent(HomeActivity.this, btnActs.get(item));
                startActivity(intent);
            }
        });
    }
}
