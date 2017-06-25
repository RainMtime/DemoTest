package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by 人间一小雨 on 2017/6/25.
 */

public class IntercepterRelativeLayout extends RelativeLayout {

    private static final String TAG = "IntercepterTest";

    boolean enableIntercepterTouchEvent = false;
    OnTouchListener mTouchListener;

    public IntercepterRelativeLayout(Context context) {
        super(context);
    }

    public IntercepterRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "event:" + event.toString());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"onInterceptTouchEvent->"+ev.toString());
//        if (enableIntercepterTouchEvent && mTouchListener != null) {
//            return true;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG,"dispatchTouchEvent->"+ev.toString());
        return super.dispatchTouchEvent(ev);
    }

    public void setEnableIntercepterTouchEvent(boolean enableIntercepterTouchEvent) {
        this.enableIntercepterTouchEvent = enableIntercepterTouchEvent;
    }

    public void setmTouchListener(OnTouchListener mTouchListener) {
        this.mTouchListener = mTouchListener;
    }
}
