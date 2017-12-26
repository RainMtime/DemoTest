package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by chunyu on 2017/12/26.
 */

public class GestureButton extends android.support.v7.widget.AppCompatButton {


    GestureDetector mGestureDetector;

    public GestureButton(Context context) {
        super(context);
    }

    public GestureButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setGestureDetector(GestureDetector gestureDetector) {
        mGestureDetector = gestureDetector;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            mGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }


}
