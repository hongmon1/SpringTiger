package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyRecipeActivity extends AppCompatActivity {

    private DBOpenHelper mDbOpenHelper;
    private ListViewItem item;

    private ImageView imageView;
    private EditText register_name, register_ingre, register_desc;

    private String C_name;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);

        getSupportActionBar().hide();

        Intent intent = getIntent();

        C_name = intent.getExtras().getString("name");

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

        mCursor = null;
        mCursor = mDbOpenHelper.getMyrepAllColumns();

        while(mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex("name")).equals(C_name)){


            }
        }
    }
}
