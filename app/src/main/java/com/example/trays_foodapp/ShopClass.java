package com.example.trays_foodapp;

import com.google.firebase.database.Exclude;

public class ShopClass
{
    private String name,location,telephone,image;
    private String mKey;

    public ShopClass() {
    }

    public ShopClass(String name, String location, String telephone, String image) {
        this.name = name;
        this.location = location;
        this.telephone = telephone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Exclude
    public String getmKey() {
        return mKey;
    }
    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
