package com.example.chunyu.demotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chunyu.demotest.R;

/**
 * Created by chunyu on 2017/7/14.
 */

public class AnimFragment2 extends Fragment {

    View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.anim_fragment_layout2, null, false);
        return mRootView;
    }

    private void initView(){

    }
}
