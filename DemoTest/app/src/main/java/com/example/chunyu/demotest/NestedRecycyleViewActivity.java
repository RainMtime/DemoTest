package com.example.chunyu.demotest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/11/12.
 */

public class NestedRecycyleViewActivity extends AppCompatActivity {


    public static final String TAG = "chunyu_test";
    RecyclerView mRecyclerView;
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nested_recycler_view_layout);
        initView();
    }


    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        SampleAdapter adapter = new SampleAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemViewCacheSize(4);
        adapter.setData(mockData());

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


    public static class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

        private Context mComtext;

        public SampleAdapter(Context context) {
            mComtext = context;
        }

        ArrayList<ArrayList<String>> mData = new ArrayList<>();

        @Override
        public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mComtext).inflate(R.layout.item_recycle_view_layout, parent, false);
            return new SampleViewHolder(view, mComtext);
        }

        @Override
        public void onViewRecycled(SampleViewHolder holder) {
            super.onViewRecycled(holder);
            Log.i("onViewRecycled:", holder.name + "");
        }

        @Override
        public void onViewAttachedToWindow(SampleViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.i("onViewAttachedToWindow:", holder.name + "");
        }

        @Override
        public void onViewDetachedFromWindow(SampleViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            Log.i("onViewDetachFromWindow", holder.name + "");
        }


        @Override
        public void onBindViewHolder(SampleViewHolder holder, int position) {
            holder.renderView(mData.get(position), position, mComtext);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
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
            notifyDataSetChanged();
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
            LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
            linearSnapHelper.attachToRecyclerView(mRecyclerView);
            mRecyclerView.setAdapter(new ItemAdapter(context));
            Log.i(NestedRecycyleViewActivity.TAG, "SampleViewHolder");

        }

        public void renderView(ArrayList<String> ItemData, int position, Context context) {
            ((ItemAdapter) mRecyclerView.getAdapter()).setData(ItemData);
            Log.w(NestedRecycyleViewActivity.TAG, ItemData.toString());
        }
    }

}
