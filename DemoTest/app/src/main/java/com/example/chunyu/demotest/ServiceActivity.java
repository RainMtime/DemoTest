package com.example.chunyu.demotest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.chunyu.demotest.AIDL.IMyAidlInterface;
import com.example.chunyu.demotest.services.LocalService;

/**
 * Created by chunyu on 2017/6/14.
 */

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button mStartService;
    Button mBindService;
    Button mUnBindService;

    IMyAidlInterface mLocalService;


    private static final String TAG = "ServiceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        initView();
    }

    private void initView() {
        mBindService = (Button) findViewById(R.id.bindservice_btn);
        mStartService = (Button) findViewById(R.id.startservice_btn);
        mUnBindService = (Button) findViewById(R.id.unbindService);
        mBindService.setOnClickListener(this);
        mStartService.setOnClickListener(this);
        mUnBindService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bindservice_btn:
                Intent intent = new Intent(this, LocalService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.startservice_btn:
                Intent intent1 = new Intent(this, LocalService.class);
                startService(intent1);
                break;
            case R.id.unbindService:
                unbindService(mConnection);
                break;

        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected->name:" + name);
            mLocalService = IMyAidlInterface.Stub.asInterface(service);
            try {
                Log.e(TAG, mLocalService.hello());
            } catch (Exception e) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected->name:" + name);
        }
    };
}
