package com.xiangsong.ticket.presenter.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xiangsong on 2016/9/9.
 */
public class HttpUtil {
    public static String baseUrl = "http://10.10.2.47:80/My12306";
//    public static String baseUrl = "http://192.168.0.116:8080/My12306";
    public static HttpURLConnection getHttpUrlConnection(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        return connection;
    }

    public static void setBodyParameter(HttpURLConnection conn,String parameter)throws Exception {
        DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
        outputStream.write(parameter.getBytes("utf-8"));

        outputStream.flush();
        outputStream.close();

    }

    public static String readInputStream(HttpURLConnection conn)throws Exception {

        InputStream inputStream = conn.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String readline;
        StringBuilder stringBuilder = new StringBuilder();
        while ((readline = br.readLine())!=null){
            stringBuilder.append(readline);
        }
        br.close();
        inputStream.close();
        return stringBuilder.toString();
    }


}
