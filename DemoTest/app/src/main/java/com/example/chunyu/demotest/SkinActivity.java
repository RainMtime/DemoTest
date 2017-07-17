package com.example.chunyu.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 人间一小雨 on 2017/7/1.
 */

public class SkinActivity extends AppCompatActivity {

    Button mBtn;

    private static int mThemeId = R.style.AppTheme_Black;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(mThemeId);
        setContentView(R.layout.skin_activity);
        initView();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.skin_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThemeId = R.style.AppTheme_Light;
                recreate();
            }
        });

    }
}
