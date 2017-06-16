package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by chunyu on 2017/6/14.
 */

public class FixSizeVideoView extends VideoView {

    private static final String TAG = "FixSizeVideoView";

    private int mMeasureWidthSpec;
    private int mHeightMeasureSpec;

    public FixSizeVideoView(Context context) {
        super(context);
    }

    public FixSizeVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixSizeVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "originWidth:" + widthMeasureSpec + "\toriginHeight:" + heightMeasureSpec);
        if (mMeasureWidthSpec > 0 && mHeightMeasureSpec > 0) {
            setMeasuredDimension(mMeasureWidthSpec, mHeightMeasureSpec);
            Log.e(TAG, "realWidth:" + mMeasureWidthSpec + "\t realHeight:" + mHeightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setMeasureWidthSpec(int measureWidthSpec) {
        mMeasureWidthSpec = measureWidthSpec;
    }

    public void setHeightMeasureSpec(int heightMeasureSpec) {
        mHeightMeasureSpec = heightMeasureSpec;
    }
}
