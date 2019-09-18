package com.example.springtigersapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    //SQLiteDatabase - 데이터배이스 입력,삭제,수정을 위한 클래스
    public static SQLiteDatabase mDB;
    //SQLiteOpenHelper 상속
    //데이터베이스를 만들거나 열기 위해 필요한 일들을 도와주는 역할
    //이미 배포된 앱의 DB를 업그레이드 할 시 유용
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        //context : DB를 생성하는 Context.보통은 Main activity
        //name, version : DB 파일 이름 및 버전 - DB 생성 및 업데이트 시 사용
        //factory : SQLiteDatabase.CursorFactory를 구현한 객체여야 함
        //사용자가 직접 커서 객체 생성
        //표준 커서 이용시 null로 지정
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //데이터베이스가 처음 생성될 때 호출
        //테이블 생성&초기 레코드 삽입 작업
        //다른 테이블 명칭 추가 작성 시 하나의 데이터베이스에서 여러 테이블 생성 가능
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataBases.CreateDB._CREATE0);
        }

        //버전 업그레이드 시 사용
        //이전 버전 지우고 새버전 생성
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    //데이터베이스 열어서 사용할 수 있도록 해줌
    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        //getWritableDatabase() 는 데이터베이스를 읽고 쓸 수 있도록 해줌
        mDB = mDBHelper.getWritableDatabase();
        return null;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    //데이터베이스를 닫는다
    //모두 사용한 후에는 가급적이면 닫아주는 것이 좋다
    public void close(){
        mDB.close();
    }

    //데이터베이스에 사용자가 입력한 값을 insert
    public long insertColumn(String userid, String name, long age, String gender){
        //ContentValues 클래스 - ContentResolver가 처리할 수 있는 값 집합을 저장
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.USERID, userid);
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.AGE, age);
        values.put(DataBases.CreateDB.GENDER, gender);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    //데이터 선택 - 메소드 이용해 특정 행 찾기 가능
    public Cursor selectColumn(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null,null, null, null, null);
    }
    //지정된 column을 기준으로 정렬
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }

    //update 데이터 갱신
    //갱신하고자 하는 특정 행의  데이터만 업데이트 -> _ID입력 필요
    public boolean updateColumn(long id, String userid, String name, long age, String gender){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.USERID, userid);
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.AGE, age);
        values.put(DataBases.CreateDB.GENDER, gender);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id="+id, null)>0;
    }

    //Delete all
    public void deleteAllColumns(){
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    //Delete column
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id,null)>0;
    }
}
