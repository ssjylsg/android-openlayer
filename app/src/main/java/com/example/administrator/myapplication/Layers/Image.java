package com.example.administrator.myapplication.Layers;

import com.example.administrator.myapplication.Size;

/**
 * Created by Administrator on 2016/10/26.
 */

public class Image {
    public Image(String url, Size imageSize) {
        this.url = url;
        this.imageSize = imageSize;
    }

    private String url;
    private Size imageSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Size getImageSize() {
        return imageSize;
    }

    public void setImageSize(Size imageSize) {
        this.imageSize = imageSize;
    }
}
