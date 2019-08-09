package com.shankaryadav.www.sqlitetesting.pojo;

import android.net.Uri;

public class Contacts_pojo {
    public String no;
    public String name;
    public Uri imgurl;


    public Contacts_pojo() {

    }

    public Contacts_pojo(String no, String name, Uri imgurl) {
        this.no = no;
        this.name = name;
        this.imgurl = imgurl;
    }


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImgurl() {
        return imgurl;
    }

    public void setImgurl(Uri imgurl) {
        this.imgurl = imgurl;
    }
}
