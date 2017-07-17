package com.example.chunyu.demotest;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chunyu.demotest.fragment.AnimFragment;

/**
 * Created by 人间一小雨 on 2017/6/24.
 */

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AnimationActivity";
    View animView;
    ViewGroup container;
    View clickView;
    View fakeView;

    int offsetX = 0;
    int offsetY = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);
        initView();
    }

    private void initView() {
        animView = findViewById(R.id.anim_view);
        animView.setOnClickListener(this);
        container = (ViewGroup)findViewById(R.id.container);
        container.setOnClickListener(this);
        clickView = findViewById(R.id.click_view);
        clickView.setOnClickListener(this);
        fakeView = findViewById(R.id.fake_container);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.child_view_anim);
//        LayoutAnimationController  layoutAnimationController = new LayoutAnimationController(animation);
//        container.setLayoutAnimation(layoutAnimationController);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.anim_view:
                printPos();
                addFragment();
                break;
            case R.id.container:
                addChildView();
                break;
            case R.id.click_view:
                Toast.makeText(this,"haha",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void addChildView(){
        finish();
        overridePendingTransition(R.anim.anim_enter_anim,R.anim.anim_exit_anim);
//        View view = new View(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
//        view.setBackgroundColor(Color.YELLOW);
//        view.setLayoutParams(params);
//        container.addView(view);
    }

    private void printPos(){
        Log.d("chunyu-test","getX:"+animView.getX());
        Log.d("chunyu-test","getY:"+animView.getY());
        Log.d("chunyu-test","getLeft:"+animView.getLeft());
        Log.d("chunyu-test","getRight:"+animView.getRight());
        Log.d("chunyu-test","getTop:"+animView.getTop());
        Log.d("chunyu-test","getBottom:"+animView.getBottom());
        Log.d("chunyu-test","getwidth:"+animView.getWidth());
        Log.d("chunyu-test","getmeasurewidth:"+animView.getMeasuredWidth());
        Log.d("chunyu-test","getheight:"+animView.getHeight());
        Log.d("chunyu-test","getmesureHeight:"+animView.getMeasuredHeight());




//        offsetX +=10;
//        offsetY +=10;
//        //累积位移
//        animView.offsetLeftAndRight(offsetX);
//        animView.offsetTopAndBottom(offsetY);
//        //不累积位移
//        animView.setTranslationX(offsetX);

//        //不累计，滚动放行和预期的相反，负数值滚动到正向。
//        offsetY = 100;
//        offsetX = 200;
//        container.scrollTo(-offsetX,-offsetY);
//        Log.d("chunyu-test","getScrollX:"+container.getScrollX());
//        Log.d("chunyu-test","getScrollY:"+container.getScrollY());

//        //帧动画
//        AnimationDrawable animationDrawable = new AnimationDrawable();
    }

    private void addFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim,R.anim.exit_anim,R.anim.anim_enter_anim,R.anim.anim_exit_anim);
        AnimFragment fragment = new AnimFragment();
       fragmentTransaction.add(R.id.fake_container,fragment).commitAllowingStateLoss();
    }

    private void printVisibleRect(){
        Rect rect = new Rect();
        Log.d("chunyu-test","getLocalVisibleRect:"+animView.getLocalVisibleRect(rect));
        Log.d("chunyu-test","getmesureHeight:"+animView.getMeasuredHeight());
        Log.d("chunyu-test","getmesureHeight:"+animView.getMeasuredHeight());
        Log.d("chunyu-test","getmesureHeight:"+animView.getMeasuredHeight());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
    }
}
