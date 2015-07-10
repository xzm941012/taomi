package com.example.de.taomi2.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.de.taomi2.R;
import com.example.de.taomi2.fragment.Fragment_gouwuche;
import com.example.de.taomi2.obj.ShangPingItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class Adapter_moban extends BaseAdapter {

    private LayoutInflater mInflater;
    public List<ShangPingItem> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public Adapter_moban(Context context, List<ShangPingItem> data, Fragment_gouwuche fragment_chaoshi) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ShangPingItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ShangPingItem item = getItem(position);
        final ViewHolder holder ;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.goods_home_lv_right_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }
    public final class ViewHolder {

        TextView name;
        TextView buyNum;
        TextView selectNum;
        ImageView fenmian;
        TextView jiage;
        View add;
        View reduce;

        public ViewHolder(View view){
            add=view.findViewById(R.id.iv_goods_add);
            reduce=view.findViewById(R.id.iv_goods_reduction);
            buyNum=(TextView) view.findViewById(R.id.tv_goods_sales_vol);
            name=(TextView) view.findViewById(R.id.tv_goods_title);
            selectNum=(TextView) view.findViewById(R.id.tv_goods_select_count);
            fenmian=(ImageView) view.findViewById(R.id.iv_goods_img);
            jiage=(TextView) view.findViewById(R.id.tv_goods_price);
        }
    }
}
