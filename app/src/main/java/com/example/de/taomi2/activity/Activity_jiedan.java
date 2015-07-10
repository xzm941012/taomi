package com.example.de.taomi2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.de.taomi2.R;
import com.example.de.taomi2.adapter.Jiedan_adapter;
import com.example.de.taomi2.obj.DingDan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_jiedan extends Activity {
    ListView listView;
    Jiedan_adapter adapter;
    TextView zhuangtaiBt,zhuangtaiTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jiedan);
        initLayout();
        initListennner();
        initParam();
    }
    private void initListennner(){
        zhuangtaiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zhuangtai=zhuangtaiTxt.getText().toString().trim();
                if(zhuangtai.equals("休息中")){
                    zhuangtaiTxt.setText("营业中");
                }else{
                    zhuangtaiTxt.setText("休息中");
                }
            }
        });
    }
    private void initLayout(){
        zhuangtaiBt=(TextView)findViewById(R.id.textView53);
        zhuangtaiTxt=(TextView)findViewById(R.id.textView52);
        listView=(ListView)findViewById(R.id.listView5);
    }
    private void initParam(){
        List<DingDan> dingDanList=new ArrayList<>();
        /*
        DingDan dingDan=new DingDan("1","接单中","可口可乐特价x1  美年达x5  乐视薯片x2","NB625","18816404955","请在10分钟内送达，很急，过期不需",7.20,"1","江西财经大学麦庐园","货到付款","22:10:13");
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        dingDanList.add(dingDan);
        DingDan dingDan2=new DingDan("1","已接单","可口可乐特价x1  美年达x5  乐视薯片x2","NB625","18816404955","请在10分钟内送达，很急，过期不需",7.20,"1","江西财经大学麦庐园","货到付款","22:10:13");
        dingDanList.add(dingDan2);
        adapter=new Jiedan_adapter(Activity_jiedan.this,dingDanList);
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
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
