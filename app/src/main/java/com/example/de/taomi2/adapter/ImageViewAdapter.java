package com.example.de.taomi2.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class ImageViewAdapter extends PagerAdapter {
    Context context;
    List<View> viewList;

    public ImageViewAdapter(Context context,List<View> viewList) {
        this.viewList=viewList;
        this.context=context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return viewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // TODO Auto-generated method stub
        View views=viewList.get(position);
        container.addView(views);
        return views;
    }
}
