package com.example.howtocook_version2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

class Test_Activity extends AppCompatActivity {

    ImageButton button_food;
    AutoCompleteTextView autoFoodName;
    private List<String> list;

    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    String food_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button_food = (ImageButton)findViewById(R.id.btn_food);
        autoFoodName = (AutoCompleteTextView)findViewById(R.id.autoFoodName);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();

        for(int i = 15 ; i <30; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","comment","empty image");
        }

        list = new ArrayList<String>();
        settingList(list);

        autoFoodName.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        button_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Test_Activity.this, SearchContentActivity.class);
                intent.putExtra("it_idRecipe", autoFoodName.getText().toString());
                startActivity(intent);

            }
        });

    }

    private void settingList(List<String> list){
        mCursor = mDbOpenHelper.getRepAllColumns();

        while(mCursor.moveToNext()){

            //cook_id = mCursor.getString(mCursor.getColumnIndex("_id"));
            food_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            list.add(food_name);
            //cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));

            //Log.d("dbTest", cook_name);

        }
        mCursor.close();

    }

}
