package com.example.de.taomi2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.R;
import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.Util.GouWuChe;
import com.example.de.taomi2.Util.ResultUtil;
import com.example.de.taomi2.fragment.Fragment_chaoshi;
import com.example.de.taomi2.fragment.Fragment_geren;
import com.example.de.taomi2.fragment.Fragment_gouwuche;
import com.example.de.taomi2.fragment.Fragment_guangjie;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
//晚上资料只是确认自己的标签，更具标签选择活动标签

    Fragment_chaoshi fm1=new Fragment_chaoshi();
    Fragment_guangjie fm2=new Fragment_guangjie();
    Fragment_gouwuche fm3=new Fragment_gouwuche();
    Fragment_geren fm4=new Fragment_geren();
    private static TextView countsText;

    int twoThouch=0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if(MyApplication.myApplication.school.equals("")||MyApplication.myApplication.loudong.equals("")){
            startActivity(new Intent(MainActivity.this,Activity_choose_school.class));
            finish();
        }else{
            init();
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }

    public static void setAllCounds(){
        int counds= GouWuChe.nums;
        countsText.setVisibility(View.VISIBLE);
        countsText.setText(counds+"");
        if(counds==0){
            countsText.setVisibility(View.INVISIBLE);
        }
    }
    private void init(){
        initMessage();
        initUser();
        initLayout();
        initListener();
        /*
        FragmentTransaction ts=getSupportFragmentManager().beginTransaction();
        ts.replace(R.id.frameLayout, fm3) .commit();
        */
        findViewById(R.id.bt1).performClick();
    }
    private void initUser(){

    }
    private void initLayout(){
        countsText=(TextView)findViewById(R.id.textView2);
    }
    private void initMessage(){

    }
    private void initListener(){
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        /*
        findViewById(R.id.fabuhuodong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    startActivity(new Intent(MainActivity.this, com.example.topnewgrid.choosephotos.choosephotos.MainActivity.class));
                }else{
                    startActivityForResult(new Intent(MainActivity.this,Activity_login.class),1);
                }
            }
        });
        findViewById(R.id.textView88).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    startActivityForResult(new Intent(MainActivity.this, Activity_fabu_xuqiu.class),1);
                }else{
                    startActivityForResult(new Intent(MainActivity.this,Activity_login.class),1);
                }
            }
        });
        */
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case ResultUtil.CHOOSE_SCHOOL_RIGTH:
                init();
                break;
            case ResultUtil.CHAOSHI_XIADAN:
                countsText.setVisibility(View.INVISIBLE);
                countsText.setText("0");
                break;
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                if(i!=1) {
                    i=1;
                    ((ImageView) findViewById(R.id.imageView126)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_processing_outline_press));
                    ((TextView) findViewById(R.id.textView139)).setTextColor(getResources().getColor(R.color.bt));
                    ((ImageView) findViewById(R.id.imageView128)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_multipe_outline));
                    ((TextView) findViewById(R.id.textView140)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView127)).setImageDrawable(getResources().getDrawable(R.drawable.ic_compass_outline));
                    ((TextView) findViewById(R.id.textView137)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView130)).setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
                    ((TextView) findViewById(R.id.textView138)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts = getSupportFragmentManager().beginTransaction();
                    ts.replace(R.id.frameLayout, fm1).commit();
                }
                break;
            case R.id.bt2:
                if(i!=2) {
                    i = 2;
                    ((ImageView) findViewById(R.id.imageView126)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_processing_outline));
                    ((TextView) findViewById(R.id.textView139)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView128)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_multipe_outline_press));
                    ((TextView) findViewById(R.id.textView140)).setTextColor(getResources().getColor(R.color.bt));
                    ((ImageView) findViewById(R.id.imageView127)).setImageDrawable(getResources().getDrawable(R.drawable.ic_compass_outline));
                    ((TextView) findViewById(R.id.textView137)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView130)).setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
                    ((TextView) findViewById(R.id.textView138)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts2 = getSupportFragmentManager().beginTransaction();
                    ts2.replace(R.id.frameLayout, fm2).commit();
                }
                break;
            case  R.id.bt3:
                if(i!=3) {
                    i = 3;
                    ((ImageView) findViewById(R.id.imageView126)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_processing_outline));
                    ((TextView) findViewById(R.id.textView139)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView128)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_multipe_outline));
                    ((TextView) findViewById(R.id.textView140)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView127)).setImageDrawable(getResources().getDrawable(R.drawable.ic_compass_outline_press));
                    ((TextView) findViewById(R.id.textView137)).setTextColor(getResources().getColor(R.color.bt));
                    ((ImageView) findViewById(R.id.imageView130)).setImageDrawable(getResources().getDrawable(R.drawable.ic_account));
                    ((TextView) findViewById(R.id.textView138)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    FragmentTransaction ts3 = getSupportFragmentManager().beginTransaction();
                    ts3.replace(R.id.frameLayout, fm3).commit();
                }
                break;
            case R.id.bt4:
                if(i!=4) {
                    i = 4;
                    ((ImageView) findViewById(R.id.imageView126)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_processing_outline));
                    ((TextView) findViewById(R.id.textView139)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView128)).setImageDrawable(getResources().getDrawable(R.drawable.ic_comment_multipe_outline));
                    ((TextView) findViewById(R.id.textView140)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView127)).setImageDrawable(getResources().getDrawable(R.drawable.ic_compass_outline));
                    ((TextView) findViewById(R.id.textView137)).setTextColor(getResources().getColor(R.color.subscribe_item_text_color_normal_night));
                    ((ImageView) findViewById(R.id.imageView130)).setImageDrawable(getResources().getDrawable(R.drawable.ic_account_press));
                    ((TextView) findViewById(R.id.textView138)).setTextColor(getResources().getColor(R.color.bt));
                    FragmentTransaction ts4 = getSupportFragmentManager().beginTransaction();
                    ts4.replace(R.id.frameLayout, fm4).commit();
                }
                break;

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //do something...
            if(twoThouch==1){
                finish();
            }else{
                Toast.makeText(MainActivity.this,"再次点击返回键退出",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        twoThouch=1;
                        try {
                            Thread.sleep(3000);
                            twoThouch=0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}