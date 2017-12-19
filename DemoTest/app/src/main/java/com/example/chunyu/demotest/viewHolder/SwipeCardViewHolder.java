package com.example.chunyu.demotest.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chunyu.demotest.R;
import com.example.chunyu.demotest.swipecard.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * Created by chunyu on 2017/12/18.
 */

public class SwipeCardViewHolder extends RecyclerView.ViewHolder implements SwipeFlingAdapterView.onFlingListener {
    SwipeFlingAdapterView mSwipeFlingAdapterView;
    InnerAdapter adapter;

    public SwipeCardViewHolder(View itemView) {
        super(itemView);
        mSwipeFlingAdapterView = (SwipeFlingAdapterView) itemView.findViewById(R.id.swipe_card_view);
        mSwipeFlingAdapterView.setIsNeedSwipe(true);
        mSwipeFlingAdapterView.setFlingListener(this);

        adapter = new InnerAdapter();
        mSwipeFlingAdapterView.setAdapter(adapter);
    }


    public void renderView(ArrayList<String> datas) {
        adapter.setDatas(datas);
    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {

    }

    @Override
    public void onRightCardExit(Object dataObject) {

    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            //loadmore();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }


    private class InnerAdapter extends BaseAdapter {

        private ArrayList<String> mDatas = new ArrayList<>();


        public InnerAdapter() {

        }

        public void setDatas(ArrayList<String> datas) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }


        public void remove(int index) {
            if (index > -1 && index < mDatas.size()) {
                mDatas.remove(index);
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public String getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            InnerViewHolder innerViewHolder = new InnerViewHolder();

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_item_layout, parent, false);
                innerViewHolder.mTextView = (TextView) convertView.findViewById(R.id.text_view);
                convertView.setTag(innerViewHolder);
            } else {
                innerViewHolder = (InnerViewHolder) convertView.getTag();
            }

            innerViewHolder.renderData(getItem(position));
            return convertView;
        }
    }

    private class InnerViewHolder {
        TextView mTextView;

        public void renderData(String data) {
            mTextView.setText(data);
        }
    }
}
