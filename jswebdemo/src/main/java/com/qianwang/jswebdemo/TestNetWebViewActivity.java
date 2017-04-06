package com.qianwang.jswebdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


public class TestNetWebViewActivity extends AppCompatActivity {


    private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_net_web_view);

        wb= (WebView) findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.addJavascriptInterface(new WebViewJavaScriptInterface(this),"app");
        wb.loadUrl("file:///android_asset/index.html");

    }

       class WebViewJavaScriptInterface{

           private Context context;

           public WebViewJavaScriptInterface(Context context) {

               this.context=context;
           }
           @JavascriptInterface
           //这个addJavascriptInterface 方法在4.2 以下呢，是有一个很严重的安全漏洞的，不会检测有没有注解
           public void  makeToast(String message,boolean lengthLong){

               Toast.makeText(context, message,(lengthLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT)).show();
               //界面如果有toast 则js调用java成功，因为makeToast是js中的方法

               wb.loadUrl("javascript:display_alert()");//通过js调用java 再在java的代码里回调js代码那就完全无效了。

               //第一：让js能够安全的调用java代码，主要是对于4.2版本以下的手机来说

               //第二：让js调用java以后 依旧可以回调js，这是对于所有手机来说的。

           }

       }

    public void test(View view){
              wb.loadUrl("javascript:display_alert()");
    }
}
