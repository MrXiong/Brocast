package com.page.brocast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReceycleViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Bean> list;
    int i = 0;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receycle_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        myAdapter = new MyAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReceycleViewActivity.this));
        recyclerView.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private List<Bean> list;
        public MyAdapter(List<Bean> list){
            this.list = list;
        }
        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.tv_text.setText(list.get(position).getStr());
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    ReceycleViewActivity.this).inflate(R.layout.item_text, parent,
                    false));
            return holder;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_text;

            public MyViewHolder(View view) {
                super(view);
                tv_text = (TextView) view.findViewById(R.id.tv_text);
            }
        }

        public void setList(List<Bean> list) {
            this.list = list;
        }

        public List<Bean> getList() {
            return list;
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
            List tempList = new ArrayList();
            tempList.add(new Bean("bean" + i));
            i++;
            tempList.add(new Bean("bean" + i));
            i++;
            tempList.add(new Bean("bean" + i));
            i++;
            tempList.add(new Bean("bean" + i));
            i++;
            list.addAll(tempList);
            myAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Bean {
        String str;
        public Bean(String str){
            this.str = str;
        }
        public void setStr(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
}
