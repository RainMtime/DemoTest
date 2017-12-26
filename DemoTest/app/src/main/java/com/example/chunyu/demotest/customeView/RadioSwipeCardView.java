package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chunyu.demotest.R;
import com.example.chunyu.demotest.Utils.DemoUtils;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/12/26.
 */

public class RadioSwipeCardView extends RelativeLayout {

    ArrayList<View> mChildView = new ArrayList<>();

    private float TouchX;
    private float TouchY;
    private float sumDistanceX;
    private float sumDistanceY;

    private  int count =0;

    private View mCurrentView;

    private boolean hasFloatingView;

    private ImageView floatingView;

    private WindowManager mWindowManager;

    public RadioSwipeCardView(Context context) {
        super(context);
        init(context);
    }

    public RadioSwipeCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadioSwipeCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        floatingView = new ImageView(context);
        createView();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                TouchX = event.getRawX();
                TouchY = event.getRawY();
                Log.i("chunyu-swipe", "TouchX:" + TouchX + "\tTouchY:" + TouchY);
                break;
            case MotionEvent.ACTION_CANCEL:
                resetView();
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                sumDistanceX = Math.abs(event.getRawX() - TouchX);
                if (!hasFloatingView && sumDistanceX > ViewConfiguration.getTouchSlop()) {
                    mCurrentView = retriveTopCardView();
                    addViewToWindow(mCurrentView);
                }
                if (hasFloatingView) {
                    translateView(event.getRawX(), event.getRawY());
                }
                break;
            case MotionEvent.ACTION_UP:
                resetView();
                break;
            default:
                return super.onTouchEvent(event);
        }

        return true;
    }


    public void createView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 300);
        params.setMargins(50, 10, 50, 50);
        imageView.setLayoutParams(params);
        imageView.setBackground(new ColorDrawable(DemoUtils.getRandomColor()));
        imageView.setBackgroundResource(R.drawable.ic_menu_camera);
        addView(imageView);
    }


    public void translateView(float x, float y) {
        if (floatingView != null && hasFloatingView) {
            floatingView.setTranslationX(2*count++);
            floatingView.setTranslationY(2*count++);
        }
    }


    public void resetView() {
        if (mWindowManager != null && floatingView.getWindowToken() != null) {
            mWindowManager.removeView(floatingView);
            hasFloatingView = false;
            count = 0;
        }
    }

    @Nullable
    public View retriveTopCardView() {
        int childCount = getChildCount();
        if (childCount >= 1) {
            return getChildAt(childCount - 1);
        }
        return null;
    }

    @Nullable
    public Bitmap createViewDrawable(View view) {
        view.setDrawingCacheEnabled(true);
        return Bitmap.createBitmap(view.getDrawingCache());
    }


    public void addViewToWindow(View view) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();


        params.gravity = Gravity.TOP;

        int xy[] = new int[2];

        view.getLocationOnScreen(xy);
        params.x = 0;
        params.y = xy[1];

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = 0;

        floatingView.setImageBitmap(createViewDrawable(view));
        mWindowManager.addView(floatingView, params);
        hasFloatingView = true;
    }

}
