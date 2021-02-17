package com.example.howtocook_version2;

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
        checkPermission();

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
    }

    private void captureCamera(){
        //

        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                File photoFile = null;
                try{
                    photoFile = createImageFile();
                }
                catch(IOException e){

                }
                if(photoFile!= null){
                    photoURI = FileProvider.getUriForFile(this,"SpringTiger",photoFile);

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }

    }

    private File createImageFile() throws IOException{
        //Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp+".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pathvalue/"+imageFileName);

        //Save a file:path for use with ACTION_VIEW intents
        mCurrentPhotoPath = storageDir.getAbsolutePath();
        return storageDir;
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

    private boolean checkPermission() {
        //사용자가 퍼미션 거부를 한 적 있는 경우
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            //사용자에게 이유 설명
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한은 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용해주세요.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:com.shuvic.alumni.andokdcapp"));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2001);
            }
        } else {
            return true;
        }
        return true;
    }

}
