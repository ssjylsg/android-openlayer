package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.jsbridge.BridgeWebView;
import com.example.administrator.myapplication.Geometry.Marker;
import com.example.administrator.myapplication.Geometry.MarkerOptions;
import com.example.administrator.myapplication.Layers.CustomerLayer;

import java.util.EventObject;

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
                        Util.Info("JSCALLBAKC","hELLOW");
                    }
                }
        );


    }
}
