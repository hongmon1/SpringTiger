package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyRecipeRegisterActivity extends AppCompatActivity {



    Button registerBtn;
    EditText idEt,nameEt,ingreEt,descEt;

    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private static final String TAG1 = "TestDataBase";

    //private static final int REQUEST_CODE = 0;
    private static final String TAG = "blackjin";
    private Boolean isPermission = true;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    private File tempFile;
    ImageView mImageView;
    Button GalleryBtn;
    Button CameraBtn;
    String tempImg;
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

        //DB 연결
        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //레시피 등록
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Toast.makeText(MyRecipeRegisterActivity.this,"등록 완료", Toast.LENGTH_SHORT).show();
                    String result;
                    //String id = idEt.getText().toString();
                    String name = nameEt.getText().toString();
                    String ingre = ingreEt.getText().toString();
                    String desc = descEt.getText().toString();
                    Log.d(TAG1,name+ingre+desc);
                    //String user_id = "1";
                    String image = tempImg;
                    mDbOpenHelper.insertColumn(name,ingre, desc, image);
                    doWhileCursorToArray();

                    //MyRecipeActivity로 전환
                    Intent intent = new Intent(getApplicationContext(),MyRecipeActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);

                    overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
                }catch (Exception e){
                    Log.i("DBtest","Error!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        });


        String temp ="";
        //갤러리 연동
        //tedPermission();

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
        mImageView = (ImageView)findViewById(R.id.imageView);
        GalleryBtn = (Button)findViewById(R.id.btnGallery);
        GalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tedPermission();
                if(isPermission) goToAlbum();
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
                /*
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
                */
            }
        });

        //카메라 연동

        CameraBtn = (Button)findViewById(R.id.btnCamera);
        CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(MyRecipeRegisterActivity.this, Manifest.permission.CAMERA);
                if(permissionCheck == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(MyRecipeRegisterActivity.this,new String[]{Manifest.permission.CAMERA},0);
                }
                else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,PICK_FROM_CAMERA);
                }
                //tedPermission();
                //if(isPermission)  takePhoto();
                //else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        } else if (requestCode == PICK_FROM_CAMERA) {
            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();
            //setImage();

        }
    }

    /**
     *  앨범에서 이미지 가져오기
     */
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /**
     *  카메라에서 이미지 가져오기
     */
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            Uri photoUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }

    /**
     *  폴더 및 파일 만들기
     */
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "blackJin_" + timeStamp + "_";

        // 이미지가 저장될 폴더 이름 ( blackJin )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/blackJin/");
        if (!storageDir.exists()) storageDir.mkdirs();

        // 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }

    /**
     *  tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
     */
    private void setImage() {

        //ImageView imageView = findViewById(R.id.imageView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        mImageView.setImageBitmap(originalBm);
        tempImg = BitmapToString(originalBm);


        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null;

    }

    /**
     *  권한 설정
     */
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                isPermission = false;

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }


    private void doWhileCursorToArray() {

        mCursor = null;
        //DB에 있는 모든 컬럼을 가져옴
        mCursor = mDbOpenHelper.getMyrepAllColumns();
        //컬럼의 갯수 확인
        Log.i(TAG, "Count = " + mCursor.getCount());

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력

            Log.d(TAG1, mCursor.getString(mCursor.getColumnIndex("myrecipe_name")));
            CameraBtn.setText(mCursor.getString(mCursor.getColumnIndex("myrecipe_name")));
            Log.d(TAG1,mCursor.getString(mCursor.getColumnIndex("myrecipe_ingre")));
            Log.d(TAG1,mCursor.getString(mCursor.getColumnIndex("myrecipe_desc")));
            Log.d(TAG1,mCursor.getString(mCursor.getColumnIndex("myrecipe_image")));
        }
        //Cursor 닫기
        mCursor.close();
    }


    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }


}
