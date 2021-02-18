package com.example.howtocook_version2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FavoriteContentActivity extends AppCompatActivity {
    Button home_btn, fav_btn, myrep_btn;

    static Bitmap imgBitmap = null;
    static HttpURLConnection conn = null;
    static BufferedInputStream bis = null;

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

    ImageView vImgage;
    TextView vName;
    TextView vIngre;
    TextView vDesc;
    TextView vCom;

    private ArrayList<ListViewItem> alist;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private ListViewItem item;

    private static final String TAG1 = "TestDataBase";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
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

        vImgage = (ImageView)findViewById(R.id.imageView);
        vName = (TextView)findViewById(R.id.register_name);
        vIngre = (TextView)findViewById(R.id.register_ingre);
        vDesc = (TextView)findViewById(R.id.register_desc);

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



    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }

    //URL(string으로 주어짐)을 Bitmap으로 변환
    public static Bitmap StringToBitmap(String img) {
        final String imageURL = img;

        try{
            AsyncTask<String,Void,Bitmap> asyncTask = new AsyncTask<String, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... strings) {
                    try
                    {
                        Log.d("testImg","in translate");
                        URL url = new URL(imageURL);
                        conn = (HttpURLConnection)url.openConnection();
                        conn.connect();

                        int nSize = conn.getContentLength();
                        bis = new BufferedInputStream(conn.getInputStream(), nSize);
                        imgBitmap = BitmapFactory.decodeStream(bis);
                        Log.d("testImg",imgBitmap.toString());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    } finally{
                        if(bis != null) {
                            try {bis.close();} catch (IOException e) {}
                        }
                        if(conn != null ) {
                            conn.disconnect();
                        }
                    }
                    return  imgBitmap;
                }
            };

            Bitmap bm = asyncTask.execute(imageURL).get();
        }catch (Exception e){

        }

        return imgBitmap;

    }


}
