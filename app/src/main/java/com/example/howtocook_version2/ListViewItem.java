package com.example.howtocook_version2;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;

public class ListViewItem {
    private Drawable img;
    private String name;

    public ListViewItem(Drawable img, String name){
        this.img = img;
        this.name = name;
    }

    public void setImg(Drawable img){
        this.img = img;
    }
    public void setName(String name){
        this.name = name;
    }

    public Drawable getImg(){
        return this.img;
    }

    public String getName(){
        return this.name;
    }


}
