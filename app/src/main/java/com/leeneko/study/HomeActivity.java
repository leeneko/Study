package com.leeneko.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final List<String> arData = new ArrayList<>();
        arData.add("Main");
        arData.add("Image");
        arData.add("Intent");
        arData.add("ListView");
        arData.add("Custom ListView");

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arData);
        ListView lv = findViewById(R.id.lvMenu);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (position == 0) { intent = new Intent(HomeActivity.this, MainActivity.class); }
                if (position == 1) { intent = new Intent(HomeActivity.this, ImageActivity.class); }
                if (position == 2) { intent = new Intent(HomeActivity.this, SubActivity.class); }
                if (position == 3) { intent = new Intent(HomeActivity.this, ListActivity.class); }
                if (position == 4) { intent = new Intent(HomeActivity.this, ChatActivity.class); }
                startActivity(intent);
            }
        });
    }
}
