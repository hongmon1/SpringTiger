package com.example.howtocook_version2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavoriteContentActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;


    private DBOpenHelper mDbOpenHelper;

    private Cursor vCursor;

    private String recipe_id;
    private String cook_id;
    private String cook_img;
    private String cook_name;
    private String cook_ingre;
    private String cook_desc;
    private String cook_com;


    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    private static final String TAG1 = "TestDataBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        myrep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });


        // favorite_recipe_id 가져오기
        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();


        vCursor = null;
        vCursor = mDbOpenHelper.getRepAllColumns();

        while (vCursor.moveToNext()) {

            cook_id = vCursor.getString(vCursor.getColumnIndex("_id"));


            //Log.d("dbTest", cook_name);
            if (cook_id.equals(recipe_id)) {
                cook_name = vCursor.getString(vCursor.getColumnIndex("recipe_name"));
                cook_ingre = vCursor.getString(vCursor.getColumnIndex("recipe_ingre"));
                cook_desc = vCursor.getString(vCursor.getColumnIndex("recipe_desc"));
                cook_com = vCursor.getString(vCursor.getColumnIndex("recipe_com"));
                cook_img = vCursor.getString(vCursor.getColumnIndex("recipe_image"));


            }
        }


        vCursor.close();

    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }


}
