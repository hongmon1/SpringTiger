package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;


    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;

    private String recipe_id;
    private String cook_img;
    private String cook_name;
    private String cook_id;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //action bar 없애기
        getSupportActionBar().hide();


        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);

        //하단 탭 버튼 세팅
        fav_btn.setEnabled(false);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });
        myrep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });



        // favorite_recipe_id 가져오기

        alist = new ArrayList<ListViewItem>();
        listViewAdapter = new ListViewAdapter(this,alist);
        listView = findViewById(R.id.listview);
        listView.setAdapter(listViewAdapter);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

        mCursor = null;
        mCursor = mDbOpenHelper.getFavAllColumns();
        Log.d("dbTest", "Count = " + mCursor.getCount());

        while(mCursor.moveToNext()){
            recipe_id = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
            Log.d("dbTest", recipe_id);
        }

        mCursor.close();

        //recipe 내용 가져오기
        mCursor = null;
        mCursor = mDbOpenHelper.getRepAllColumns();
        Log.d("dbTest", "Count = " + mCursor.getCount());

        while(mCursor.moveToNext()){

            cook_id = mCursor.getString(mCursor.getColumnIndex("recipe_id"));
            cook_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));

            Log.d("dbTest", cook_name);
            if(cook_id.equals(recipe_id)) {
                item = new ListViewItem(cook_img, cook_name);
                alist.add(item);
                listViewAdapter.notifyDataSetChanged();
            }
        }

        mCursor.close();

    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }
}
