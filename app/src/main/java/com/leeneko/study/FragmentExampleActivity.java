package com.leeneko.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentExampleActivity extends AppCompatActivity implements ToolbarFragment.toolBarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
    }

    @Override
    public void onButtonClick(int fontSize, String text) {
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentText);
        textFragment.changeTextProperties(fontSize, text);
    }
}
