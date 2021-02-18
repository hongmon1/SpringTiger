package com.example.howtocook_version2;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


//http://egloos.zum.com/adamjin/v/2875879

public class RecipeDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "recipe.sqlite";
    public static final String ROOT_DIR = "/data/data/com.example.howtocook_version2";
    private static final int SCHEMA_VERSION = 1;

    public RecipeDBOpenHelper(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_VERSION);
        setDB(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public static void setDB(Context ctx){
        File folder = new File(ROOT_DIR);
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets(); //ctx가 없으면 assets폴더를 찾지 못한다.
        File outfile = new File(ROOT_DIR+"recipe.sqlite");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("recipe.sqlite", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } else {}
        } catch (IOException e) {}

    }
}
