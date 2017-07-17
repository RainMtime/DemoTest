package com.example.chunyu.demotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.chunyu.demotest.Utils.DemoUtils;
import com.example.chunyu.demotest.fragment.AnimFragment;
import com.example.chunyu.demotest.fragment.AnimFragment2;

/**
 * Created by chunyu on 2017/7/14.
 */

public class FragmentAnimActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       showFragment();
    }

    private void showFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.anim_enter_anim,R.anim.anim_exit_anim);
        fragmentTransaction.disallowAddToBackStack().replace(android.R.id.content, new AnimFragment2()).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        DemoUtils.postDelay(new Runnable() {
            @Override
            public void run() {
                showFragment2();
            }
        }, 2000);
    }


    private void showFragment2() { FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.anim_enter_anim,R.anim.anim_exit_anim);
        fragmentTransaction.disallowAddToBackStack().replace(android.R.id.content, new AnimFragment()).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }
}
