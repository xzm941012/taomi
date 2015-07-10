package com.example.de.taomi2.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.Encoding;
import com.example.de.taomi2.Util.GouWuChe_shangping;
import com.example.de.taomi2.Util.ResultUtil;
import com.example.de.taomi2.http.HttpUtil;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.DingDan;
import com.example.de.taomi2.obj.ShangPingItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pingplusplus.android.PingppLog;
import com.pingplusplus.libone.PayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_shangping_xiadan extends FragmentActivity {
    EditText address,lianxiStyle;
    TextView liuyan;
    DingDan dingDan;
    Gson gson=new Gson();
    RadioButton rb1,rb2;
    private ProgressDialog dialog;
    public static final String URL = HttpUtil.BASE_URL + "payAction.action";
    int onlinePay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xiadan);
        onlinePay=getIntent().getIntExtra("onlinePay",0);
        // 设置要使用的支付方式
        PayActivity.SHOW_CHANNEL_WECHAT = true;
        PayActivity.SHOW_CHANNEL_UPMP = true;
        PayActivity.SHOW_CHANNEL_BFB = true;
        PayActivity.SHOW_CHANNEL_ALIPAY = true;
        // PingPP日志开关
        PingppLog.DEBUG = true;
        dingDan=(DingDan)getIntent().getSerializableExtra("dingdan");
        dialog = new ProgressDialog(Activity_shangping_xiadan.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initLayout();
        initListenner();
    }
    private void initLayout(){
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        address=(EditText)findViewById(R.id.editText);
        lianxiStyle=(EditText)findViewById(R.id.editText2);
        liuyan=(TextView)findViewById(R.id.textView31);
        if(onlinePay==0){
            findViewById(R.id.radioButton2).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView30).setVisibility(View.VISIBLE);
        }
    }
    private void initListenner(){
        findViewById(R.id.relativeLayout11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) address.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        findViewById(R.id.relativeLayout12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lianxiStyle.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) lianxiStyle.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        findViewById(R.id.relativeLayout14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_shangping_xiadan.this,Activity_editliuyan.class),1);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(address.getText().toString().equals("")){
                    Toast.makeText(Activity_shangping_xiadan.this,"写个地址啊",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(lianxiStyle.getText().toString().equals("")){
                    Toast.makeText(Activity_shangping_xiadan.this,"联系方式啊",Toast.LENGTH_SHORT).show();
                    return;
                }
                dingDan.setAddress(address.getText().toString().trim());
                dingDan.setBuytel(lianxiStyle.getText().toString().trim());
                dingDan.setBeizhu(liuyan.getText().toString().trim());
                if(rb1.isChecked()) {
                    dingDan.setPaytype(0);
                    MyTask myTask = new MyTask();
                    myTask.execute("");
                }else{
                    dingDan.setPaytype(1);
                    // 产生个订单号
                    String orderNo = new SimpleDateFormat("yyyyMMddhhmmss")
                            .format(new Date());

                    // 计算总金额（以分为单位）
                    int amount = 0;
                    JSONArray billList = new JSONArray();
                    amount=(int)(Double.parseDouble(dingDan.getCounts())*100);
                    List<ShangPingItem> shangPingItemList=gson.fromJson(dingDan.getShangPing(),new TypeToken<List<ShangPingItem>>(){}.getType());
                    for(ShangPingItem shangPingItem:shangPingItemList){
                        billList.put(shangPingItem.getName() + " x " + shangPingItem.getSelectNum());
                    }
                    JSONObject bill = new JSONObject();
                    JSONObject displayItem = new JSONObject();
                    try {
                        displayItem.put("name", "商品");
                        displayItem.put("contents", billList);
                        JSONArray display = new JSONArray();
                        display.put(displayItem);
                        bill.put("order_no", orderNo+ MyApplication.user.getId());
                        bill.put("amount", amount);
                        bill.put("display", display);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // 发起支付
                    PayActivity.CallPayActivity(Activity_shangping_xiadan.this, bill.toString(), URL);
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PayActivity.PAYACTIVITY_REQUEST_CODE) {
            if (resultCode == PayActivity.PAYACTIVITY_RESULT_CODE) {
                if(data.getExtras().getInt("code")==1){
                    MyTask myTask = new MyTask();
                    myTask.execute("");
                }else if(data.getExtras().getInt("code")==0){

                }else if(data.getExtras().getInt("code")==-1){
                    Toast.makeText(this,"支付失败",Toast.LENGTH_LONG).show();
                }else if(data.getExtras().getInt("code")==-2){
                    Toast.makeText(this,"用户自定义错误",Toast.LENGTH_LONG).show();
                }

            }
        }
        switch (resultCode){
            case 1:
                String liuyan1=data.getStringExtra("liuyan");
                liuyan.setText(liuyan1);
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
    class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String result=null;
            try {
                result= Manage.pushShangpingDingdan(dingDan);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        protected void onPostExecute(String result){
            dialog.dismiss();
            System.out.println("result"+result);
            System.out.println("Encode"+ Encoding.getEncoding(result));

            if(result.trim().equals("true")){
                Toast.makeText(Activity_shangping_xiadan.this,"下单成功，预计20分钟到达",Toast.LENGTH_SHORT).show();
                GouWuChe_shangping.clearGouWuChe();
                setResult(ResultUtil.SHANGPING_XIADAN);
                finish();
            }else{
                Toast.makeText(Activity_shangping_xiadan.this,"未知错误",Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }

    }
}
