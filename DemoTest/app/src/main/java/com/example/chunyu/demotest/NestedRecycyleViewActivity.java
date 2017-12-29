package com.example.chunyu.demotest;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.chunyu.demotest.customeView.FloatView;
import com.example.chunyu.demotest.customeView.GestureButton;
import com.example.chunyu.demotest.viewHolder.BaseRecyclerViewHolder;
import com.example.chunyu.demotest.viewHolder.SwipeCardViewHolder;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/11/12.
 */

public class NestedRecycyleViewActivity extends AppCompatActivity {
    public static final String TAG = "chunyu_test";
    private WindowManager mWindowManager;
    RecyclerView mRecyclerView;
    FloatView floatingView;
    GestureDetector mGestureDetector;
    GestureButton mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_recycler_view_layout);
        initView();
    }


    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
//        mButton = (GestureButton) findViewById(R.id.button);

        SampleAdapter adapter = new SampleAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setData(mockData());
        initGesture();
//        mButton.setGestureDetector(mGestureDetector);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                printChildHashCode();
//                mRecyclerView.getAdapter().notifyDataSetChanged();
//
//                mRecyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        printChildHashCode();
//                    }
//                });
//            }
//        });
//
//
//        mButton.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                mWindowManager = (WindowManager) mButton.getContext().getSystemService(Context.WINDOW_SERVICE);
//                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
////                params.type = WindowManager.LayoutParams.TYPE_TOAST;
//
//                params.gravity = Gravity.TOP;
//
//                int xy[] = new int[2];
//
//                mButton.getLocationOnScreen(xy);
//                params.x = xy[0];
//                params.y = xy[1];
//
//                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//                params.format = PixelFormat.TRANSLUCENT;
//                params.windowAnimations = 0;
////                createFloatingView(params);
//                ImageView imageView = new ImageView(NestedRecycyleViewActivity.this);
//                imageView.setImageBitmap(createViewDrawable());
//                mWindowManager.addView(imageView, params);
//                return true;
//            }
//        });

    }

    public void initGesture() {
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private float touchX;
            private float touchY;

            private float sumDistanceX;
            private float sumDistanceY;

            @Override
            public boolean onDown(MotionEvent e) {
                touchX = e.getRawX();
                touchY = e.getRawY();
                Log.i("chunyu-touch", "touchx:" + touchX + "\t touchy:" + touchY);
                return false;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("chunyu-touch", "onSingleTapUp");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                sumDistanceX += distanceX;
                sumDistanceY += distanceY;
                Log.i("chunyu-touch", "onScroll->sumDistanceX:" + sumDistanceX + "\t sumDistanceY:" + sumDistanceY);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.i("chunyu-touch", "onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("chunyu-touch", "onFling");
                return false;
            }
        });
    }

    public Bitmap createViewDrawable() {
        mButton.setDrawingCacheEnabled(true);
        return Bitmap.createBitmap(mButton.getDrawingCache());
    }

    public void createFloatingView(WindowManager.LayoutParams params) {
        floatingView = new FloatView(this, params);
        floatingView.setBackgroundResource(R.drawable.ic_menu_camera);
    }


    public void printChildHashCode() {
        int childCount = mRecyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Log.i("hash_code", "pos:" + i + "hashcode:" + mRecyclerView.getChildAt(i).hashCode());
        }
    }

    protected ArrayList<ArrayList<String>> mockData() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                temp.add("" + i + "行,我是第" + j + "数据");
            }
            result.add(temp);
        }
        return result;
    }


    public static class SampleAdapter extends RecyclerView.Adapter {

        private Context mComtext;

        public SampleAdapter(Context context) {
            mComtext = context;
        }

        ArrayList<ArrayList<String>> mData = new ArrayList<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 1) {
                View view = LayoutInflater.from(mComtext).inflate(R.layout.item_recycle_view_layout, parent, false);
                Log.i("chunyu-create", "onCreateViewHolder");
                return new SampleViewHolder(view, mComtext);
            } else if (viewType == 2) {
                View view = LayoutInflater.from(mComtext).inflate(R.layout.swipe_cardview_layout, parent, false);
                return new BaseRecyclerViewHolder(view);
            } else {
//                View view = LayoutInflater.from(mComtext).inflate(R.layout.swipecard_view_layout,parent,false);
                View view = LayoutInflater.from(mComtext).inflate(R.layout.swipecard_view_layout, parent, false);
                return new SwipeCardViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == 1) {
                ((SampleViewHolder) holder).renderView(mData.get(position), position, mComtext);
            } else if (getItemViewType(position) == 2) {

            } else {
                ((SwipeCardViewHolder) holder).renderView(mData.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {

            if (position == 3) {
                return 3;
            } else if (position == 2) {
                return 2;
            }
            return 1;
        }

        public void setData(ArrayList<ArrayList<String>> datas) {
            mData.clear();
            mData.addAll(datas);
            notifyDataSetChanged();
        }
    }


    public static class ItemAdapter extends RecyclerView.Adapter {

        private Context mContext;

        public ItemAdapter(Context context) {
            mContext = context;
        }

        ArrayList<String> mData = new ArrayList<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.real_item_layout, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ItemViewHolder) holder).renderView(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void setData(ArrayList<String> datas) {
            mData.clear();
            mData.addAll(datas);
//            notifyDataSetChanged();
            notifyItemRangeChanged(0, datas.size());
        }

        @Override
        public int getItemViewType(int position) {
            return 2;
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        Button mButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.button);
            Log.e(TAG, "ItemViewholder");
        }

        public void renderView(String str) {
            mButton.setText(str);
            Log.v(TAG, str);
        }

    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {

        RecyclerView mRecyclerView;
        public String name;

        @Override
        public String toString() {
            return "name:" + name;
        }

        public SampleViewHolder(View itemView, Context context) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(new ItemAdapter(context));
            Log.i(NestedRecycyleViewActivity.TAG, "SampleViewHolder");

        }

        public void renderView(ArrayList<String> ItemData, int position, Context context) {
            ((ItemAdapter) mRecyclerView.getAdapter()).setData(ItemData);
            Log.w(NestedRecycyleViewActivity.TAG, ItemData.toString());
        }
    }


}
