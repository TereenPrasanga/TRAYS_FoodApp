package com.example.trays_foodapp;

public class promotion {

    public String name,url,price,des;
    public String key;

    public promotion() {
    }

    public promotion(String name, String url, String price, String des, String key) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.des = des;
        this.key = key;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
