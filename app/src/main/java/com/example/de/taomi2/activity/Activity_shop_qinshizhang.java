package com.example.de.taomi2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.example.de.taomi2.R;
import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.adapter.Shop_qingshizhang_adapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.ShangPingItem;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_shop_qinshizhang extends Activity {
    ListView listView;
    Shop_qingshizhang_adapter adapter;
    List<ShangPingItem>shangPingItemList=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop);
        initLayout();
        initListennner();
        initParam();
    }
    private void initListennner(){
        findViewById(R.id.relativeLayout24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_shop_qinshizhang.this,Activity_fabu_shangping.class));
            }
        });
    }
    private void initLayout(){
        listView=(ListView)findViewById(R.id.listView6);
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

            try {
                shangPingItemList= Manage.viewChaoshilistByUserid(MyApplication.school, MyApplication.loudong, MyApplication.user.getId() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return shangPingItemList;
        }
        protected void onPostExecute(List<ShangPingItem> shangPingItemList){
            adapter=new Shop_qingshizhang_adapter(Activity_shop_qinshizhang.this,shangPingItemList);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){
        }
    }

}
