package com.example.administrator.myapplication;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.administrator.myapplication.Events.EventManager;
import com.example.administrator.myapplication.Layers.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/10/17.
 */

public class NetPosaMap extends Entity {
    private static final String NetPosaMap_TAG = "NetPosaMap";
    @JSONField(serialize = false)
    private WebView webView;
    /**
     * 互调对象
     */
    @JSONField(serialize = false)
    private JsObject jsObject;

    private final Semaphore semp = new Semaphore(1);

    private String mapConfig = "http://192.168.61.28:807/mobile/dist//mapCONFIG.JSON";
    private String mapContainer = "viewerContainer";
    private Boolean isMapavaild = false;
    private EventManager manager;

    public NetPosaMap(WebView webView) {
        this.setClassName("NPMobile.Map");
        this.jsObject = new JsObject(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new SimpleJavaJSWebChromeClient(this));
        webView.setWebViewClient(new NPWebViewClient(this));
        webView.addJavascriptInterface(jsObject, "Android");
        this.webView = webView;
        this.loadUrl("http://192.168.61.28:807/mobile/dist/index_c.html");
        manager = new EventManager();

    }

    public void CreateMap() {
        String msg = this.getJavascript(this, "donothing");
        android.util.Log.i(NetPosaMap_TAG, msg);
        this.webView.loadUrl(msg);
        isMapavaild = true;
        while (!manager.isEmpty()) {
            manager.execute();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadUrl(String url) {
        this.webView.loadUrl(url);
    }

    private Object ExecuteJs(String method, Object... args) {
        return this.ExecuteJs(this, method, args);
    }

    private <T extends Entity> String getJavascript(T obj, String method, Object... args) {
        List<String> list = new ArrayList<>();
        list.add(obj.toJson());
        list.add("'" + method + "'");
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    list.add("'" + args[i] + "'");
                } else {
                    list.add(args[i].toString());
                }
            }
        }
        String msg = "javascript:NPMobileHelper.callMethod(" + Util.join(list.toArray(), ",") + ")";
        return msg;
    }

    public Object ExecuteJavaScripts(String code) {
//        try {
//            semp.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        webView.loadUrl(code);
        return outMsg;
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        android.util.Log.i(NetPosaMap_TAG, msg);
        if (!this.isMapavaild) {
            this.manager.push(this, "ExecuteJavaScripts", msg);
            return null;

        } else {
            return ExecuteJavaScripts(msg);
        }

    }

    /**
     * @param {number} zoom
     */
    public void SetZoom(int zoom) {

    }

    @JSONField(serialize = false)
    public int getZoom() {
        return Integer.parseInt(this.ExecuteJs("getZoom").toString());
    }


    public String getMapConfig() {
        return mapConfig;
    }

    public void setMapConfig(String mapConfig) {
        this.mapConfig = mapConfig;
    }

    /**
     * 设置地图容器
     *
     * @return {String}
     */
    public String getMapContainer() {

        return mapContainer;
    }

    /**
     * 获取地图容器
     *
     * @param {String} mapContainer
     */
    public void setMapContainer(String mapContainer) {
        this.mapContainer = mapContainer;
    }

    public void AddLayer(Layer layer) {
        this.ExecuteJs("addLayer", layer);
        layer.setMap(this);
    }

    private String outMsg;

    public Boolean parseJsonFromJs(String msg) {
        outMsg = msg;
        android.util.Log.i("LSG", outMsg);
//        semp.release();
        return true;
    }


}
