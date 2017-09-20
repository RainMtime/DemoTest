package com.example.chunyu.demotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
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

    View mAccessibilityView;

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
        mAccessibilityView = findViewById(R.id.accessibility);
        mViewGroup = (RelativeLayout) findViewById(R.id.relative_layout);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekBar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSeekBar.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_ENTER);
                        mSeekBar.announceForAccessibility("你还是少年！");
                        Log.i("chunyu", "foucus!");
                    }
                }, 300);

            }
        });

//        mTextView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
//            @Override
//            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
//                super.onPopulateAccessibilityEvent(host, event);
//
//                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
//                    event.getText().add("来来往往，一个人的北京");
//                }
//            }
//        });

        mAccessibilityView.setAccessibilityDelegate(new View.AccessibilityDelegate() {

            @Override
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                    event.getText().add("来来往往，一个人的北京");
                }
            }

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
//                info.setSelected(true);
            }
        });


    }


}
