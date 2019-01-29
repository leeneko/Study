package com.leeneko.study;

import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class AsyncDownThread extends Thread {
    String mAddr;
    Handler mHandler;

    AsyncDownThread(String addr, Handler handler) {
        mAddr = addr;
        mHandler = handler;
    }

    public void run() {
        String result = downloadHtml(mAddr);

        Message message = mHandler.obtainMessage();
        message.obj = result;
        mHandler.sendMessage(message);
    }

    private String downloadHtml(String addr) {
        StringBuilder html = new StringBuilder();
        try {
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) break;
                        html.append(line);
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch (NetworkOnMainThreadException e) {
            return "Error : NetworkOnMainThreadException " + e.getMessage();
        } catch (Exception e) {
            return "Error : " + e.getMessage();
        }
        return html.toString();
    }

}
