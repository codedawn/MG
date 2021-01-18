package com.codedawn.mg.entity;

import java.io.Serializable;

/**
 * @author codedawn
 * @date 2021-01-17 21:47
 */
public class EduVideo implements Serializable {
    private int icon;
    private String title;

    public String url;

    public EduVideo() {
    }

    public int getIcon() {
        return icon;
    }

    public EduVideo setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EduVideo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public EduVideo setUrl(String url) {
        this.url = url;
        return this;
    }
}
