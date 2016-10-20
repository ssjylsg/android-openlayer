package com.example.administrator.myapplication;

import android.os.Handler;
import android.webkit.JavascriptInterface;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/10/17.
 *
 * @class JsObject
 */

public class JsObject {
    private static final Semaphore semp = new Semaphore(1);
    public static Object TResult = null;
    private Handler mHandler = new Handler();
    private NetPosaMap map;


    public static void acquire(){

        try {
            semp.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * JsObject
     *
     * @param map
     */
    public JsObject(NetPosaMap map) {
        this.map = map;
    }

    /**
     * @param content
     * @return
     */
    @JavascriptInterface
    public String Excute(String content) {
        return null;
    }

    @JavascriptInterface
    public void ExcuteAndroid(String msg) {
        TResult = msg;
       // semp.release();
    }

}
