package com.qianwang.work2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {
    private Button btn_kiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_kiss = (Button) findViewById(R.id.btn_kiss);
        tv_show = (TextView) findViewById(R.id.tv_show);


       /* btn_kiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_show.setText("我点了你！！");
            }
        });*/

       /* btn_kiss.setOnClickListener(v -> {
            tv_show.setText("我点了你");
        }
        );*/

      /*  Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello yicool");
            }
        });

        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                  tv_show.setText(s);
            }
        };

        observable.subscribe(subscriber);
*/
        /*Observable.just("nell yicool!!!").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                tv_show.setText(s);
            }
        });*/

        /*Observable.just("hello yicool===").subscribe(s -> {
            tv_show.setText(s);
        });
*/


        /**
         * rxAndroid
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("你好啊，嘻嘻");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                tv_show.setText(s);
            }
        };
        Subscriber<String> toastSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, "你好啊，嘻嘻", Toast.LENGTH_SHORT).show();
            }
        };
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(stringSubscriber);
        observable.subscribe(toastSubscriber);


    }

    private TextView tv_show;

}
