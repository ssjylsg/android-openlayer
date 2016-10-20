package com.example.administrator.myapplication;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;



/**
 * Created by Administrator on 2016/10/18.
 */

public class NPWebViewClient extends WebViewClient {

    private NetPosaMap map;

    public NPWebViewClient(NetPosaMap map) {
        this.map = map;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        this.map.CreateMap();
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        android.util.Log.e("NPWebViewClient", error.toString());
    }



}
