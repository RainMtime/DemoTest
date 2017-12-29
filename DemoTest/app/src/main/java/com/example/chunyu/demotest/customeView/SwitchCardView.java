package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.chunyu.demotest.R;
import com.example.chunyu.demotest.Utils.DemoUtils;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/12/27.
 */

public class SwitchCardView extends RelativeLayout {

    public final ArrayList<String> mDatas = new ArrayList<>();

    private View mTargetView;
    private ViewDragHelper mViewDragHelper;

    public SwitchCardView(Context context) {
        super(context);
        init(context);
    }

    public SwitchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setData(ArrayList<String> datas) {
        mDatas.addAll(datas);
    }


    private void init(Context context) {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTargetView = findViewById(R.id.swith_cardview_item);
    }

//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//
//    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            return child.getId() == R.id.swith_cardview_item;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return DemoUtils.getScreenHeight(getContext());
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return DemoUtils.getScreenWidth(getContext());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);

        return true;
    }
}
