package com.page.brocast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //http://blog.csdn.net/veryitman/article/details/6611138
    RecyclerView mRecyclerView;
    List<String> list = new ArrayList<String>();
    HomeAdapter mHomeAdapter;
    boolean isbrocast = false;
    int pos = 0;
    Timer timer = new Timer();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    list.add(new String("done" + pos));
                    pos ++;
                    mHomeAdapter.notifyDataSetChanged();
                    if (isbrocast) {
                        Intent i = new Intent("com.page.brocast.async");
                        i.putExtra("size", list.size() + "");
                        sendBroadcast(i);
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MyActivityManager.getInstance().addActivity(MainActivity.this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mHomeAdapter = new HomeAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHomeAdapter);


    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public HomeAdapter(){
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_text, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv_text.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv_text;

            public MyViewHolder(View view)
            {
                super(view);
                tv_text = (TextView) view.findViewById(R.id.tv_text);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,ResultActivity.class));
            return true;
        }
        if (id == R.id.action_settings2) {
            timer.schedule(new TimerTask() {
                               public void run() {
                                   Log.e("task", "task");
                                   Message message = new Message();
                                   message.what = 1;
                                   handler.sendMessage(message);
                               }
                           },
                    1000,
                    1000); //延时1000ms后执行，1000ms执行一次
        }
        if (id == R.id.action_settings3) {
            startActivity(new Intent(MainActivity.this,ReceycleViewActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setIsbrocast(boolean isbrocast) {
        this.isbrocast = isbrocast;
    }
    public boolean getIsbrocast(){
        return this.isbrocast;
    }
}
