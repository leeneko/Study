package com.leeneko.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    private TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        textView = view.findViewById(R.id.textView1);
        return view;
    }

    public void changeTextProperties(int fontsize, String text) {
        textView.setTextSize(fontsize);
        textView.setText(text);
    }
}
