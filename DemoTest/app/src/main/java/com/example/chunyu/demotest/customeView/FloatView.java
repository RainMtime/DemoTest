package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by chunyu on 2017/12/19.
 */

public class FloatView extends View {
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;

    private WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    private WindowManager.LayoutParams wmParams;

    public FloatView(Context context, WindowManager.LayoutParams params) {
        super(context);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams = params;
        // TODO Auto-generated constructor stub
    }


    public FloatView(Context context) {
        super(context);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY();   //25是系统状态栏的高度
        Log.i("chunyu-pos", "currX" + x + "====currY" + y);
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //获取相对View的坐标，即以此View左上角为原点
//                mTouchStartX = event.getX();
//                mTouchStartY = event.getY();
//
//                Log.i("chunyu-pos", "startX" + mTouchStartX + "====startY" + mTouchStartY);
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                updateViewPosition();
//                break;
//
//            case MotionEvent.ACTION_UP:
//                updateViewPosition();
//                mTouchStartX = mTouchStartY = 0;
//                break;
//        }
        return true;
    }

    private void updateViewPosition() {
        //更新浮动窗口位置参数
//        wmParams.x = (int) (x - mTouchStartX);
//        wmParams.y = (int) (y - mTouchStartY);
//        wm.updateViewLayout(this, wmParams);
       setTranslationX(x - mTouchStartX);
       setTranslationY(y - mTouchStartY);

    }

}