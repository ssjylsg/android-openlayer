package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.jsbridge.BridgeWebView;
import com.example.administrator.myapplication.Events.EventObject;
import com.example.administrator.myapplication.Geometry.ClusterMarker;
import com.example.administrator.myapplication.Geometry.Marker;
import com.example.administrator.myapplication.Geometry.MarkerOptions;
import com.example.administrator.myapplication.Layers.ClusterLayer;
import com.example.administrator.myapplication.Layers.ClusterLayerOptions;
import com.example.administrator.myapplication.Layers.CustomerLayer;
import com.example.administrator.myapplication.Layers.Image;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private NetPosaMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BridgeWebView webView = (BridgeWebView) findViewById(R.id.webView);

        map = new NetPosaMap(webView);

        CustomerLayer layer = new CustomerLayer("测试");
        map.AddLayer(layer);

        Marker marker = new Marker(new Point(116.37948369818618, 39.871976142236186), new MarkerOptions(
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
        for (Integer i = 0; i < 100; i++) {
            markers.add(new ClusterMarker(new Point(lon + Math.random() * Math.pow(-1, i) * 0.1, lat + Math.random() * Math.pow(-1, i + 1) * 0.1)));
        }

        clusterLayer.addClusterMarkers(markers.toArray(new ClusterMarker[]{}));
        clusterLayer.addEventListener("click", new NPEventListener<ClusterMarker>() {
            @Override
            public void processEvent(EventObject<ClusterMarker> sender, EventArgs e) {
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
                sender.getSource().changeStyle(new MarkerOptions("img/Flag.png", 21.0, 25.0));
            }
        });

    }
}
