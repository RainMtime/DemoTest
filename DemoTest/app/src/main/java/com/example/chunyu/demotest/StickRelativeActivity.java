package com.example.chunyu.demotest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chunyu.demotest.Utils.DemoUtils;
import com.example.chunyu.demotest.customeView.StickRelativeLayout;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/6/27.
 */

public class StickRelativeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    MyAdapter mAdapter;

    LinearLayoutManager mLinearLayoutManager;
    StickRelativeLayout mRelativeLayout;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stick_layout);
        initView();
    }

    private void initView() {
        mRelativeLayout = (StickRelativeLayout) findViewById(R.id.root_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new MyAdapter(this);
        ArrayList<String> mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDataList.add("我是第" + i + "个元素");
        }
        mAdapter.setData(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mRelativeLayout.setDispatchTouchEventListener(mDispatchTouchEventListener);

    }

    private StickRelativeLayout.DispatchTouchEventListener mDispatchTouchEventListener = new StickRelativeLayout.DispatchTouchEventListener() {

        float originY, originX;
        boolean mMoved;
        boolean mbeginSlide = false;

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            float distanceY;
            boolean consumed = false;

            Log.d("chunyu-debug", "playerview touch event = " + event.getActionMasked()
                    + " distance = " + (event.getRawY() - originY)

                    + "  mMoved = " + mMoved);
            if (isTop()) {
                Log.e("chunyu-test", "isTop =true");
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:
                        originY = event.getRawY();
                        Log.d("chunyu-debug", "originY = " + originY);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //待补充
                        break;
                    case MotionEvent.ACTION_MOVE:
                        distanceY = event.getRawY() - originY;
                        //代表下滑
                        if (distanceY > 0) {
                            mMoved = true;
                            animationTo(distanceY);
                            consumed = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mMoved) {
                            distanceY = event.getRawY() - originY;
                            if (distanceY > 200) {
                                animationShow();
                            } else {
                                //划回顶部。
                                animationShow();
                            }
                            animationTo(0);
                            consumed = true;
                            mMoved = false;

                        }
                        break;
                }
            } else {
                Log.e("chunyu-debug", "isTop:" + isTop());
            }
            return consumed;
        }
    };

//    private void tempIntercepter(){
//        Log.d("chunyu-debug", "playerview touch event = " + event.getActionMasked()
//                + " distance = " + (event.getRawY() - originY)
//
//                + " ; mMoved = " + mMoved);
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                originY = event.getRawY();
//                originX = event.getRawX();
//                mMoved = false;
//                mbeginSlide = true;
//                Log.d("chunyu-debug", "originY = " + originY);
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                //待补充
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mbeginSlide) {
//                    distanceY = event.getRawY() - originY;
//                    float distanceX = event.getRawX() - originX;
//                    if (mMoved) {
//                        if (isTop()) {
//                            animationTo(distanceY);
//                            consumed = true;
//                        }
//                    } else if (Math.abs(distanceY) < 100 && distanceY > 30) {
//                        mMoved = true;
//                        originY = event.getRawY();
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                if (mMoved && mbeginSlide) {
//                    distanceY = event.getRawY() - originY;
//                    if (distanceY > 200) {
//
//                    } else  {
//                        animationShow();
//                    }
//                    mMoved = false;
//                    mbeginSlide = false;
//                    consumed = true;
//                }
//                break;
//        }
//    }

    private void animationShow() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(500);
        final float startY = mRecyclerView.getTranslationY();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animtedValue = (Float) animation.getAnimatedValue();
                if (animtedValue != null) {
                    animationTo(animtedValue * startY);
                    Log.i("chunyu-debug", "TranlateY:" + animtedValue * startY);
                }
            }
        });
        animator.start();
    }

    // 利用函数做拉力效果。
    private void animationTo(float distanceY) {
        Log.d("chunyu-debug", "distanceY:" + distanceY);
        mRecyclerView.setTranslationY((int) distanceY);
    }


    private boolean isTop() {
        if (mLinearLayoutManager != null) {
            int pos = -2;
            pos = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
            Log.e("chunyu-test", "TopPos = " + pos);
            return pos == 0;
        }
        return false;
    }

    public static class MyVH extends RecyclerView.ViewHolder {


        public MyVH(View itemView) {
            super(itemView);
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyVH> {

        Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
        }

        ArrayList<String> mData = new ArrayList<>();

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            return new MyVH(textView);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {
            String text = mData.get(position);
            ((TextView) holder.itemView).setText(text);
            ((TextView) holder.itemView).setTextColor(DemoUtils.getRandomColor());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void setData(ArrayList<String> data) {
            mData.addAll(data);
        }
    }

}
