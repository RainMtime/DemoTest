package com.example.chunyu.demotest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

/**
 * Created by chunyu on 2017/6/13.
 */

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MediaPlayerActivity";
    public static String VideoType = "VideoType";
    MediaPlayer mMediaPlayer = null;
    SurfaceView mSurfaceView = null;
    Button mStartBtn;
    Button mPauseBtn;
    Button mStopBtn;
    private int mCurrentPos = 0;
    private int mVideoType = 0;

    MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.e(TAG, "onPrepared");
            mMediaPlayer.seekTo(mCurrentPos);
            mMediaPlayer.start();
        }
    };

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.e(TAG, "onCompletion");
        }
    };

    MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            Log.e(TAG, "onError,what:" + what + "\n extra:" + extra);
            return false;
        }
    };

    MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(MediaPlayer mp) {
            Log.e(TAG, "onSeekComplete");

        }
    };

    SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.e(TAG, "surface被创建");
            Log.e(TAG, "onSurfaceCreated:" + mCurrentPos);
            initMediaPlayer(mCurrentPos);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.e(TAG, "surface被销毁");
            if (mMediaPlayer != null) {
                mCurrentPos = mMediaPlayer.getCurrentPosition();
                Log.e(TAG, "Destroyedpos:" + mCurrentPos);
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }
    };

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_layout);
        initArgs();
        initView();

    }

    private void initArgs() {
        mVideoType = getIntent().getIntExtra(VideoType, 0);

    }

    private void initView() {
        mStartBtn = (Button) findViewById(R.id.start_btn);
        mStopBtn = (Button) findViewById(R.id.stop_btn);
        mPauseBtn = (Button) findViewById(R.id.pause_btn);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        mSurfaceView.getHolder().addCallback(mCallback);
        mStopBtn.setOnClickListener(this);
        mStartBtn.setOnClickListener(this);
        mPauseBtn.setOnClickListener(this);
    }

    private Uri getUri() {
        if (mVideoType == 1) {
            return Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.guide_video2);
        } else {
            return Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.guide_video);
        }

    }

    private void initMediaPlayer(int currentPos) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnSeekCompleteListener(mOnSeekCompleteListener);
        mMediaPlayer.setOnErrorListener(mOnErrorListener);
        mMediaPlayer.setOnPreparedListener(mOnPreparedListener);
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        mMediaPlayer.reset();
        try {
            Log.e(TAG, "Uri:" + getUri().toString());
            mMediaPlayer.setDataSource(this, getUri());
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepare();
        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                mMediaPlayer.start();
                break;
            case R.id.stop_btn:
                mMediaPlayer.stop();
                break;
            case R.id.pause_btn:
                mMediaPlayer.pause();
                break;
        }
    }
}
