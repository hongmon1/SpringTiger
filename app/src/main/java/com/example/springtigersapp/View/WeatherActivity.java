package com.example.springtigersapp.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.springtigersapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherActivity extends AppCompatActivity {
    EditText et_x;
    EditText et_y;
    Button btn;
    TextView tv_parsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_x = (EditText)findViewById(R.id.et_x);
        et_y = (EditText)findViewById(R.id.et_y);
        btn = (Button)findViewById(R.id.btn);
        tv_parsing = (TextView)findViewById(R.id.tv_parsing);

        btn.setOnClickListener(myClickListener);
    }

    View.OnClickListener myClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch (v.getId()){

                case R.id.btn:
                    /*try{
                        url = url+"?ServiceKey="+serviceKey+
                                "&base_date=20190820"+"&base_time=0500"+"&nx=60"+"&ny=127"
                                +"&numOfRows=10"+"&PageNo=1"+"&_type=json";

                        urlBuilder = new StringBuilder(url);
                        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
                        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20190820", "UTF-8"));
                        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8"));
                        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(et_x.getText().toString(), "UTF-8"));
                        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(et_y.getText().toString(), "UTF-8"));
                        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

                        urlBuilder = new StringBuilder(url);
                        URL url = new URL(urlBuilder.toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Content-type", "application/json");
                        BufferedReader rd;
                        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        } else {
                            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        }
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }

                        System.out.println(sb.toString());

                        JSONArray jsonArray = new JSONArray(sb.toString());
                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);


                            pop = jsonObject.getString("POP"); //강수 확률
                            reh = jsonObject.getString("REH"); //습도
                            sky = jsonObject.getString("SKY"); //하늘 상태
                            t3h = jsonObject.getString("T3H"); //3시간 기온
                            tmn = jsonObject.getString("TMN"); //아침최저기온
                            tmx = jsonObject.getString("TMX"); //낮최고기온
                            wsd = jsonObject.getString("WSD"); //풍속
                            tv_parsing.setText("" + pop + "\n"
                                    + "" + reh + "\n"
                                    + "" + sky + "\n"
                                    + "" + t3h + "\n"
                                    + "" + tmn + "\n"
                                    + "" + tmx + "\n"
                                    + "" + wsd );
                        }

                        rd.close();
                        conn.disconnect();

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }   */

                    try{

                        tv_parsing.setText("버튼이 눌렷다");
                        StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
                        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=AlTCQWFRxBhZXn0EKDHdYjwuuTNzp98MYnQuP%2FMLcdYXI0r%2BjN%2FMqHWA6%2Btb0mId%2B%2FUHMlgyiYprl3mlI%2BbqrQ%3D%3D"); /*Service Key*/
                        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("TEST_SERVICE_KEY", "UTF-8")); /*서비스 인증*/
                        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20190820", "UTF-8")); /*‘15년 12월 1일발표*/
                        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표 * 기술문서 참조*/
                        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값*/
                        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
                        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
                        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
                        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml(기본값), json*/
                        URL url = new URL(urlBuilder.toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Content-type", "application/json");
                        System.out.println("Response code: " + conn.getResponseCode());
                        BufferedReader rd;
                        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        } else {
                            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        }
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }
                        rd.close();
                        conn.disconnect();
                        System.out.println(sb.toString());

                            /*JSONArray jsonArray = new JSONArray(sb.toString());
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                pop = jsonObject.getString("POP"); //강수 확률
                                reh = jsonObject.getString("REH"); //습도
                                sky = jsonObject.getString("SKY"); //하늘 상태
                                t3h = jsonObject.getString("T3H"); //3시간 기온
                                wsd = jsonObject.getString("WSD"); //풍속
                                tv_parsing.setText("세팅" + pop + "\n"
                                        + "" + reh + "\n"
                                        + "" + sky + "\n"
                                        + "" + t3h + "\n"
                                        + "" + wsd );
                            }
                            */
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    break;


            }

        }

    };

    public void parsingWeather()throws IOException {



        StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=AlTCQWFRxBhZXn0EKDHdYjwuuTNzp98MYnQuP%2FMLcdYXI0r%2BjN%2FMqHWA6%2Btb0mId%2B%2FUHMlgyiYprl3mlI%2BbqrQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("TEST_SERVICE_KEY", "UTF-8")); /*서비스 인증*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode("20190820", "UTF-8")); /*‘15년 12월 1일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표 * 기술문서 참조*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("127", "UTF-8")); /*예보지점의 Y 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml(기본값), json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

    }
}
