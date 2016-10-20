package com.example.administrator.jsbridge;

/**
 * Created by Administrator on 2016/10/20.
 */

public interface WebViewJavascriptBridge {
    public void send(String data);
    public void send(String data, CallBackFunction responseCallback);
}
