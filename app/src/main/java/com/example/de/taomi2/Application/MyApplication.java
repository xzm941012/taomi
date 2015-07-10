package com.example.de.taomi2.Application;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.de.taomi2.R;
import com.example.de.taomi2.obj.Label_edit;
import com.example.de.taomi2.obj.User;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by 真爱de仙 on 2015/6/22.
 */
public class MyApplication extends Application {
    List<Label_edit> edits=new ArrayList<>();
    SharedPreferences sharedPreferences;
    public  static User user;
    public static MyApplication myApplication;
    public static String school;
    public static String loudong;
    public static String qinShiShangId;
    public static String dianzhangId;
    public static String type;
    Gson gson=new Gson();

    @Override
    public void onCreate() {
        super.onCreate();
        initOption();
        myApplication=this;
        sharedPreferences=getSharedPreferences("option",
                Activity.MODE_PRIVATE);
        initParam();
    }
    private void initOption(){
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().showImageOnFail(R.color.brownss).displayer(new SimpleBitmapDisplayer()).showImageOnLoading(R.color.brownss).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private void initParam(){
        school=sharedPreferences.getString("school","");
        loudong=sharedPreferences.getString("loudong","");
        qinShiShangId=sharedPreferences.getString("qinShiShangId","");
        String users=sharedPreferences.getString("user","");
        if(!users.equals("")){
            user=gson.fromJson(users,User.class);
            user.setShenfen(2);
            JPushInterface.setAlias(MyApplication.myApplication, user.getId() + "", new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> strings) {
                    switch (i) {
                        case 0:
                            System.out.println("设置成功");
                            break;
                    }
                }
            });
            System.out.println("读取用户:"+user.getName());
        }
    }

    public List<Label_edit> getEdits() {
        return edits;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setEdits(List<Label_edit> edits) {
        this.edits = edits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        JPushInterface.setAlias(MyApplication.myApplication, user.getId() + "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> strings) {
                switch (i) {
                    case 0:
                        System.out.println("设置成功");
                        break;
                }
            }
        });
    }
}
