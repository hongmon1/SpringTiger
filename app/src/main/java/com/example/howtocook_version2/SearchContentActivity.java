package com.example.howtocook_version2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchContentActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;


    private DBOpenHelper mDbOpenHelper;

    private Cursor vCursor;

    Intent it2 ;
    private String recipe_id;
    private String cook_id;
    private String cook_img;
    private String cook_name;
    private String cook_ingre;
    private String cook_desc;
    private String cook_com;
    private boolean check=false;

    ImageView vImgage;
    TextView vName;
    TextView vIngre;
    TextView vDesc;
    TextView vCom;
    Button fav_add_btn;

    private static final String TAG1 = "TestDataBase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view_main);
/*
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
        */


        it2 = this.getIntent();
        recipe_id = it2.getExtras().getString("it_idRecipe");
        Log.d("id","recipe id 는 "+recipe_id);

        vImgage = (ImageView) findViewById(R.id.imageView);
        vName = (TextView) findViewById(R.id.register_name);
        vIngre = (TextView) findViewById(R.id.register_ingre);
        vDesc = (TextView) findViewById(R.id.register_desc);
        fav_add_btn = (Button) findViewById(R.id.fav_add_btn);

        if(recipe_id.equals("null")){
            vName.setText("해당 요리레시피 없음");
        }
        else {



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

                    vName.setText(cook_name);
                    vIngre.setText(cook_ingre);
                    vDesc.setText(cook_desc);
                    vImgage.setImageBitmap(StringToBitmap(cook_img));
                    //vCom = (TextView)findViewById(R.id.register_com);
                    //imageView.setImageBitmap(StringToBitmap(img));
                    break;
                }
            }


            vCursor.close();
            check = false;

            fav_add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check == false) {
                        mDbOpenHelper.insertColumn(Integer.parseInt(recipe_id));
                        Toast.makeText(getApplicationContext(), "즐겨찾기 추가", Toast.LENGTH_SHORT).show();
                        check = true;
                    } else {
                        mDbOpenHelper.deleteFavColumn(Integer.parseInt(recipe_id), "dd");
                        Toast.makeText(getApplicationContext(), "즐겨찾기 취소", Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                }
            });

        }
    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
