package com.example.administrator.jsbridge;



/**
 * Created by Administrator on 2016/10/20.
 */

public class DefaultHandler implements BridgeHandler {
    String TAG = "DefaultHandler";

    @Override
    public void handler(String data, CallBackFunction function) {
        if(function != null){
            function.onCallBack("DefaultHandler response data");
        }
    }
}
