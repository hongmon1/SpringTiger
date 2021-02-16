package com.example.howtocook_version2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {
    public static String ip = "172.30.1.49"; //내 IP번호
    String sendMsg, receiveMsg;
    String serverip = "http://"+ip+":8787/DbConn/Android/androidDB.jsp"; //연결할 jsp주소

    /*Task(String sendmsg){
        this.sendMsg = sendmsg;
    }
    */
    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;
            URL url = new URL(serverip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"EUC-KR");

            /*
            if(sendMsg.equals("vision_write")){
                sendMsg = "vision_write="+strings[0]+"&type="+strings[1];
            }else if(sendMsg.equals("vision_list")){
                sendMsg = "&type="+strings[0];
            }
            */
            //전송할 데이터. GET방식으로 지정
            sendMsg = "id="+strings[0]+"&name="+strings[1]+"&ingre="+strings[2]+"&desc="+strings[3]+"&image="+strings[4]+"&user_id="+strings[5];

            osw.write(sendMsg);
            osw.flush();

            //jsp와 통신 성공 시 수행
            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"EUC-KR");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                //jsp에서 보낸값을 받는 부분
                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else{
                //통신 실패
                Log.i("통신 결과", conn.getResponseCode()+"에러");
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        //jsp로부터 받은 리턴값
        return receiveMsg;
    }
}
