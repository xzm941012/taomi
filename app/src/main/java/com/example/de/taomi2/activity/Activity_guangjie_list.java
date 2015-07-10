package com.example.de.taomi2.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.adapter.Guangjie_adapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.ShangPingItem;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_guangjie_list extends Activity {
    TextView bt1,bt2,bt3;
    PullToRefreshListView listView;
    EditText findEdittext;
    String school,loudong,type;
    Guangjie_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guangjie_list);

        school= MyApplication.school;
        loudong=MyApplication.loudong;
        type=getIntent().getStringExtra("type");
        MyApplication.type=type;
        initLayout();
        initListenner();
        initParam();
    }
    private void initLayout(){
        bt1=(TextView)findViewById(R.id.textView37);
        bt2=(TextView)findViewById(R.id.textView38);
        bt3=(TextView)findViewById(R.id.textView39);
        listView=(PullToRefreshListView)findViewById(R.id.plv_home_right);
        findEdittext=(EditText)findViewById(R.id.editText6);
    }
    private void initListenner(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:" + position);
                Intent intent=new Intent(Activity_guangjie_list.this,Activity_xiangqing.class);
                intent.putExtra("id",adapter.getItem(position-1).getId());
                startActivity(intent);
            }
        });
        findViewById(R.id.imageView14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.textView18).setVisibility(View.GONE);
                findViewById(R.id.imageView14).setVisibility(View.GONE);
                findViewById(R.id.view2).setVisibility(View.VISIBLE);
                findViewById(R.id.textView40).setVisibility(View.VISIBLE);
                findEdittext.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) findEdittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        findViewById(R.id.imageView40).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.textView18).setVisibility(View.VISIBLE);
                findViewById(R.id.imageView14).setVisibility(View.VISIBLE);
                findViewById(R.id.view2).setVisibility(View.GONE);
                findViewById(R.id.textView40).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setTextColor(getResources().getColor(R.color.bt_pressed));
                bt2.setTextColor(getResources().getColor(R.color.bt_normal));
                bt3.setTextColor(getResources().getColor(R.color.bt_normal));
            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setTextColor(getResources().getColor(R.color.bt_normal));
                bt2.setTextColor(getResources().getColor(R.color.bt_pressed));
                bt3.setTextColor(getResources().getColor(R.color.bt_normal));
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setTextColor(getResources().getColor(R.color.bt_normal));
                bt2.setTextColor(getResources().getColor(R.color.bt_normal));
                bt3.setTextColor(getResources().getColor(R.color.bt_pressed));
            }
        });


    }
    private void initParam(){
        MyTask myTask=new MyTask();
        myTask.execute("");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){

        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    class MyTask extends AsyncTask<String,Void,List<ShangPingItem>> {

        @Override
        protected List<ShangPingItem> doInBackground(String... params) {
            List<ShangPingItem>shangPingItemList=null;
            try {
                shangPingItemList= Manage.viewShangpinlistByType(school, loudong, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return shangPingItemList;
        }
        protected void onPostExecute(List<ShangPingItem>shangPingItemList){
            if(shangPingItemList==null){
                shangPingItemList=new ArrayList<>();
            }
            adapter=new Guangjie_adapter(Activity_guangjie_list.this,shangPingItemList);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){
        }
    }
}
