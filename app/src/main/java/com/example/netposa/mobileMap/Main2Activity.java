package com.example.netposa.mobileMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        WebView webView = (WebView) this.findViewById(R.id.webView);
//
//
//        {
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message,
//                JsResult result) {
//            result.confirm();
//            Util.Info("LSG", message);
//            return false;
//        }
//        }

        webView.setWebChromeClient(new android.webkit.WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webSettings.setDomStorageEnabled(true);
        webView.requestFocus();
        String url = "http://192.168.61.28:807/mobile/dist/index.html";
        //"http://192.168.61.28:807/mobile/source/examples/mobile-jq.html"
        webView.loadUrl(url);
    }
}
