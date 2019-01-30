package com.leeneko.study;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

public class ToolbarFragment extends Fragment {

    private int seekValue = 10;
    public toolBarListener activityCallback;
    public interface toolBarListener {
        void onButtonClick(int fontSize, String text);
    }

    @Override
    public void onAttach(Context context) { // 초기화
        super.onAttach(context);
        try {
            activityCallback = (toolBarListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ToolbarListener");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 이 프래그먼트의 레이아웃을 인플레이트한다
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        final EditText et = view.findViewById(R.id.editText1);
        SeekBar sb = view.findViewById(R.id.seekBar1);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activityCallback.onButtonClick(seekValue, et.getText().toString());
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        return view;
    }
}
