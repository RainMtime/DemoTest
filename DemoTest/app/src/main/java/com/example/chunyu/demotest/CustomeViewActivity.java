package com.example.chunyu.demotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.chunyu.demotest.customeView.IntercepterRelativeLayout;

/**
 * Created by 人间一小雨 on 2017/6/25.
 */

public class CustomeViewActivity extends AppCompatActivity {

    private static final String TAG = "CustomeViewActivity";
    IntercepterRelativeLayout rootView;
    View bottomView;
    private int originX;
    private int originY;

    private int originBottomX = 0;
    private int originBottomY = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custome_activity_layout);
        initView();
    }

    private void initView() {
        rootView = (IntercepterRelativeLayout) findViewById(R.id.root_view);
        rootView.setEnableIntercepterTouchEvent(true);
        bottomView = findViewById(R.id.bottom_view);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("IntercepterTest", "onTouch->" + event.toString());
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        originX = (int) event.getRawX();
                        originY = (int) event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        animationView(originY - (int) event.getY());
                        return true;
                    case MotionEvent.ACTION_UP:
                        originBottomY = 0;
                        break;
                }
                return true;
            }
        });
    }

    private void animationView(int offsetY) {
        if (originBottomY == 0) {
            originBottomY = bottomView.getLayoutParams().height;
        }
        ViewGroup.LayoutParams params = bottomView.getLayoutParams();
        params.height = offsetY + originBottomY;
        bottomView.requestLayout();
    }
}
