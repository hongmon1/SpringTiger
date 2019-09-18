package com.example.springtigersapp.Model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Task extends AsyncTask<String, Void, String> {

    com.example.test_ver2.Weather weather = com.example.test_ver2.Weather.getInstance();
    private String str, receiveMsg;
    Date date =  new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    String currentDate = dateFormat.format(date);

    @Override
    protected String doInBackground(String... params) {
        try{
            StringBuilder urlBuilder = new StringBuilder("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=AlTCQWFRxBhZXn0EKDHdYjwuuTNzp98MYnQuP%2FMLcdYXI0r%2BjN%2FMqHWA6%2Btb0mId%2B%2FUHMlgyiYprl3mlI%2BbqrQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("TEST_SERVICE_KEY", "UTF-8")); /*서비스 인증*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8")); /*‘15년 12월 1일발표*/
            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*05시 발표 * 기술문서 참조*/
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(weather.getX()), "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(weather.getY()), "UTF-8")); /*예보지점의 Y 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값), json*/
            URL url = new URL(urlBuilder.toString());
            Log.d("커넥션:", "시작 전");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("커넥션:", "시작 후");

            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine())!= null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.d("receiveMsg:",receiveMsg);

                reader.close();
                weatherParser(receiveMsg);
            }
            else{
                Log.d("ResponseCode", conn.getResponseCode()+"코드넘버");
                Log.d("통신결과",conn.getResponseCode()+"에러");

            }

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return receiveMsg;
    }

    public void weatherParser(String jsonString){

        try{
            JSONObject obj = new JSONObject(jsonString);
            JSONObject parse_response = (JSONObject)obj.get("response");
            JSONObject parse_body = (JSONObject)parse_response.get("body");
            JSONObject parse_items = (JSONObject)parse_body.get("items");

            JSONArray parse_item = (JSONArray)parse_items.get("item");
            JSONObject parse_weather;
            int i;
            for(i=0; i<parse_item.length(); i++) {
                parse_weather = (JSONObject) parse_item.get(i);
                if (parse_weather.optString("fcstTime").equals("0600")) {
                    switch (parse_weather.optString("category")) {
                        case "POP":
                            weather.setPOP(parse_weather.optString("fcstValue", "POP"), 0);
                            break;
                        case "PTY":
                            weather.setPTY(parse_weather.optString("fcstValue", "PTY"), 0);
                            break;
                        case "REH":
                            weather.setREH(parse_weather.optString("fcstValue", "REH"), 0);
                            break;
                        case "SKY":
                            weather.setSKY(parse_weather.optString("fcstValue", "SKY"), 0);
                            break;
                        case "T3H":
                            weather.setT3H(parse_weather.optString("fcstValue", "T3H"), 0);
                            break;
                        case "UUU":
                            weather.setUUU(parse_weather.optString("fcstValue", "UUU"), 0);
                            break;
                        case "VEC":
                            weather.setVEC(parse_weather.optString("fcstValue", "VEC"), 0);
                            break;
                        case "VVV":
                            weather.setVVV(parse_weather.optString("fcstValue", "VVV"), 0);
                            break;
                    }
                }

                else if (parse_weather.optString("fcstTime").equals("1200")) {
                    switch (parse_weather.optString("category")) {
                        case "POP":
                            weather.setPOP(parse_weather.optString("fcstValue", "POP"), 1);
                            break;
                        case "PTY":
                            weather.setPTY(parse_weather.optString("fcstValue", "PTY"), 1);
                            break;
                        case "REH":
                            weather.setREH(parse_weather.optString("fcstValue", "REH"), 1);
                            break;
                        case "SKY":
                            weather.setSKY(parse_weather.optString("fcstValue", "SKY"), 1);
                            break;
                        case "T3H":
                            weather.setT3H(parse_weather.optString("fcstValue", "T3H"), 1);
                            break;
                        case "UUU":
                            weather.setUUU(parse_weather.optString("fcstValue", "UUU"), 1);
                            break;
                        case "VEC":
                            weather.setVEC(parse_weather.optString("fcstValue", "VEC"), 1);
                            break;
                        case "VVV":
                            weather.setVVV(parse_weather.optString("fcstValue", "VVV"), 1);
                            break;

                    }
                }
                else if (parse_weather.optString("fcstTime").equals("1800")) {
                        switch (parse_weather.optString("category")) {
                            case "POP":
                                weather.setPOP(parse_weather.optString("fcstValue", "POP"), 2);
                                break;
                            case "PTY":
                                weather.setPTY(parse_weather.optString("fcstValue", "PTY"), 2);
                                break;
                            case "REH":
                                weather.setREH(parse_weather.optString("fcstValue", "REH"), 2);
                                break;
                            case "SKY":
                                weather.setSKY(parse_weather.optString("fcstValue", "SKY"), 2);
                                break;
                            case "T3H":
                                weather.setT3H(parse_weather.optString("fcstValue", "T3H"), 2);
                                break;
                            case "UUU":
                                weather.setUUU(parse_weather.optString("fcstValue", "UUU"), 2);
                                break;
                            case "VEC":
                                weather.setVEC(parse_weather.optString("fcstValue", "VEC"), 2);
                                break;
                            case "VVV":
                                weather.setVVV(parse_weather.optString("fcstValue", "VVV"), 2);
                                break;
                        }

                    }

                }


            Log.d("POP",weather.getPOP(0));
            Log.d("PTY",weather.getPTY(0));
            Log.d("REH",weather.getREH(0));
            Log.d("SKY",weather.getSKY(0));
            Log.d("T3H",weather.getT3H(0));
            Log.d("UUU",weather.getUUU(0));
            Log.d("VEC",weather.getVEC(0));
            Log.d("VVV",weather.getVVV(0));

            Log.d("POP",weather.getPOP(1));
            Log.d("PTY",weather.getPTY(1));
            Log.d("REH",weather.getREH(1));
            Log.d("SKY",weather.getSKY(1));
            Log.d("T3H",weather.getT3H(1));
            Log.d("UUU",weather.getUUU(1));
            Log.d("VEC",weather.getVEC(1));
            Log.d("VVV",weather.getVVV(1));

            Log.d("POP",weather.getPOP(2));
            Log.d("PTY",weather.getPTY(2));
            Log.d("REH",weather.getREH(2));
            Log.d("SKY",weather.getSKY(2));
            Log.d("T3H",weather.getT3H(2));
            Log.d("UUU",weather.getUUU(2));
            Log.d("VEC",weather.getVEC(2));
            Log.d("VVV",weather.getVVV(2));


        }
        catch(JSONException e){
            e.printStackTrace();
            Log.d("weatherParser():","error!");
        }

    }

}
