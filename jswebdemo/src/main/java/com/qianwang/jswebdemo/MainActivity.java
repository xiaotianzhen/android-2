package com.qianwang.jswebdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    private WebView first_wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_wv= (WebView) findViewById(R.id.first_wv);

        first_wv.getSettings().setJavaScriptEnabled(true);
        first_wv.getSettings().setDefaultTextEncodingName("UTF-8");

        first_wv.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains("orpheus://login")){

                    Intent intent=new Intent(MainActivity.this,TestNetWebViewActivity.class);
                    startActivity(intent);
                    finish();
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        first_wv.loadUrl("http://music.163.com/store/m/product/index");
    }
}
