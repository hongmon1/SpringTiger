package com.example.howtocook_version2;

public class InfoClass {
    public int _id;
    public String name;

    public InfoClass(){}

    public InfoClass(int _id, String name){
        this._id = _id;
        this.name = name;
    }

    public int get_id(){
        return _id;
    }

    public void set_id(int _id){
        this._id = _id;
    }

    public String get_name(){
        return name;
    }

    public void set_name(String name){
        this.name = name;
    }

}
