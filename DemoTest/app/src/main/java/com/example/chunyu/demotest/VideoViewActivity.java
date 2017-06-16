package com.example.chunyu.demotest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chunyu.demotest.Utils.DemoUtils;
import com.example.chunyu.demotest.customeView.FixSizeVideoView;

/**
 * Created by chunyu on 2017/6/13.
 */

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = "VideoViewActivity";
    FixSizeVideoView mVideoView;

    MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {

            Log.e(TAG, "onPrepared");
//            mVideoView.setVisibility(View.VISIBLE);
            mVideoView.start();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_layout);
        initView();
    }

    private void initView() {
        mVideoView = (FixSizeVideoView) findViewById(R.id.video_view);
        int width = DemoUtils.getScreenWidth(this);
        int height = (int) (width * 16f / 9f);
        mVideoView.setMeasureWidthSpec(width);
        mVideoView.setHeightMeasureSpec(height);
        mVideoView.setZOrderOnTop(false);
        mVideoView.setOnPreparedListener(mOnPreparedListener);
        mVideoView.setVideoURI(getUri());
    }

    private Uri getUri() {
        return Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.guide_video2);
    }


}
