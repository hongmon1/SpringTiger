package com.example.howtocook_version2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//https://tony-programming.tistory.com/entry/Android-데이터베이스-사용법
//참고한 사이트

public class DBOpenHelper {
    private static final String DATABASE_NAME="omona.db";
    private static final int DATABASE_VERSION = 0;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{
        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Databases.CreateFavorite._CREATE);
            db.execSQL(Databases.CreateMyrep._CREATE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Databases.CreateFavorite._TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS "+Databases.CreateMyrep._TABLENAME);
            onCreate(db);
        }

    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    //DB open
    public DBOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx,DATABASE_NAME,null,DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    //DB close
    public void close(){
        mDB.close();
    }

    //favorite table에 column 삽입
    public long insertColumn(int recipe_id){
        ContentValues values = new ContentValues();
        values.put(Databases.CreateFavorite.RECIPE_ID, recipe_id);
        return mDB.insert(Databases.CreateFavorite._TABLENAME,null,values);

    }

    //myrecipe table에 column 삽입
    public long insertColumn(String name, String ingre, String desc, String image){
        ContentValues values = new ContentValues();
        values.put(Databases.CreateMyrep.MYRECIPE_NAME,name);
        values.put(Databases.CreateMyrep.MYRECIPE_INGRE,ingre);
        values.put(Databases.CreateMyrep.MYRECIPE_DESC,desc);
        values.put(Databases.CreateMyrep.MYRECIPE_IMAGE,image);
        return mDB.insert(Databases.CreateMyrep._TABLENAME,null,values);
    }

    //id로 Favorite table에서 삭제
    public boolean deleteFavColumn(long id){
        return mDB.delete(Databases.CreateFavorite._TABLENAME,"_id="+id,null)>0;
    }

    //id로 Myrecip table에서 삭제
    public boolean deleteMyrepColumn(long id){
        return mDB.delete(Databases.CreateMyrep._TABLENAME,"_id="+id,null)>0;
    }

    //모든 컬럼 가져옴
    //커서 전체 선택
    public Cursor getFavAllColumns(){
        return mDB.query(Databases.CreateFavorite._TABLENAME,null,null,null,null,null,null);
    }

    //모든 컬럼 가져옴
    //커서 전체 선택
    public Cursor getMyrepAllColumns(){
        return mDB.query(Databases.CreateFavorite._TABLENAME,null,null,null,null,null,null);
    }


}