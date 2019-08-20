package com.example.springtigersapp.Model;

public class Weather {
    String pop; //강수 확률
    String reh; //습도
    String sky; //하늘 상태
    String t3h; //3시간 기온
    String tmn; //아침최저기온
    String tmx; //낮최고기온
    String wsd; //풍속

    //setter
    public void setPop(String pop) {
        this.pop = pop;
    }

    public void setReh(String reh) {
        this.reh = reh;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public void setT3h(String t3h) {
        this.t3h = t3h;
    }

    public void setTmn(String tmn) {
        this.tmn = tmn;
    }

    public void setTmx(String tmx) {
        this.tmx = tmx;
    }

    public void setWsd(String wsd) {
        this.wsd = wsd;
    }

    //getter
    public String getPop() {
        return pop;
    }

    public String getReh() {
        return reh;
    }

    public String getSky() {
        return sky;
    }

    public String getT3h() {
        return t3h;
    }

    public String getTmn() {
        return tmn;
    }

    public String getTmx() {
        return tmx;
    }

    public String getWsd() {
        return wsd;
    }
}
