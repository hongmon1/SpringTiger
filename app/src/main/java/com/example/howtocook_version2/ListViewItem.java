package com.example.howtocook_version2;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;

public class ListViewItem {
    private String img;
    private String name;

    public ListViewItem(String img, String name){
        this.img = img;
        this.name = name;
    }

    public void setImg(String img){
        this.img = img;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getImg(){
        return this.img;
    }

    public String getName(){
        return this.name;
    }


}
