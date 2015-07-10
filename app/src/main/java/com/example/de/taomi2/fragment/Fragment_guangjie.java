package com.example.de.taomi2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.de.taomi2.R;
import com.example.de.taomi2.activity.Activity_choose_school;
import com.example.de.taomi2.activity.Activity_guangjie_list;
import com.example.de.taomi2.adapter.ImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Fragment_guangjie extends Fragment implements View.OnClickListener {
    View view;
    ViewPager imageViewpager;
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
        view = inflater.inflate(R.layout.fragment_guangjie, container, false);
        initLayout();
        initListenner();
        initParam();
        return view;
    }
    private void initLayout(){
        imageViewpager=(ViewPager)view.findViewById(R.id.viewpager2);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View views=inflater.inflate(R.layout.fragment_tupian, null);
        View views1=inflater.inflate(R.layout.fragment_tupian, null);
        View views2=inflater.inflate(R.layout.fragment_tupian, null);
        List<View> viewList=new ArrayList<>();
        viewList.add(views);
        viewList.add(views1);
        viewList.add(views2);
        ImageViewAdapter imageViewAdapter=new ImageViewAdapter(getActivity(),viewList);
        imageViewpager.setAdapter(imageViewAdapter);

    }
    private void initListenner(){
        view.findViewById(R.id.view1).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun2).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun3).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun4).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun5).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun6).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun7).setOnClickListener(this);
        view.findViewById(R.id.touxiang_pinglun8).setOnClickListener(this);
    }
    private void  initParam(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touxiang_pinglun:
                Intent intent=new Intent(getActivity(),Activity_guangjie_list.class);
                intent.putExtra("type","电脑配件");
                startActivity(intent);
                break;
            case R.id.touxiang_pinglun2:
                Intent intent2=new Intent(getActivity(),Activity_guangjie_list.class);
                intent2.putExtra("type","早餐");
                startActivity(intent2);
                break;
            case R.id.touxiang_pinglun3:
                Intent intent3=new Intent(getActivity(),Activity_guangjie_list.class);
                intent3.putExtra("type","水果");
                startActivity(intent3);
                break;
            case R.id.touxiang_pinglun4:
                Intent intent4=new Intent(getActivity(),Activity_guangjie_list.class);
                intent4.putExtra("type","宵夜");
                startActivity(intent4);
                break;
            case R.id.touxiang_pinglun5:
                Intent intent5=new Intent(getActivity(),Activity_guangjie_list.class);
                intent5.putExtra("type","快递");
                startActivity(intent5);
                break;
            case R.id.touxiang_pinglun6:
                Intent intent6=new Intent(getActivity(),Activity_guangjie_list.class);
                intent6.putExtra("type","零食");
                startActivity(intent6);
                break;
            case R.id.touxiang_pinglun7:
                Intent intent7=new Intent(getActivity(),Activity_guangjie_list.class);
                intent7.putExtra("type","干洗");
                startActivity(intent7);
                break;
            case R.id.touxiang_pinglun8:
                Intent intent8=new Intent(getActivity(),Activity_guangjie_list.class);
                intent8.putExtra("type","美食");
                startActivity(intent8);
                break;
            case R.id.view1:
                startActivityForResult(new Intent(getActivity(), Activity_choose_school.class),1);
                break;
        }
    }
}
