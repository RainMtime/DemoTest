package com.example.chunyu.demotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by chunyu on 2017/8/29.
 */

public class AccessibilityActivity extends AppCompatActivity {

    SeekBar mSeekBar;
    Button mButton;
    TextView mTextView;
    GridView mGridView;

    RelativeLayout mViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessibility_layout);
        initView();
    }

    private void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.textView);
        mViewGroup =(RelativeLayout) findViewById(R.id.relative_layout);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSeekBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_ENTER);
                        Log.i("chunyu","foucus!");
                    }
                }, 300);
            }
        });

    }


}
