package com.example.test_ver2;

import androidx.databinding.ObservableField;

public class Weather {
    private int x;
    private int y;
    private String guName;

    private String [] POP = new String[3];  //강수확률%
    private String [] REH = new String[3];  //습도%
    private String [] PTY = new String[3];  //강수형태 없음(0) 비(1) 진눈개비(2) 눈(3) 소나기(4)
    private String [] SKY = new String[3];  //하늘상태 맑음(1) 구름많음(3) 흐림(4)
    private String [] T3H = new String[3];  //3시간 기온 도씨
    private String [] UUU = new String[3];  //풍속(동서) 동(+) 서(-)
    private String [] VEC = new String[3];  //풍향 m/s
    private String [] VVV = new String[3];  //풍속(남북) 북(+) 남(-)


    private Weather(){
        x = 60;
        y = 127;
        //종로구 기준
    }
    private static Weather weather = new Weather();

    public static Weather getInstance(){
        return weather;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void setPOP(String POP, int index) {
        this.POP[index] = POP;
    }

    public void setREH(String REH, int index) {
        this.REH[index] = REH;
    }

    public void setPTY(String PTY, int index) {
        this.PTY[index] = PTY;
    }

    public void setSKY(String SKY, int index) {
        this.SKY[index] = SKY;
    }

    public void setT3H(String t3H, int index) {
        this.T3H[index] = t3H;
    }

    public void setUUU(String UUU, int index) {
        this.UUU[index] = UUU;
    }

    public void setVEC(String VEC, int index) {
        this.VEC[index] = VEC;
    }

    public void setVVV(String VVV, int index) {
        this.VVV[index] = VVV;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public String getPOP(int index) {
        return POP[index];
    }

    public String getREH(int index) {
        return REH[index];
    }

    public String getPTY(int index) {
        return PTY[index];
    }

    public String getSKY(int index) {
        return SKY[index];
    }

    public String getT3H(int index) {
        return T3H[index];
    }

    public String getUUU(int index) {
        return UUU[index];
    }

    public String getVEC(int index) {
        return VEC[index];
    }

    public String getVVV(int index) {
        return VVV[index];
    }


}
