package com.example.de.taomi2.Task;

import android.os.AsyncTask;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.http.Manage;


/**
 * Created by 真爱de仙 on 2015/7/2.
 */
public class ChanceZhungtai extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {
            String id=params[0];
            String zhuangtai=params[1];
            String changetype=params[2];
            String result=null;
            try {
                result= Manage.chanceShangpinZhuangtai(MyApplication.school, MyApplication.loudong, MyApplication.user.getId() + "", id, zhuangtai, changetype);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        protected void onPostExecute(String result){

        }
        @Override
        protected void onPreExecute(){
            ;
        }

}
