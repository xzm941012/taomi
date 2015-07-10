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
import com.example.de.taomi2.http.PostUrl;
import com.example.de.taomi2.obj.ShangPingItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class Guangjie_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public List<ShangPingItem> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().showImageOnFail(R.drawable.qiang_btn_focus).build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public Guangjie_adapter(Context context, List<ShangPingItem> data) {
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
            convertView = mInflater.inflate(R.layout.labek_guangjie_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(PostUrl.getSmallFengmian(item.getPictureUrl()), holder.fenmian, defaultOptions);
        holder.name.setText(item.getName());
        holder.buyNum.setText("已售"+item.getBuyNum()+"件");
        holder.jiage.setText("￥"+item.getJiage());


        return convertView;
    }
    public final class ViewHolder {

        TextView name;
        TextView buyNum;
        ImageView fenmian;
        TextView jiage;

        public ViewHolder(View view){
            buyNum=(TextView) view.findViewById(R.id.tv_goods_sales_vol);
            name=(TextView) view.findViewById(R.id.tv_goods_title);
            fenmian=(ImageView) view.findViewById(R.id.iv_goods_img);
            jiage=(TextView) view.findViewById(R.id.tv_goods_price);
        }
    }
}
