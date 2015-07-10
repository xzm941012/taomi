package com.example.de.taomi2.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.obj.DingDan;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 真爱de仙 on 2015/2/25.
 */
public class MyReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences=MyApplication.myApplication.getSharedPreferences("option", Activity.MODE_PRIVATE);
    @Override
    public void onReceive(Context context, Intent intent) {
        Gson gson=new Gson();
        System.out.println("收到通知");
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("用户点击打开了通知");
            Bundle bundle = intent.getExtras();
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            System.out.println("extra:"+extra);
        }else if(JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())){
            System.out.println("用户收到了通知");
            Bundle bundle = intent.getExtras();
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JSONObject object=null;
            try {
                object=new JSONObject(extra);
                String dingdanextra=object.getString("dingdan");
                DingDan dingDan=gson.fromJson(dingdanextra,DingDan.class);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString("dingdanList","");
                editor1.apply();
                System.out.println("Dingdan:"+dingDan.toString());
                String dingdans=sharedPreferences.getString("dingdanList","");
                List<DingDan>dingDanList=null;
                if(dingdans.equals("")){
                    dingDanList=new ArrayList<>();
                }else{
                    dingDanList=gson.fromJson(dingdans,new TypeToken<List<DingDan>>(){}.getType());
                }
                dingDanList.add(dingDan);
                System.out.println("dingDanList:"+dingDanList);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("dingdanList",gson.toJson(dingDanList));
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
