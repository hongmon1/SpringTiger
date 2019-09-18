package com.example.springtigersapp.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.springtigersapp.Model.Task;
import com.example.springtigersapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kr.go.seoul.airquality.AirQualityTypeMini;

public class WeatherActivity extends AppCompatActivity {

    private List<String> list;
    Button btn;
    TextView tv_parsing;
    AutoCompleteTextView autoGuName;
    com.example.test_ver2.Weather weather = com.example.test_ver2.Weather.getInstance();
    AirQualityTypeMini mini;
    String openAPIKey = "704f6576557968653132314f644a6f56";
    String resultText = "결과";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();

        settingList();

        autoGuName = (AutoCompleteTextView)findViewById(R.id.autoGuName);
        btn = (Button)findViewById(R.id.btn);
        tv_parsing = (TextView)findViewById(R.id.tv_pop);
        mini = (AirQualityTypeMini)findViewById(R.id.mini);
        mini.setOpenAPIKey(openAPIKey);
        autoGuName.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));


        try{
            resultText = new Task().execute().get();
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        tv_parsing.setText(resultText);
    }

    private void settingList(){
        list.add("종로구");
        list.add("중구");
        list.add("용산구");
        list.add("성동구");
        list.add("광진구");
        list.add("동대문구");
        list.add("중랑구");
        list.add("성북구");
        list.add("강북구");
        list.add("도봉구");
        list.add("노원구");
        list.add("은평구");
        list.add("서대문구");
        list.add("마포구");
        list.add("양천구");
        list.add("강서구");
        list.add("구로구");
        list.add("금천구");
        list.add("영등포구");
        list.add("동작구");
        list.add("관악구");
        list.add("서초구");
        list.add("강남구");
        list.add("송파구");
        list.add("강동구");
    }

    public void guWeatherSearch(View view){
        switch(autoGuName.getText().toString()){
            case "종로구":
            case "중구":
                weather.setX(60);
                weather.setY(127);
                break;

            case "용산구":
                weather.setX(60);
                weather.setY(126);
                break;

            case "성동구":
            case "동대문구":
            case "성북구":
                weather.setX(61);
                weather.setY(127);
                break;

            case "광진구":
            case "송파구":
            case "강동구":
                weather.setX(62);
                weather.setY(126);
                break;

            case "중랑구":
                weather.setX(62);
                weather.setY(128);
                break;

            case "강북구":
                weather.setX(61);
                weather.setY(128);
                break;

            case "도봉구":
            case "노원구":
                weather.setX(61);
                weather.setY(129);
                break;

            case "은평구":
            case "서대문구":
            case "마포구":
                weather.setX(59);
                weather.setY(127);
                break;

            case "양천구":
            case "강서구":
            case "영등포구":
                weather.setX(58);
                weather.setY(126);
                break;

            case "구로구":
                weather.setX(58);
                weather.setY(125);
                break;

            case "금천구":
                weather.setX(59);
                weather.setY(124);
                break;

            case "동작구":
            case "관악구":
                weather.setX(59);
                weather.setY(125);
                break;

            case "서초구":
                weather.setX(61);
                weather.setY(125);
                break;
            case "강남구":
                weather.setX(61);
                weather.setY(126);
                break;

        }

        try{
            resultText = new Task().execute().get();
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        tv_parsing.setText(resultText);
        resultText = "";
    }
}
