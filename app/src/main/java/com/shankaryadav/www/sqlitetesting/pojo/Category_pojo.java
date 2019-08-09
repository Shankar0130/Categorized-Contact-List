package com.shankaryadav.www.sqlitetesting.pojo;

public class Category_pojo {
    public int img;
    public String catename;


    public Category_pojo() {

    }

    public Category_pojo(int img, String catename) {
        this.img = img;
        this.catename = catename;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
