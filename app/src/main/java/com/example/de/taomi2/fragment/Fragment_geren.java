package com.example.de.taomi2.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.activity.Activity_dingdan;
import com.example.de.taomi2.activity.Activity_jiedan_online;
import com.example.de.taomi2.activity.Activity_login;
import com.example.de.taomi2.activity.Activity_shop;
import com.example.de.taomi2.activity.Activity_shop_qinshizhang;
import com.example.de.taomi2.http.PostUrl;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Fragment_geren extends Fragment {
    private DisplayImageOptions defaultOptions;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    final int RESULT_LOGIN=0x1;
    View view;
    ImageView touxiang;
    TextView tuichu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fragment_geren, container, false);
        initOption();
        initLayout();
        initListenner();
        initParam();
        return view;
    }
    private void initOption(){
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().showImageOnFail(R.drawable.avatar_default).build();
    }
    private void initLayout(){
        touxiang=(ImageView)view.findViewById(R.id.touxiang_pinglun);
        tuichu=(TextView)view.findViewById(R.id.textView92);
        if(MyApplication.user!=null){
            tuichu.setVisibility(View.VISIBLE);
            imageLoader.displayImage(PostUrl.getTouxiangPath(MyApplication.user.getId() + ""), touxiang, defaultOptions);
            ((TextView)view.findViewById(R.id.textView4)).setText(MyApplication.user.getName());
        }
    }
    private void initListenner(){
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=MyApplication.myApplication.getSharedPreferences();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user","");
                editor.commit();
                getActivity().finish();
            }
        });
        view.findViewById(R.id.relativeLayout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.user!=null) {
                    startActivity(new Intent(getActivity(), Activity_dingdan.class));
                }else{
                    Toast.makeText(getActivity(), "还没登陆呢", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.user==null) {
                    startActivityForResult(new Intent(getActivity(), Activity_login.class), RESULT_LOGIN);
                }
            }
        });
        view.findViewById(R.id.relativeLayout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.user!=null){
                    if(MyApplication.user.getShenfen()==1){
                        startActivity(new Intent(getActivity(),Activity_shop_qinshizhang.class));
                    }else if(MyApplication.user.getShenfen()==2){
                        startActivity(new Intent(getActivity(),Activity_shop.class));
                    }
                }
            }
        });
        view.findViewById(R.id.relativeLayout19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.user!=null) {
                    startActivityForResult(new Intent(getActivity(), Activity_jiedan_online.class),RESULT_LOGIN);
                }else{
                    Toast.makeText(getActivity(), "还没登陆呢", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void  initParam(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                if(MyApplication.user!=null){
                    tuichu.setVisibility(View.VISIBLE);
                    imageLoader.displayImage(PostUrl.getTouxiangPath(MyApplication.user.getId() + ""), touxiang, defaultOptions);
                    ((TextView)view.findViewById(R.id.textView4)).setText(MyApplication.user.getName());
                }
        }
    }
}
