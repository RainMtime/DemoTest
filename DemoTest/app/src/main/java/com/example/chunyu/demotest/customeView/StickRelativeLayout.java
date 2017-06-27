package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by chunyu on 2017/6/27.
 */

public class StickRelativeLayout extends RelativeLayout {

    boolean mEnabledIntercept = false;


    DispatchTouchEventListener mDispatchTouchEventListener ;

    public StickRelativeLayout(Context context) {
        super(context);
    }

    public StickRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       return mEnabledIntercept;
    }



    public void setDispatchTouchEventListener(DispatchTouchEventListener dispatchTouchEventListener) {
        mDispatchTouchEventListener = dispatchTouchEventListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mEnabledIntercept = mDispatchTouchEventListener != null && mDispatchTouchEventListener.dispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    public interface DispatchTouchEventListener {
        boolean dispatchTouchEvent(MotionEvent ev);
    }



}
