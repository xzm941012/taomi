package com.example.de.taomi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.adapter.DingDan_adapter;
import com.example.de.taomi2.obj.DingDan;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_jiedan_online extends Activity {
    ListView listView;
    SharedPreferences sharedPreferences=MyApplication.myApplication.getSharedPreferences("option", Activity.MODE_PRIVATE);
    Gson gson=new Gson();
    boolean running=true;
    DingDan_adapter adapter;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dingdan);

        dialog = new ProgressDialog(Activity_jiedan_online.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initLayout();
        initListennner();
        initParam();
    }
    private void initListennner(){

    }
    private void initLayout(){
        listView=(ListView)findViewById(R.id.listView4);
    }
    private void initParam(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running){
                    MyTask myTask=new MyTask();
                    myTask.execute("");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        /*
        DingDan dingDan=new DingDan("1","接单中","可口可乐特价x1  美年达x5  乐视薯片x2","NB625","18816404955","请在10分钟内送达，很急，过期不需",7.20,"1","江西财经大学麦庐园","货到付款","22:10:13");
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        adapter=new DingDan_adapter(Activity_dingdan.this,dingDanList);
        listView.setAdapter(adapter);
        */
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
        running=false;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    class MyTask extends AsyncTask<String,Void,List<DingDan>> {

        @Override
        protected List<DingDan> doInBackground(String... params) {
            List<DingDan>dingDanList=null;
            String dingdans=sharedPreferences.getString("dingdanList","");
            System.out.println("dingDanList:"+dingDanList);
            if(dingdans.equals("")){
                dingDanList=new ArrayList<>();
            }else{
                dingDanList=gson.fromJson(dingdans,new TypeToken<List<DingDan>>(){}.getType());
            }
            return dingDanList;
        }
        protected void onPostExecute(List<DingDan>dingDanList){
            if(dingDanList==null) {
                dingDanList = new ArrayList<>();
            }
            adapter=new DingDan_adapter(Activity_jiedan_online.this,dingDanList);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){

        }

    }
}
