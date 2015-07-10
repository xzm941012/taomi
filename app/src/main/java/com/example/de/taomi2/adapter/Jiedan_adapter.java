package com.example.de.taomi2.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.de.taomi2.R;
import com.example.de.taomi2.obj.DingDan;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class Jiedan_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public List<DingDan> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public Jiedan_adapter(Context context, List<DingDan> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public DingDan getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DingDan item = getItem(position);
        final ViewHolder holder ;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.label_dingdan_mai_dialog, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.shangping.setText(item.getShangPing());
        holder.address.setText(item.getAddress());
        holder.beizhu.setText(item.getBeizhu());
        holder.tel.setText(item.getBuytel());
        holder.time.setText(item.getTime());
        holder.counts.setText("￥"+item.getCounts()+" "+"("+(item.getPaytype()==1?"在线支付":"货到付款")+")");
        if(item.getType().equals("接单中")){
            holder.jiedan.setVisibility(View.VISIBLE);
        }else{
            holder.jiedan.setVisibility(View.GONE);
        }

        return convertView;
    }
    public final class ViewHolder {
        View jiedan;
        TextView shangping;
        TextView address;
        TextView tel;
        TextView beizhu;
        TextView counts;

        TextView time;


        public ViewHolder(View view){
            jiedan=view.findViewById(R.id.jiedanview);
            time=(TextView) view.findViewById(R.id.textView56);
            shangping=(TextView) view.findViewById(R.id.textView42);
            address=(TextView) view.findViewById(R.id.textView45);
            tel=(TextView) view.findViewById(R.id.textView44);
            counts=(TextView) view.findViewById(R.id.textView43);
            beizhu=(TextView) view.findViewById(R.id.textView46);
        }
    }
}
