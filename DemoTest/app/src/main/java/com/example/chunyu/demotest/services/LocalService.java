package com.example.chunyu.demotest.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chunyu.demotest.AIDL.IMyAidlInterface;

import java.util.Random;

/**
 * Created by chunyu on 2017/6/14.
 */

public class LocalService extends Service {

    private static final String TAG = "LocalService";

    // Random number generator
    private final Random mGenerator = new Random();

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind->" + intent.toString());
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public String hello() throws RemoteException {
            return "hello";
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand->intent:" + intent.toString() + "\tflags:" + flags + "\tstartId:" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    public String hello() {
        return "Hello chunyu!";
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
