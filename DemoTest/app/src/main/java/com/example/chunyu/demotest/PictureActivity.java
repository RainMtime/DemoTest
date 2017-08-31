package com.example.chunyu.demotest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by chunyu on 2017/8/30.
 */

public class PictureActivity extends AppCompatActivity {


    private Button mButton;

    private ViewGroup mViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_layout);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn);
        mViewGroup = (ViewGroup) findViewById(R.id.picture_root_view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewGroup.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                mViewGroup.buildDrawingCache();
                Bitmap bitmap = mViewGroup.getDrawingCache();

                String filename = "test";
                String contentUrl = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),
                        bitmap, filename, null);
                Log.e("chunyu-test", "path:" + contentUrl);
            }
        });
    }


}
