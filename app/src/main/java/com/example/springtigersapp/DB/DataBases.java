package com.example.springtigersapp.DB;

import android.provider.BaseColumns;

//Scheme 정의 클래스
//BaseColumns 인터페이스 구현
//final class - 상속 불가
public final class DataBases {
    public static final class CreateDB implements BaseColumns{
        //테이블 명칭 :
        //column : userid, name, age, gender
        //not null을 통해 null값 입력 방지
        //_ID는 integer의 형태로 생성되는 구분자
        //중복되는 값 생성x -> 행을 추가할 떄 마다 숫자 1씩 증가하여 _ID에 입력
        public static final String USERID = "userid";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+ _TABLENAME0+"("
                +_ID+" integer primary key autoincrement,"
                +USERID+" text not null , "
                +NAME+" text not null , "
                +AGE+" integer not null , "
                +GENDER+" text not null );";
    }
}