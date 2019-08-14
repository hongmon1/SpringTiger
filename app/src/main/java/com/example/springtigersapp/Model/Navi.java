package com.example.springtigersapp.Model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.springtigersapp.R;
import com.google.android.material.snackbar.Snackbar;

public class Navi extends AppCompatActivity {
    EditText editText1, editText2;
    Button button8;

    LocationManager locationManager;
    Location location;

    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        button8 = (Button) findViewById(R.id.button8);


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  checkLocationService(v);    //핸드폰 gps키기
                startLocationService(v);    //앱의 위치서비스 제공 허락
            }
        });
    }


    public void startLocationService(View v) {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //FINE는 gps와 네트워크를 사용, COARSE는 네트워크만 사용(FINE가 더 정확)
        //checkSelfPermission()은 권한이 있는지 없는지 확인한다.
        //권한이 있다면 permission_granted를
        //없다면 permission_denied를 반환한다.
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION);

        //해당 권한이 거절상태라면
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //사용자가 권한요청을 이미 거절한적이 있는 경우에만 설명을 제공하기 위해
            //shouldShowRequestPermissionRationale()메소드를 사용한다.
            //거부내역이 있으면 true, 없으면 false, 다시보지않기 후 거절은 false를 리턴

            //사용자가 권한요청을 거절한 경우가 있다면
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //사용자에게 권한이 필요한 이유를 설명해주고
                //세번째 인자는 유지시간으로 int형이라 따로 숫자를 넣어도 된다.
                Snackbar.make(v, "위치권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //ok버튼이 눌리면 사용자에게 권한요청을 한다.
                        //결과는 onRequestPermissionResult에서 수신된다.
                        ActivityCompat.requestPermissions(Navi.this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                        }, 0);
                    }
                }).show();
            }
            //사용자가 권한거부를 한적이 없는 경우(즉 최초의 앱)에는 권한요청을 바로하고
            //요청결과는 onRequestPermissionResult에서 수신된다.
            else {
                Snackbar.make(v, "위치설정을 사용할 수 없습니다.",
                        Snackbar.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 0);
            }
            //해당 권한이 승인상태라면
        } else {
            Snackbar.make(v, "위치권한승인확인", Snackbar.LENGTH_SHORT).show();
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Snackbar.make(v, "위치가 뜹니다", Snackbar.LENGTH_SHORT).show();
            }else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                Snackbar.make(v, "위치가 뜹니다", Snackbar.LENGTH_SHORT).show();

            }
        }

        //위치 정보 업데이트를 위해 새로 정의한 gps리스터 객체를 생성해주자
        GPSListener gpsLIstener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,
                minTime,minDistance,gpsLIstener);
    }

    private class GPSListener implements LocationListener {
        public void onLocationChanged(Location location){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            editText1.setText(latitude + "," + longitude);
        }
        public void onProviderDisabled(String provider){}

        public void onProviderEnabled(String provider){}

        public void onStatusChanged(String provider, int status, Bundle extras){}
    }


    //이 메소드는 사용자가 대화상자에서 어떤 선택을 했는지 체크한다.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case 0: {
                //권한요청이 승낙되면 기능을 시작한다.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(view, "권한이 승인되었습니다", Snackbar.LENGTH_SHORT).show();
                //    startLocationService();
                } else {     //권한요청이 거절된다면
                    Snackbar.make(view, "권한이 승인되지 못합니다", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }
}
