package com.example.de.taomi2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.de.taomi2.R;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Fragment_moban extends Fragment {
    View view;
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

    }
    private void initListenner(){

    }
    private void  initParam(){

    }
}
