package com.example.howtocook_version2;

import android.provider.BaseColumns;

public final class Databases {

    //즐겨찾기 테이블
    public static final class CreateFavorite implements BaseColumns{
        public static final String _TABLENAME = "favorite";
        public static final String RECIPE_ID = "recipe_id";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +RECIPE_ID+" integer not null );";
    }

    //내 레시피 테이블
    public static final class CreateMyrep implements BaseColumns{
        public static final String _TABLENAME = "myrecipe";
        public static final String MYRECIPE_NAME = "myrecipe_name";
        public static final String MYRECIPE_INGRE = "myrecipe_ingre";
        public static final String MYRECIPE_DESC = "myrecipe_desc";
        public static final String MYRECIPE_IMAGE = "myrecipe_image";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +MYRECIPE_NAME+" text not null, "
                        +MYRECIPE_INGRE+" text not null, "
                        +MYRECIPE_DESC+" text not null, "
                        +MYRECIPE_IMAGE+" char(50) );";
    }

    //레시피테이블
    public static final class CreateRep implements BaseColumns{
        public static final String _TALBLENAME = "recipe";
        public static final String RECIPE_NAME = "recipe_name";
        public static final String RECIPE_DESC = "recipe_desc";
        public static final String RECIPE_COM = "recipe_com";

        public static final String _CREATE =
                "create table "+_TALBLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +RECIPE_NAME+" text not null), "
                        +RECIPE_DESC+" text not null, "
                        +RECIPE_COM+" text not null );";


    }

}
