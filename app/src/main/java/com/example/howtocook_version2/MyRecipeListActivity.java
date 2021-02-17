package com.example.howtocook_version2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyRecipeListActivity extends AppCompatActivity {

    private Button home_btn, fav_btn, myrep_btn;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;

    private String cook_img;
    private String cook_name;

    private ListViewItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_my_recipe_list);

        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);

        alist = new ArrayList<ListViewItem>();
        listViewAdapter = new ListViewAdapter(this,alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

        myrep_btn.setEnabled(false);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });

        mCursor = null;
        mCursor = mDbOpenHelper.getMyrepAllColumns();

        while(mCursor.moveToNext()){
            cook_name = mCursor.getString(mCursor.getColumnIndex("myrecipe_name"));
            cook_img = mCursor.getString(mCursor.getColumnIndex("myrecipe_img"));

            item = new ListViewItem(cook_img,cook_name);
            listViewAdapter.notifyDataSetChanged();
        }

        mCursor.close();
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }
}
