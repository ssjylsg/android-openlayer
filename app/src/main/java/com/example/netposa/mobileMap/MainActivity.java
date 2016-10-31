package com.example.netposa.mobileMap;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.netposa.jsbridge.BridgeWebView;
import com.example.netposa.mobileMap.Common.Image;
import com.example.netposa.mobileMap.Common.Size;
import com.example.netposa.mobileMap.Common.Util;
import com.example.netposa.mobileMap.Events.EventArgs;
import com.example.netposa.mobileMap.Events.EventObject;
import com.example.netposa.mobileMap.Events.NPCallBackFunction;
import com.example.netposa.mobileMap.Events.NPEventListener;
import com.example.netposa.mobileMap.Geometry.ClusterMarker;
import com.example.netposa.mobileMap.Geometry.Marker;
import com.example.netposa.mobileMap.Geometry.MarkerStyle;
import com.example.netposa.mobileMap.Geometry.Point;
import com.example.netposa.mobileMap.Layers.ClusterLayer;
import com.example.netposa.mobileMap.Layers.ClusterLayerOptions;
import com.example.netposa.mobileMap.Layers.CustomerLayer;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private NetPosaMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);

        map = new NetPosaMap(webView, "http://192.168.61.28:807/mobile/dist//mapCONFIG.JSON", "http://192.168.61.28:807/mobile/dist/index_c.html");

        CustomerLayer layer = new CustomerLayer("测试");
        map.AddLayer(layer);

        Marker marker = new Marker(new Point(116.37948369818618, 39.871976142236186), new MarkerStyle(
                "img/marker.png", 21.0, 25.0
        ));

        layer.addOverlay(marker);

        marker.addEventListener("click", new NPEventListener() {
                    @Override
                    public void processEvent(EventObject sender, EventArgs e) {
                        Util.Info("JSCALLBAKC", "hELLOW");
                    }
                }
        );
        ClusterLayerOptions options = new ClusterLayerOptions();
        options.setClusterImage(new Image("img/Flag.png", new Size(32, 32)));
        options.setSingleImage(new Image("img/marker.png", new Size(21, 25)));
        ClusterLayer clusterLayer = new ClusterLayer("聚合图层测试", options);
        this.map.AddLayer(clusterLayer);
        double lon = 116.3427702718185;
        double lat = 39.89369592052587;

        ArrayList<ClusterMarker> markers = new ArrayList<ClusterMarker>();
        for (Integer i = 0; i < 1000; i++) {
            markers.add(new ClusterMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1, lat + Math.random() * Math.pow(-1, i + 1) * 0.1)));
        }


        clusterLayer.addClusterMarkers(markers.toArray(new ClusterMarker[]{}));


        clusterLayer.addEventListener("click", new NPEventListener<ClusterMarker>() {
            @Override
            public void processEvent(EventObject<ClusterMarker> sender, EventArgs e) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("聚合事件").setMessage(sender.getSource().getPoint().toString()).show();

                map.getZoom(new NPCallBackFunction<Integer>() {
                    @Override
                    public void onCallBack(Integer data) {
                        Util.Info("getZoom", "ok");
                    }
                });
                map.getCenter(new NPCallBackFunction<Point>() {
                    @Override
                    public void onCallBack(Point data) {

                    }
                });
                map.setCenter(sender.getSource().getPoint());
                sender.getSource().changeStyle(new MarkerStyle("img/Flag.png", 21.0, 25.0));
            }
        });

    }
}
