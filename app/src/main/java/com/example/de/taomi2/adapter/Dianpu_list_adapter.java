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
import com.example.de.taomi2.obj.Dianpu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class Dianpu_list_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public List<Dianpu> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public Dianpu_list_adapter(Context context, List<Dianpu> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Dianpu getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Dianpu item = getItem(position);
        final ViewHolder holder ;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.labek_dianpu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(item.getName());
        holder.buyNum.setText("已售"+item.getBuyNum()+"件");
        if(item.getZhuangtai().equals("打烊中")) {
            holder.zhuangtai.setText(item.getZhuangtai());
        }
        holder.content.setText(item.getContent());


        return convertView;
    }
    public final class ViewHolder {

        TextView name;
        TextView content;
        ImageView fenmian;
        TextView zhuangtai;
        TextView buyNum;

        public ViewHolder(View view){
            buyNum=(TextView) view.findViewById(R.id.textView91);
            name=(TextView) view.findViewById(R.id.tv_goods_title);
            fenmian=(ImageView) view.findViewById(R.id.iv_goods_img);
            zhuangtai=(TextView) view.findViewById(R.id.textView90);
            content=(TextView) view.findViewById(R.id.tv_goods_sales_vol);
        }
    }
}
