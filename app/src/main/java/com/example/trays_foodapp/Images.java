package com.example.trays_foodapp;

import com.google.firebase.database.Exclude;

public class Images {

    private String name,url,price;
    private String mKey;


    public Images() {
    }

    public Images(String name, String url, String price, String mKey) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.mKey = mKey;
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

    @Exclude
   public String getkey(){
        return mKey;
   }

   @Exclude
   public void setkey(String key)
   {
       mKey = key;

   }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
