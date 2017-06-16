package com.example.chunyu.demotest.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by chunyu on 2017/6/14.
 */

public final class DemoUtils {

    private static final String TAG = "DemoUtils";

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }


    public static boolean copyFile(File srcFile, File destFile) {

        try {
            long start = System.currentTimeMillis();
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];
            int num = 0;
            while ((num = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, num);
                fileOutputStream.flush();
            }
            fileInputStream.close();
            fileOutputStream.close();
            long end = System.currentTimeMillis();
            Log.i(TAG, "copyFiletime: " + (end - start));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "copyFile: exception:" + e.getMessage());
            return false;
        }
    }

    public static boolean copyFilebyBuffer(File srcFile, File destFile) {
        try {
            long start = System.currentTimeMillis();
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            BufferedInputStream bufInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufOutputStream = new BufferedOutputStream(fileOutputStream);
            int num = 0;
            while ((num = bufInputStream.read()) != -1) {
                Log.e(TAG, "copyFilebyBuffer->num:" + num);
                bufOutputStream.write(num);
                bufOutputStream.flush();
            }
            fileInputStream.close();
            fileOutputStream.close();
            long end = System.currentTimeMillis();
            Log.i(TAG, "copyFilebyBuffer: " + (end - start));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "copyFilebyBuffer: exception:" + e.getMessage());
            return false;
        }

    }


    public static boolean copyFilebyWriter(File srcFile, File destFile) {
        try {
            long start = System.currentTimeMillis();
            FileReader fileReader = new FileReader(srcFile);
            FileWriter fileWriter = new FileWriter(destFile);
            int num = 0;
            char[] chars = new char[1024];
            while ((num = fileReader.read(chars)) != -1) {
                Log.e(TAG, "copyFilebyWriter->num:" + num);
                fileWriter.write(chars, 0, num);
                fileWriter.flush();
            }
            fileReader.close();
            fileWriter.close();
            long end = System.currentTimeMillis();
            Log.i(TAG, "copyFilebyWriter: " + (end - start));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "copyFilebyWriter: exception:" + e.getMessage());
            return false;
        }
    }


    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

}
