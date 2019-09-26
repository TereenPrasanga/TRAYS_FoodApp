package com.example.trays_foodapp;

public class Orders {

    private String pid,date,time,pname,price,contactno,uname;

    public Orders() {
    }

    public Orders(String pid, String date, String time, String pname, String price, String contactno, String uname) {
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.pname = pname;
        this.price = price;
        this.contactno = contactno;
        this.uname = uname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
