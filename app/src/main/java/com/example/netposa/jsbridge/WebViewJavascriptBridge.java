package com.example.netposa.jsbridge;

/**
 * WebViewJavascriptBridge
 */

public interface WebViewJavascriptBridge {
    public void send(String data);

    public void send(String data, CallBackFunction responseCallback);
}
