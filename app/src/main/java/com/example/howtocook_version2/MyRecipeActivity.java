package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class MyRecipeActivity extends AppCompatActivity {

    private DBOpenHelper mDBOpenHelper;
    private ListViewItem item;

    private ImageView imageView;
    private TextView register_name, register_ingre, register_desc;

    private String c_name;
    private String name, ingre, desc, img;

    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);


        getSupportActionBar().hide();

        Intent intent = getIntent();

        c_name = intent.getExtras().getString("name");

        mDBOpenHelper = new DBOpenHelper(this);
        mDBOpenHelper.open();

        register_name = findViewById(R.id.register_name);
        register_ingre = findViewById(R.id.register_ingre);
        register_desc = findViewById(R.id.register_desc);
        imageView = findViewById(R.id.imageView);

        mCursor = null;
        mCursor = mDBOpenHelper.getMyrepAllColumns();

        //intent로 받아온 name으로 myrep 검색, 결과 뿌려줌
        while(mCursor.moveToNext()){
            if(mCursor.getString(mCursor.getColumnIndex("myrecipe_name")).equals(c_name)){
                name = mCursor.getString(mCursor.getColumnIndex("myrecipe_name"));
                ingre = mCursor.getString(mCursor.getColumnIndex("myrecipe_ingre"));
                desc = mCursor.getString(mCursor.getColumnIndex("myrecipe_desc"));
                img = mCursor.getString(mCursor.getColumnIndex("myrecipe_image"));

                register_name.setText(name);
                register_ingre.setText(ingre);
                register_desc.setText(desc);
                imageView.setImageBitmap(StringToBitmap(img));
            }
        }
    }

    //String을 Bitmap으로 변환
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
