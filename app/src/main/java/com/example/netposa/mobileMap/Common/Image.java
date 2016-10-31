package com.example.netposa.mobileMap.Common;

/**
 * 图片
 */

public class Image {
    private String url;
    private Size imageSize;

    /**
     * 图片实体类
     * @param url 图片路径
     * @param imageSize 图片大小
     */
    public Image(String url, Size imageSize) {
        this.url = url;
        this.imageSize = imageSize;
    }

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
