package com.chocksaway.entity;

/**
 * Author milesd on 20/09/2016.
 */
public class Site {
    private String name;
    private String url;

    public Site(String name, String url) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + "," + getUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
