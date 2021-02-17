package com.example.howtocook_version2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText idEt,nameEt,ingreEt,descEt;

    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;

    private Button GalleryBtn, CameraBtn;
    private ImageView mImageView;
    String tempImg;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    static final int REQUEST_TAKE_PHOTO = 2001;

    String mCurrentPhotoPath;
    Uri photoURI, albumURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);


        //action bar 없애기
        getSupportActionBar().hide();


        registerBtn=(Button)findViewById(R.id.register_btn);
        idEt=(EditText)findViewById(R.id.register_id);
        nameEt=(EditText)findViewById(R.id.register_name);
        ingreEt=(EditText)findViewById(R.id.register_ingre);
        descEt =(EditText)findViewById(R.id.register_desc);

        mImageView = (ImageView)findViewById(R.id.imageView);
        GalleryBtn = (Button)findViewById(R.id.btnGallery);
        CameraBtn = findViewById(R.id.btnCamera);

        //DB 연결
        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //앨범용 권한 확인
        String temp = "";
        //파일 읽기 권한 확인
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE+" ";
        }

        //권한 요청
        if(TextUtils.isEmpty(temp)== false){
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }
        else{
            Toast.makeText(this,"권한 모두 허용", Toast.LENGTH_SHORT).show();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {} else {  ActivityCompat.requestPermissions(TestActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1); } }


        //새 레시피 등록
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerRecipe();
            }
        });

        //앨범에서 사진 가져오기
        GalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goToAlbum();
            }
        });

        CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,PICK_FROM_CAMERA);

            }
        });
    }

    // 권한 요청
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { super.onRequestPermissionsResult(requestCode, permissions, grantResults); if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ) { } }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case PICK_FROM_ALBUM :
                break;
            case PICK_FROM_CAMERA:
                if(resultCode == RESULT_OK && data.hasExtra("data")){
                    Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                    if(bitmap!=null){
                        mImageView.setImageBitmap(bitmap);
                        tempImg = BitmapToString(bitmap);
                    }
                }
                break;
        }
    }




    private void registerRecipe(){
        try{
            Toast.makeText(TestActivity.this,"등록 완료", Toast.LENGTH_SHORT).show();
            String result;
            //String id = idEt.getText().toString();
            String name = nameEt.getText().toString();
            String ingre = ingreEt.getText().toString();
            String desc = descEt.getText().toString();

            //String user_id = "1";
            String image = tempImg;
            mDbOpenHelper.insertColumn(name,ingre, desc, image);
            //doWhileCursorToArray();

            //MyRecipeActivity로 전환
            Intent intent = new Intent(getApplicationContext(),MyRecipeActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);

            overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
        }catch (Exception e){
            Log.i("DBtest","Error!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }



    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }


}
