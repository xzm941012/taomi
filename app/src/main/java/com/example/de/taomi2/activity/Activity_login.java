package com.example.de.taomi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.User;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 真爱de仙 on 2015/1/18.
 */
public class Activity_login extends Activity {

    private final int RESULT_REGIST=1;

    private Tencent mTencent;

    private TextView registButton;
    private EditText email,password;
    private ImageView tuichu;
    private Button login;
    ImageView qqLogin;

    public static String mAppid;
    public static String openidString;
    public static String nicknameString;
    public static String TAG="Activity_login";
    public static String sex;
    public static String city;
    public static String province;
    private String name;
    Bitmap bitmap = null;

    private ProgressDialog dialog;
    private Handler dialogHandler;



    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initHandler();
        initLayout();
        initListener();

    }
    private void initHandler(){
        dialogHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        dialog.show();
                        break;
                    case 1:
                        dialog.dismiss();
                        break;
                }
            }
        };
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        Toast.makeText(Activity_login.this,"登录失败，请检查账号密码",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    private void initLayout(){
        qqLogin=(ImageView)findViewById(R.id.imageView22);
        email=(EditText)findViewById(R.id.edt);
        password=(EditText)findViewById(R.id.edt_password);
        login=(Button)findViewById(R.id.button6);
        tuichu=(ImageView)findViewById(R.id.imageView38);
        registButton=(TextView)findViewById(R.id.textView23);

    }
    private void initListener(){
        findViewById(R.id.imageView38).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qqLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "正在登录...", Toast.LENGTH_LONG).show();
                Context context = v.getContext();
                Animation shake = AnimationUtils.loadAnimation(context,
                        R.anim.shake);
                LoginQQ();
                v.startAnimation(shake);

            }
        });
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*

                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

// 提交用户信息
                            Intent intent=new Intent();
                            intent.setClass(Activity_login.this,Activity_regist.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("phone",phone);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,1);
                        }
                    }
                });
                registerPage.show(Activity_login.this);
                */
            }

        });
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask=new MyTask();
                myTask.execute("");
            }
        });
    }
    public void LoginQQ() {
        //这里的APP_ID请换成你应用申请的APP_ID，我这里使用的是DEMO中官方提供的测试APP_ID 222222
        mAppid = 222222+"";
        //第一个参数就是上面所说的申请的APPID，第二个是全局的Context上下文，这句话实现了调用QQ登录
        mTencent = Tencent.createInstance(mAppid, getApplicationContext());
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mTencent.login(Activity_login.this,"all", new BaseUiListener());

    }
    private void initUserParam(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    private class BaseUiListener implements IUiListener {

        public void onCancel() {
            // TODO Auto-generated method stub

        }
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            dialogHandler.sendEmptyMessage(0);
            try {
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                Log.e(TAG, "-------------" + response.toString());
                openidString = ((JSONObject) response).getString("openid");


                Log.e(openidString, "-------------"+openidString);
                //access_token= ((JSONObject) response).getString("access_token");				//expires_in = ((JSONObject) response).getString("expires_in");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /**到此已经获得OpneID以及其他你想获得的内容了
             QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
             sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
             如何得到这个UserInfo类呢？  */
            QQToken qqToken = mTencent.getQQToken();
            UserInfo info = new UserInfo(getApplicationContext(), qqToken);
            //这样我们就拿到这个类了，之后的操作就跟上面的一样了，同样是解析JSON
            info.getUserInfo(new IUiListener() {

                public void onComplete(final Object response) {
                    // TODO Auto-generated method stub
                    Log.e(TAG, "---------------111111");
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    dialogHandler.sendEmptyMessage(0);
                    //mHandler.sendMessage(msg);
                    try {
                        sex=((JSONObject) response).getString("gender");
                        province=((JSONObject) response).getString("province");
                        city=((JSONObject) response).getString("city");
                        name=((JSONObject) response).getString("nickname");
                        System.out.println("qqlogin:"+sex+" "+province+" "+city+""+name);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }).start();

                    } catch (JSONException e1) {

                        e1.printStackTrace();
                    }
                    Log.e(TAG, "-----111---"+response.toString());
                    /**由于图片需要下载所以这里使用了线程，如果是想获得其他文字信息直接
                     * 在mHandler里进行操作
                     *
                     */

                }
                public void onCancel() {
                    Log.e(TAG, "--------------111112");
                    // TODO Auto-generated method stub
                }
                public void onError(UiError arg0) {
                    // TODO Auto-generated method stub
                    Log.e(TAG, "-111113"+":"+arg0);
                }

            });

        }

        public void onError(UiError arg0) {
            // TODO Auto-generated method stub

        }

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
    class MyTask extends AsyncTask<String,Void,User> {

        @Override
        protected User doInBackground(String... params) {
            User user=null;
            try {
                user= Manage.login(email.getText().toString().trim(), password.getText().toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }
        protected void onPostExecute(User user){
            if(user==null){
                Toast.makeText(Activity_login.this,"登陆失败，请检查账号密码",Toast.LENGTH_SHORT).show();
            }else{
                MyApplication.myApplication.setUser(user);
                SharedPreferences sharedPreferences=MyApplication.myApplication.getSharedPreferences();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user",new Gson().toJson(user));
                editor.apply();
                setResult(1);
                finish();
            }

        }
        @Override
        protected void onPreExecute(){
        }
    }

}
