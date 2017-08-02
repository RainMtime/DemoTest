package com.example.chunyu.demotest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
    GridLayoutManager mGridLayoutManager;
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
//        mLinearLayoutManager = new LinearLayoutManager(this);
//        mGridLayoutManager = new GridLayoutManager(this, 4);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mAdapter = new MyAdapter(this);
        ArrayList<String> mDataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDataList.add("我是第" + i + "个元素");
        }
        mRecyclerView.setLayoutManager(mAdapter.getGridLayoutManager());
        mAdapter.setData(mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("chunyu-recycleView", "上滑：" + recyclerView.canScrollVertically(1));
                Log.e("chunyu-recycleView", "下滑：" + recyclerView.canScrollVertically(-1));
            }
        });
//        mRelativeLayout.setDispatchTouchEventListener(mDispatchTouchEventListener);
    }

    private int getRecycleViewTop() {
        int[] location = new int[2];
        mRecyclerView.getLocationOnScreen(location);
        return location[1];
    }

    private StickRelativeLayout.DispatchTouchEventListener mDispatchTouchEventListener = new StickRelativeLayout.DispatchTouchEventListener() {

        float originY, originX;
        boolean mMoved;
        boolean mbeginSlide = false;
        boolean isInTop = false;


        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            float distanceY;
            boolean consumed = false;


            Log.d("chunyu-debug", "playerview touch event = " + event.getActionMasked()
                    + " distance = " + (event.getRawY() - originY)

                    + "  mMoved = " + mMoved);
            switch (event.getActionMasked()) {

                case MotionEvent.ACTION_DOWN:
                    originY = event.getRawY();
                    isInTop = isTop();
                    Log.d("chunyu-debug", "originY = " + originY);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    //待补充

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isInTop) {
                        distanceY = event.getRawY() - originY;
                        //代表下滑
                        if (distanceY > 0) {
                            mMoved = true;
                            animationTo(distanceY, true);
                            consumed = true;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isInTop && mMoved) {
                        distanceY = event.getRawY() - originY;
                        if (distanceY > 200) {
                            animationShow();
                        } else {
                            //划回顶部。
                            animationShow();
                        }
                        consumed = true;
                        mMoved = false;
                        isInTop = false;
                    }
                    break;
            }
            return consumed;
        }
    };


    private void animationShow() {//    private void tempIntercepter(){
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
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(200);
        final float startY = mRelativeLayout.getTranslationY();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animtedValue = (Float) animation.getAnimatedValue();
                if (animtedValue != null) {
                    animationTo(animtedValue * startY, false);
                    Log.i("chunyu-debug", "TranlateY:" + animtedValue * startY);
                }
            }
        });
        animator.start();
    }

    // 利用函数做拉力效果。
    private void animationTo(float distanceY, boolean iszuni) {

        if (iszuni) {
            double realDistanceY = Math.pow((double) distanceY, 0.80);
            Log.d("chunyu-debug", "distanceY=" + distanceY + "\t realDistanceY =" + realDistanceY);
            mRelativeLayout.setTranslationY((int) realDistanceY);

        } else {

            mRelativeLayout.setTranslationY((int) distanceY);
        }


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

        private GridLayoutManager mGridLayoutManager;

        public MyAdapter(Context context) {
            mContext = context;
            mGridLayoutManager = new GridLayoutManager(context, 4);
            mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    String str = getItem(position);
                    if (str.contains("4")) {
                        return 4;
                    }
                    return 1;
                }
            });

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
            String text = getItem(position);
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

        public String getItem(int position) {
            return mData.get(position);
        }

        public GridLayoutManager getGridLayoutManager() {
            return mGridLayoutManager;
        }
    }

}
