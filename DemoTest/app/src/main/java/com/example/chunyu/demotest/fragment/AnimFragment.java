package com.example.chunyu.demotest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chunyu.demotest.R;

/**
 * Created by 人间一小雨 on 2017/6/25.
 */

public class AnimFragment extends android.support.v4.app.Fragment {

    View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.ainm_fragment_layout,null);
        return mRootView;
    }
}
