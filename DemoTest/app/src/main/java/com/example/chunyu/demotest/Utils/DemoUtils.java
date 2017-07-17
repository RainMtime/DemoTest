package com.example.chunyu.demotest.Utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by chunyu on 2017/6/14.
 */

public final class DemoUtils {

    private static final String TAG = "DemoUtils";

    private static Thread sMainThread = Looper.getMainLooper().getThread();
    private static Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static void post(Runnable r) {
        sMainHandler.post(r);
    }

    public static void postDelay(Runnable r, long delay) {
        sMainHandler.postDelayed(r, delay);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    public static int getRandomColor() {
        Random random = new Random();
        int a = 255;
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        a = a << 24;
        r = r << 16;
        g = g << 8;
        b = b << 0;
        return a | r | b | g;
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


    public static void sendPost() {

    }

    public static void sendGet() throws Exception {
        String url = "http://www.baidu.com";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent","");

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        bufferReader.close();
        Log.i(TAG, "content:" + stringBuilder);

    }

}
