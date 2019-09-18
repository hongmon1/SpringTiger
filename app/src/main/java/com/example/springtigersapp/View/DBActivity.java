package com.example.springtigersapp.View;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.springtigersapp.DB.DataBaseOpenHelper;
import com.example.springtigersapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SQLiteDatabase myDB;
        try {
            DataBaseOpenHelper dbhelper = new DataBaseOpenHelper(this);
            myDB = dbhelper.myDataBase;
            Cursor cur = myDB.rawQuery("SELECT * From db", null);
            cur.moveToFirst();
// 읽은값 출력
            Log.i("move!!!",""+cur.getString(0));
            TextView tv = (TextView)findViewById(R.id.textview);
            tv.setText(cur.getString(0));

        } catch (IOException e) {
            e.printStackTrace();
        }




   /*
        initialize(getApplicationContext());

        try{
// 'data/data'에 생성된 db파일 읽어오기
            SQLiteDatabase db = openOrCreateDatabase("test2.db", Context.MODE_PRIVATE, null);
// 쿼리로 db의 커서 획득
            Cursor cur = db.rawQuery("SELECT * From db", null);
// 처음 레코드로 이동
            cur.moveToFirst();
// 읽은값 출력
            Log.i("move!!!",""+cur.getString(0));
            TextView tv = (TextView)findViewById(R.id.textview);
            tv.setText(cur.getString(0));
        }catch(Exception e){
            Log.i("_)",""+e.toString());
        }
    }
    public static final String PACKAGE_DIR = "/data/data/com.test.db/";
    public static final String DATABASE_NAME = "tt.db";
    public static final String COPY2DATABASE_NAME = "tt2.db";
    public static void initialize(Context ctx) {
// check
        File folder = new File(PACKAGE_DIR + "databases");
        folder.mkdirs();
        File outfile = new File(PACKAGE_DIR + "databases/" + COPY2DATABASE_NAME);
        if (outfile.length() <= 0) {
            AssetManager assetManager = ctx.getResources().getAssets();
            try {
                InputStream is = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte [] tempdata = new byte[(int)filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   */
    }
}
