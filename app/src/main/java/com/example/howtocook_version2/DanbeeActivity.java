package com.example.howtocook_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//출처: https://duckssi.tistory.com/9 [홍드로이드의 야매코딩]
//https://jerryjerryjerry.tistory.com/72
public class DanbeeActivity extends AppCompatActivity {

    private WebView webView;
    private String url ="https://frogue.danbee.ai/?chatbot_id=9794280d-ea31-4915-ba75-cfd2a70be00e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danbee);

        //action bar 없애기
        getSupportActionBar().hide();

        webView = findViewById(R.id.webView);


        //크롬 실행 가능
        webView.setWebChromeClient(new WebChromeClient());
        //새창열기 없이 웹뷰 내에서 열기
        webView.setWebViewClient(new WebViewClientClass());

        //https://novemberde.github.io/android/2017/12/19/Android-Webview.html
        //Javascript문제로 하얀화면 뜨는거 방지하기 위해
        webView.setNetworkAvailable(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        //자바 스크립트 허용
        webView.getSettings().setJavaScriptEnabled(true);
        //웹뷰 실행
        webView.loadUrl(url);

    }

    //뒤로가기 버튼 이벤트
    //웹뷰에서 뒤로 가기 누르면 뒤로 이동 가능
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //페이지 이동
    private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
