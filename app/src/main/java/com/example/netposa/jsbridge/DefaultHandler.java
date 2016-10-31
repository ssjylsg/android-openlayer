package com.example.netposa.jsbridge;


/**
 * DefaultHandler
 */

public class DefaultHandler implements BridgeHandler {
    String TAG = "DefaultHandler";

    @Override
    public void handler(String data, CallBackFunction function) {
        if (function != null) {
            function.onCallBack("DefaultHandler response data");
        }
    }
}
