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
import com.example.de.taomi2.adapter.Dianpu_list_adapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.Dianpu;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_shop_list extends Activity {
    PullToRefreshListView listView;
    TextView title;
    EditText findEdittext;
    String type,school;
    Dianpu_list_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop_list);
        type=getIntent().getStringExtra("type");
        initLayout();
        initListenner();
        initParam();
    }
    private void initLayout(){
        title=(TextView)findViewById(R.id.textView18);
        title.setText(type);
        listView=(PullToRefreshListView)findViewById(R.id.plv_home_right);
        findEdittext=(EditText)findViewById(R.id.editText6);
    }
    private void initListenner(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position:" + position);
                Intent intent=new Intent(Activity_shop_list.this,Activity_guangjie_list.class);
                intent.putExtra("dianpuid",adapter.mItems.get(position-1).getId());
                intent.putExtra("school",school);
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
        MyApplication myApplication=(MyApplication)(getApplication());
        school=myApplication.school;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    class MyTask extends AsyncTask<String,Void,List> {

        @Override
        protected List<Dianpu> doInBackground(String... params) {
            List<Dianpu>shangPingItemList=null;
            try {
                shangPingItemList= Manage.viewDianpuByType(type, school);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return shangPingItemList;
        }
        protected void onPostExecute(List<Dianpu>shangPingItemList){
            if(shangPingItemList==null){
                shangPingItemList=new ArrayList<>();
            }
            adapter=new Dianpu_list_adapter(Activity_shop_list.this,shangPingItemList);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){
        }
    }
}
