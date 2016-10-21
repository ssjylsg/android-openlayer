package com.example.administrator.myapplication;

import android.util.Log;
import android.webkit.WebSettings;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.administrator.jsbridge.BridgeHandler;
import com.example.administrator.jsbridge.BridgeWebView;
import com.example.administrator.jsbridge.CallBackFunction;
import com.example.administrator.jsbridge.DefaultHandler;
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
    private BridgeWebView webView;
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

    public NetPosaMap(BridgeWebView webView) {
        this.setClassName("NPMobile.Map");
        this.jsObject = new JsObject(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebViewClient(new NPWebViewClient(webView, this));
        webView.setWebChromeClient(new SimpleJavaJSWebChromeClient(this));

        this.webView = webView;
        this.loadUrl("http://192.168.61.28:807/mobile/dist/index_c.html");
        manager = new EventManager();

        webView.registerHandler("NPMobileHelper.Event.Call", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Util.Info("JSCALLBACK",data);
            }
        });
    }

    public void CreateMap() {
        if (isMapavaild) {
            return;
        }
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
        String msg = Util.join(list.toArray(), ",");
        return msg;
    }

    public Object ExecuteJavaScripts(String code) {
        webView.callHandler("NPMobileHelper.callMethod", code, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
             Util.Info("onCallBack", data);
            }
        });
        return outMsg;
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        isMapavaild = true;
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
        this.ExecuteJs("setZoom", zoom);
    }

    @JSONField(serialize = false)
    public void getZoom(NPCallBackFunction<Point> callBackFunction) {
        this.ExecuteJs("getZoom");
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
        return true;
    }


}
