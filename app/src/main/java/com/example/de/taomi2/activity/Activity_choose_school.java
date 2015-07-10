package com.example.de.taomi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.School;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_choose_school extends Activity {
    ListView listView;
    EditText editText;
    String school;
    String qinshi;
    SharedPreferences sharedPreferences;//好友
    private ProgressDialog dialog;
    List<String> data=new ArrayList<>();
    List<String> data2=new ArrayList<>();
    ArrayAdapter adapter;
    int type=0; //0:学校 1:楼栋
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_school);
        dialog = new ProgressDialog(Activity_choose_school.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initLayout();
        initListennner();
        initParam();
    }
    private void initListennner(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(type==0) {
                    school = (String) adapter.getItem(position);
                    MyTask2 myTask2=new MyTask2();
                    myTask2.execute(school);
                }else{
                    if(position==0){
                        type=0;
                        adapter=new ArrayAdapter<String>(Activity_choose_school.this,R.layout.label_choose_school_item,data2);
                        listView.setAdapter(adapter);
                    }else{
                        qinshi=(String)adapter.getItem(position);
                        System.out.println("qinshi:" + qinshi);
                        MyTask3 myTask3=new MyTask3();
                        myTask3.execute("");

                    }
                }
            }
        });


        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String sousuoText = editText.getText().toString().trim();
                if(sousuoText.equals("")){
                    adapter=new ArrayAdapter<String>(Activity_choose_school.this,R.layout.label_choose_school_item,data);
                    listView.setAdapter(adapter);
                    data2=data;
                }else {
                    data2 = new ArrayList<>();
                    for (String otherUser : data) {
                        if (otherUser.contains(sousuoText)) {
                            data2.add(otherUser);
                        }
                    }
                    adapter=new ArrayAdapter<String>(Activity_choose_school.this,R.layout.label_choose_school_item,data2);
                    listView.setAdapter(adapter);
                }
            }
        });
    }
    private void initLayout(){
        editText=(EditText)findViewById(R.id.editText7);
        listView=(ListView)findViewById(R.id.listView);
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
    class MyTask extends AsyncTask<String,Void,List> {

        @Override
        protected List doInBackground(String... params) {
            data=new ArrayList<>();
            try {
                data= Manage.viewSchoolList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            data2=data;
            return data;
        }
        protected void onPostExecute(List result){
            dialog.dismiss();
            type=0;
            System.out.println(result);
            adapter=new ArrayAdapter<String>(Activity_choose_school.this,R.layout.label_choose_school_item,data);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }
    }
    class MyTask2 extends AsyncTask<String,Void,List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            String school=params[0];
            List<String> data1=new ArrayList<>();
            try {
                data1= Manage.viewQinshiList(school);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data1;
        }
        protected void onPostExecute(List<String> result){
            dialog.dismiss();
            type=1;
            result.add(0,"点击重新选择学校");
            adapter=new ArrayAdapter<String>(Activity_choose_school.this,R.layout.label_choose_school_item,result);
            listView.setAdapter(adapter);
        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }
    }
    class MyTask3 extends AsyncTask<String,Void,School> {

        @Override
        protected School doInBackground(String... params) {
            School school1=null;
            try {
                school1= Manage.viewSchoolUserIdList(school, qinshi);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return school1;
        }
        protected void onPostExecute(School school1){
            dialog.dismiss();
            MyApplication myApplication=((MyApplication)getApplication());
            SharedPreferences sharedPreferences=myApplication.getSharedPreferences();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("school",school);
            editor.putString("loudong",qinshi);
            editor.putString("qinShiShangId",school1.getUserid()+"");
            editor.apply();
            myApplication.school=school;
            myApplication.loudong=qinshi;
            myApplication.qinShiShangId=school1.getUserid()+"";
            startActivity(new Intent(Activity_choose_school.this,MainActivity.class));
            finish();
        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }
    }
}
