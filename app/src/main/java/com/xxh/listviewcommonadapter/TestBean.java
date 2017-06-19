package com.xxh.listviewcommonadapter;

/**
 * Created by 解晓辉 on 2017/6/19.
 * 作用：
 */

public class TestBean {
    private int imageId;
    private String content;

    public TestBean() {
    }

    public TestBean(int imageId, String content) {
        this.imageId = imageId;
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
