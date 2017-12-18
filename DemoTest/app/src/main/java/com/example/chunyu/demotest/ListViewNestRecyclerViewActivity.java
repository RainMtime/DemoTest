package com.example.chunyu.demotest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/12/11.
 */

public class ListViewNestRecyclerViewActivity extends AppCompatActivity {

    ListView mListView;
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_recycler_listview_layout);
        mListView = (ListView) findViewById(R.id.list_view);
        mButton = (Button) findViewById(R.id.button);


        final ListViewAdapter adapter = new ListViewAdapter(this);
        mListView.setAdapter(adapter);
        adapter.setData(mockData());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printChildHashCode();
                adapter.notifyDataSetChanged();
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        printChildHashCode();
                    }
                });
            }
        });
    }


    public void printChildHashCode() {
        int childCount = mListView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Log.i("hash_code", "pos:" + i + "hashcode:" + mListView.getChildAt(i).hashCode());
        }
    }
    public static class ListViewAdapter extends BaseAdapter {
        private final Context mContext;
        ArrayList<ArrayList<String>> mData = new ArrayList<>();

        public ListViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NestedRecycyleViewActivity.SampleViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_view_layout, parent, false);
                viewHolder = new NestedRecycyleViewActivity.SampleViewHolder(convertView, mContext);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (NestedRecycyleViewActivity.SampleViewHolder) convertView.getTag();
            }
            viewHolder.renderView(mData.get(position), position, mContext);
            return convertView;
        }

        public void setData(ArrayList<ArrayList<String>> datas) {
            mData.addAll(datas);
            notifyDataSetChanged();
        }
    }

    protected ArrayList<ArrayList<String>> mockData() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                temp.add("x" + i + "行,我是第" + j + "数据");
            }
            result.add(temp);
        }
        return result;
    }

}
