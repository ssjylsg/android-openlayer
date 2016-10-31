package com.example.netposa.mobileMap;

import android.webkit.WebSettings;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.netposa.jsbridge.BridgeHandler;
import com.example.netposa.jsbridge.BridgeWebView;
import com.example.netposa.jsbridge.CallBackFunction;
import com.example.netposa.jsbridge.DefaultHandler;
import com.example.netposa.mobileMap.Common.Entity;
import com.example.netposa.mobileMap.Common.Util;
import com.example.netposa.mobileMap.Events.EventCallBackArgs;
import com.example.netposa.mobileMap.Events.EventManager;
import com.example.netposa.mobileMap.Events.NPCallBackFunction;
import com.example.netposa.mobileMap.Geometry.Point;
import com.example.netposa.mobileMap.Layers.Layer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动端地图
 */
public class NetPosaMap extends Entity {
    private static final String NetPosaMap_TAG = "NetPosaMap";
    @JSONField(serialize = false)
    private BridgeWebView webView;



    private String mapConfig = "http://192.168.61.28:807/mobile/dist//mapCONFIG.JSON";
    private String mapContainer = "viewerContainer";
    private Boolean isMapavaild = false;
    private EventManager manager;
    private boolean isDebug = false;
    private String outMsg;

    /**
     * NetPosaMap 构造函数
     *
     * @param webView   webView
     * @param mapConfig 地图配置地址
     * @param mapUrl    地图地址
     */
    public NetPosaMap(BridgeWebView webView, String mapConfig, String mapUrl) {
        this.setClassName("NPMobile.Map");
        this.mapConfig = mapConfig;

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

        this.loadUrl(mapUrl);
        manager = new EventManager();

        /**
         * 注册JS 调用andriod 方法，此方法一般用于JS事件回调
         */
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

    private void loadUrl(String url) {
        this.webView.loadUrl(url);
    }

    private Object ExecuteJs(String method, Object... args) {
        return this.ExecuteJs(this, method, args);
    }

    private <T extends Entity> String getJavascript(T obj, String method, Object... args) {
        List<String> list = new ArrayList<>();
        list.add(obj.toString());
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
     * 获取地图层级
     *
     * @param zoom
     */
    public void SetZoom(int zoom) {
        this.ExecuteJs("setZoom", zoom);
    }

    /**
     * 获取地图层级
     *
     * @param callBackFunction
     */
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

    /**
     * 获取地图配置URL
     *
     * @return mapConfig
     */
    public String getMapConfig() {
        return mapConfig;
    }

    /**
     * 设置地图配置URL
     *
     * @param mapConfig
     */
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
     * @param mapContainer
     */
    public void setMapContainer(String mapContainer) {
        this.mapContainer = mapContainer;
    }

    /**
     * 新增图层
     *
     * @param layer
     */
    public void AddLayer(Layer layer) {
        this.ExecuteJs("addLayer", layer);
        layer.setMap(this);
    }

    Boolean parseJsonFromJs(String msg) {
        outMsg = msg;
        return true;
    }

    /**
     * 获取地图中心点
     *
     * @param callBackFunction
     */
    @JSONField(serialize = false)
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

    /**
     * 设置地图中心点
     *
     * @param center
     */
    @JSONField(serialize = false)
    public void setCenter(Point center) {
        this.ExecuteJs("setCenter", center);
    }

}
