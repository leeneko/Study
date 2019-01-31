package com.leeneko.study;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BasicActivity extends AppCompatActivity {

    List<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ListView lv = findViewById(R.id.lvItem);
        lv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(addOnClickListener);
    }

    View.OnClickListener addOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            addListItem();
            Snackbar.make(view, "Item added to list", Snackbar.LENGTH_LONG).setAction("UNDO", undoOnClickListener).show();
        }
    };

    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            removeListItem();
            Snackbar.make(view, "Item removed", Snackbar.LENGTH_LONG).setAction("ACTION", null).show();
        }
    };

    private void addListItem() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH.mm.ss MM/dd/yyyy", Locale.KOREA);
        items.add(dataFormat.format(new Date()));
        adapter.notifyDataSetChanged();
    }

    private void removeListItem() {
        items.remove(items.size() - 1);
        adapter.notifyDataSetChanged();
    }

}
