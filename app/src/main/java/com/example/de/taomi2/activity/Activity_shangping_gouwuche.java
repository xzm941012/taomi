package com.example.de.taomi2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.ResultUtil;
import com.example.de.taomi2.fragment.Fragment_shangping_gouwuche;


/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_shangping_gouwuche extends FragmentActivity {
    Fragment_shangping_gouwuche fm1=new Fragment_shangping_gouwuche();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shangping_gouwuche);
        initLayout();
        initListennner();
        initParam();
        FragmentTransaction ts = getSupportFragmentManager().beginTransaction();
        ts.replace(R.id.frameLayout, fm1).commit();
    }
    private void initListennner(){

    }
    private void initLayout(){

    }
    private void initParam(){

    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case ResultUtil.SHANGPING_XIADAN:
                finish();
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
