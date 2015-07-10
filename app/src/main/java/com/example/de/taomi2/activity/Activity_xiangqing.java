package com.example.de.taomi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.GouWuChe_shangping;
import com.example.de.taomi2.View.ListViewForScrollView;
import com.example.de.taomi2.adapter.EditSeeAdapter2;
import com.example.de.taomi2.adapter.ImageViewAdapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.http.PostUrl;
import com.example.de.taomi2.obj.Label_edit;
import com.example.de.taomi2.obj.ShangPingItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_xiangqing extends Activity {
    View headView;
    ImageView fenmian,touxiang;
    TextView title,jiage,username,buyNum;
    ViewPager headViewPager;
    ListView listView;
    String id;
    Gson gson=new Gson();
    private DisplayImageOptions defaultOptions;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private ProgressDialog dialog;
    ShangPingItem shangPingItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_xiangqing);
        LayoutInflater flater = LayoutInflater.from(Activity_xiangqing.this);
        dialog = new ProgressDialog(Activity_xiangqing.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().showImageOnFail(R.drawable.avatar_default).build();
        headView = flater.inflate(R.layout.label_huodong_head, null);
        id=getIntent().getStringExtra("id");
        initLayout();
        initListenner();
        MyTask2 myTask2=new MyTask2();
        myTask2.execute(id);

    }
    private void initListenner(){
        findViewById(R.id.baomingview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouWuChe_shangping.clearGouWuChe();
                GouWuChe_shangping.addItems(shangPingItem);
                MyApplication.dianzhangId=shangPingItem.getUserid();
                startActivity(new Intent(Activity_xiangqing.this,Activity_shangping_gouwuche.class));
            }
        });
        findViewById(R.id.xiaoxiLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouWuChe_shangping.addItems(shangPingItem);
                Toast.makeText(Activity_xiangqing.this,"已放入购物车",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Activity_xiangqing.this));
            }
        });
    }
    private void initLayout(){
        touxiang=(ImageView)headView.findViewById(R.id.imageView);
        headViewPager=(ViewPager)headView.findViewById(R.id.viewpager2);
        listView=(ListView)findViewById(R.id.listView3);
        listView.addHeaderView(headView);
        listView.setAdapter(null);
        title=(TextView)headView.findViewById(R.id.textView84);
        jiage=(TextView)headView.findViewById(R.id.textView59);
        username=(TextView)headView.findViewById(R.id.textView162);
        buyNum=(TextView)headView.findViewById(R.id.textView283);
        LayoutInflater inflater = getLayoutInflater();
        View views=inflater.inflate(R.layout.fragment_tupian, null);
        List<View>viewList=new ArrayList<>();
        viewList.add(views);
        ImageViewAdapter imageViewAdapter=new ImageViewAdapter(Activity_xiangqing.this,viewList);
        headViewPager.setAdapter(imageViewAdapter);
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
    class MyTask2 extends AsyncTask<String,Void,ShangPingItem> {

        @Override
        protected ShangPingItem doInBackground(String... params) {
            try {
                shangPingItem= Manage.viewShangpinById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return shangPingItem;
        }
        protected void onPostExecute(ShangPingItem shangPingItem1){
            dialog.dismiss();
            title.setText(shangPingItem1.getName());
            jiage.setText("￥"+shangPingItem1.getJiage()+"");
            username.setText(shangPingItem1.getUsername());
            buyNum.setText("销量："+shangPingItem1.getBuyNum());
            headViewPager.setAdapter(pagerAdapter);
            imageLoader.displayImage(PostUrl.getTouxiangPath(shangPingItem1.getUserid()), touxiang, defaultOptions);
            List<Label_edit> edits=gson.fromJson(shangPingItem1.getXiangqing(), new TypeToken<List<Label_edit>>() {
            }.getType());
            EditSeeAdapter2 editSeeAdapter2=new EditSeeAdapter2(Activity_xiangqing.this,edits);
            ((ListViewForScrollView)headView.findViewById(R.id.listView2)).setAdapter(editSeeAdapter2);
        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }
    }
    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            //container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // TODO Auto-generated method stub
            View views=LayoutInflater.from(Activity_xiangqing.this).inflate(R.layout.fragment_tupian, null);
            ImageView imageView=(ImageView)views.findViewById(R.id.imageView33);
            imageLoader.displayImage(PostUrl.getImageUrl(shangPingItem.getPictureUrl()), imageView);
            container.addView(views);
            return views;
        }
    };
}
