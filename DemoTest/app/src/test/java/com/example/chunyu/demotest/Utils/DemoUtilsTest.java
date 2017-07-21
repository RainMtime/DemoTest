package com.example.chunyu.demotest.Utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chunyu on 2017/7/13.
 */
public class DemoUtilsTest {
    @Test
    public void sendGet() throws Exception {
//        String url = "http://www.baidu.com";
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        con.setRequestMethod("GET");
////        con.setRequestProperty("User-Agent","");
//
//        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder stringBuilder = new StringBuilder();
//        while ((inputLine = bufferReader.readLine()) != null) {
//            stringBuilder.append(inputLine);
//        }
//        bufferReader.close();
//        Log.i(TAG, "content:" + stringBuilder);

        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=1&rsv_pq=b9d14c470005666a&rsv_t=a518wUC%2Fce22Tnnl2jNZxjTgZ27KdpN0uqDazE%2Fabm6huhRnj6G5j8GCLR0&rqlang=cn&rsv_enter=1&rsv_sug3=2&rsv_sug1=1&rsv_sug7=100&rsv_sug2=0&inputT=688&rsv_sug4=877";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.connect();
         //默认值我GET
        con.setRequestMethod("GET");

//        //添加请求头
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        //打印结果
        System.out.println(response.toString());

    }

    @Test
    public void add(){
        System.out.print("hahah");
    }



}