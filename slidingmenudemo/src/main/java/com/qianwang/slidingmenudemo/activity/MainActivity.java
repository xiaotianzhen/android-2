package com.qianwang.slidingmenudemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.qianwang.slidingmenudemo.R;
import com.qianwang.slidingmenudemo.view.SlidingMenu;
import com.qianwang.slidingmenudemo.adapter.menuAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private SlidingMenu smenu;
    private List<Map<Object,Object>> mList=new ArrayList<Map<Object,Object>>();

    private void initView(){
        mRecyclerView= (RecyclerView) findViewById(R.id.mRecyclerView);

    }
    private void initData(){

        for(int i=0;i<4;i++){
            Map<Object,Object> map=new HashMap<Object,Object>();
            map.put("key","第"+i+"条数据");
            map.put("img",getApplicationContext().getResources().getDrawable(R.drawable.icon));
            mList.add(map);
        }

        Log.i("520it", "mList" + "**************************"+mList.size());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smenu= (SlidingMenu) findViewById(R.id.smenu);
        initView();
        initData();

        mRecyclerView.setAdapter(new menuAdapter(getApplicationContext(),mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAnimation(new AlphaAnimation(0,1));
    }


    public void  goMenu(View view){

            smenu.toggle();
    }
}
