package com.example.administrator.myapplication;

import android.webkit.WebSettings;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.administrator.jsbridge.BridgeHandler;
import com.example.administrator.jsbridge.BridgeWebView;
import com.example.administrator.jsbridge.CallBackFunction;
import com.example.administrator.jsbridge.DefaultHandler;
import com.example.administrator.myapplication.Events.EventCallBackArgs;
import com.example.administrator.myapplication.Events.EventManager;
import com.example.administrator.myapplication.Layers.Layer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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


    private String mapConfig = "http://192.168.61.28:807/mobile/dist//mapCONFIG.JSON";
    private String mapContainer = "viewerContainer";
    private Boolean isMapavaild = false;
    private EventManager manager;
    private boolean isDebug = false;

    public NetPosaMap(BridgeWebView webView) {
        this.setClassName("NPMobile.Map");
        this.jsObject = new JsObject(this);
        WebSettings webSettings = webView.getSettings();

        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webView.requestFocus();

        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebViewClient(new NPWebViewClient(webView, this));
        webView.setWebChromeClient(new SimpleJavaJSWebChromeClient(this));

        this.webView = webView;
        this.loadUrl(isDebug ? "http://192.168.61.28:807/mobile/dist/index.html" : "http://192.168.61.28:807/mobile/dist/index_c.html");
        manager = new EventManager();

        webView.registerHandler("NPMobileHelper.Event.Call", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                EventCallBackArgs e = com.alibaba.fastjson.JSON.parseObject(data, EventCallBackArgs.class);
                if (e != null) {
                    Entity entity = Util.getEntity(e.getId());
                    entity.processEvent(e.getEventType(), e.getArgs());
                }
            }
        });
    }

    public void CreateMap() {
        if (isMapavaild) {
            return;
        }
        if (this.isDebug) {
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

    public Object ExecuteJavaScripts(String code, CallBackFunction callBack) {
        final CallBackFunction tempCallBack = callBack;
        webView.callHandler("NPMobileHelper.callMethod", code, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                if (tempCallBack != null) {
                    tempCallBack.onCallBack(data);
                }
            }
        });
        return outMsg;
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, CallBackFunction callBack, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        return ExecuteJavaScripts(msg, callBack);
    }

    public <T extends Entity> Object ExecuteJs(T obj, String method, Object... args) {
        String msg = this.getJavascript(obj, method, args);
        isMapavaild = true;
        if (!this.isMapavaild) {
            this.manager.push(this, "ExecuteJavaScripts", msg);
            return null;

        } else {
            return ExecuteJavaScripts(msg, null);
        }
    }

    /**
     * @param {number} zoom
     */
    public void SetZoom(int zoom) {
        this.ExecuteJs("setZoom", zoom);
    }

    @JSONField(serialize = false)
    public void getZoom(NPCallBackFunction<Integer> callBackFunction) {
        final NPCallBackFunction<Integer> tempcallBackFunction = callBackFunction;
        this.ExecuteJs(this, "getZoom", new CallBackFunction() {

            @Override
            public void onCallBack(String data) {
                if (tempcallBackFunction != null) {
                    tempcallBackFunction.onCallBack(Integer.parseInt(data));
                }
            }
        });
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

    Boolean parseJsonFromJs(String msg) {
        outMsg = msg;
        return true;
    }

    public void getCenter(NPCallBackFunction<Point> callBackFunction) {
        final NPCallBackFunction<Point> tempcallBackFunction = callBackFunction;
        this.ExecuteJs(this, "getCenter", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    if (tempcallBackFunction != null) {
                        tempcallBackFunction.onCallBack(new Point(jsonObject.getDouble("lon"), jsonObject.getDouble("lat")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
