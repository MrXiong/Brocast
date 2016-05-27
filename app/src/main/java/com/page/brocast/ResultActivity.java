package com.page.brocast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    List<String> list = new ArrayList<String>();
    TextView tv_result;
    AsyncReceiver  asyncReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MyActivityManager.getInstance().addActivity(ResultActivity.this);
        //生成广播处理
        asyncReceiver = new AsyncReceiver(ResultActivity.this);
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.page.brocast.async");
        //注册广播
        this.registerReceiver(asyncReceiver, intentFilter);
        if (MyActivityManager.getInstance().getMainAcitivity() != null){
            ((MainActivity)MyActivityManager.getInstance().getMainAcitivity()).setIsbrocast(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tv_result= (TextView)this.findViewById(R.id.tv_result);
    }

    public void update(Intent intent){
        Toast.makeText(getApplicationContext(),intent.getStringExtra("size"),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(asyncReceiver);
    }
}
