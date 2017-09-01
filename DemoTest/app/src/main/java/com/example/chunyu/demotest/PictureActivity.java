package com.example.chunyu.demotest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by chunyu on 2017/8/30.
 */

public class PictureActivity extends AppCompatActivity {


    private Button mButton;

    private ViewGroup mViewGroup;

    private View picture_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_layout);
        initView();
    }

    private void initView() {

        picture_view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.picture_view, null, false);
        mButton = (Button) findViewById(R.id.btn);
        mViewGroup = (ViewGroup) findViewById(R.id.picture_root_view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = drawBitmap(picture_view);

                String filename = "test";
                String contentUrl = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),
                        bitmap, filename, null);
                Log.e("chunyu-test", "path:" + contentUrl);
            }
        });
    }

    @Nullable
    private Bitmap getDrawingCacheBitmap(View view) {

        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        view.buildDrawingCache();
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }

    @Nullable
    private Bitmap drawBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(750, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST));
        Log.i("chunyu-drawBitmap", "width:" + view.getMeasuredWidth() + "\t height:" + view.getMeasuredHeight());
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredWidth());
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


}
