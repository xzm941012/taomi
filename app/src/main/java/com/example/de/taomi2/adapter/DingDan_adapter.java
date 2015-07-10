package com.example.de.taomi2.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.TimeUtil;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.DingDan;
import com.example.de.taomi2.obj.ShangPingItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class DingDan_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Gson gson=new Gson();
    public List<DingDan> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public DingDan_adapter(Context context, List<DingDan> data) {
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
            convertView = mInflater.inflate(R.layout.label_dingdan, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println("item:"+item.toString());
        List<ShangPingItem> shangPingItems=gson.fromJson(item.getShangPing(), new TypeToken<List<ShangPingItem>>(){}.getType());
        StringBuffer sb=new StringBuffer();
        for(ShangPingItem spi:shangPingItems){
            sb.append(spi.getName()+"x"+spi.getSelectNum()+" ");
        }
        holder.jiedan.setVisibility(View.GONE);
        holder.shangping.setText(sb);
        holder.address.setText(item.getAddress());
        holder.beizhu.setText(item.getBeizhu());
        holder.tel.setText(item.getBuytel());
        holder.texttype.setText(item.getType());
        if((item.getMaiid()+"").equals(MyApplication.user.getId()+"") ){
        if(item.getType().equals("接单中")) {
            holder.texttype.setTextColor(context.getResources().getColor(R.color.colorred));
            holder.imagetype.setBackgroundColor(context.getResources().getColor(R.color.colorred));
            holder.jiedan.setVisibility(View.VISIBLE);
            holder.jiedan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.texttype.setTextColor(context.getResources().getColor(R.color.colorblue));
                    holder.imagetype.setBackgroundColor(context.getResources().getColor(R.color.colorblue));
                    MyTask myTask = new MyTask();
                    myTask.execute(item.getId() + "");
                }
            });
        }else{
            holder.texttype.setTextColor(context.getResources().getColor(R.color.colorblue));
            holder.imagetype.setBackgroundColor(context.getResources().getColor(R.color.colorblue));
        }
        }
        holder.time.setText(TimeUtil.getInterval(item.getTime()));
        holder.counts.setText("￥"+item.getCounts()+" "+"("+(item.getPaytype()==1?"在线支付":"货到付款")+")");

        return convertView;
    }
    public final class ViewHolder {

        TextView shangping;
        TextView address;
        TextView tel;
        TextView beizhu;
        TextView counts;
        TextView texttype;
        TextView time;
        ImageView imagetype;
        View jiedan;

        public ViewHolder(View view){
            jiedan=view.findViewById(R.id.jiedanlayout);
            imagetype=(ImageView) view.findViewById(R.id.imageView48);
            texttype=(TextView) view.findViewById(R.id.textView57);
            time=(TextView) view.findViewById(R.id.textView58);
            shangping=(TextView) view.findViewById(R.id.textView42);
            address=(TextView) view.findViewById(R.id.textView45);
            tel=(TextView) view.findViewById(R.id.textView44);
            counts=(TextView) view.findViewById(R.id.textView43);
            beizhu=(TextView) view.findViewById(R.id.textView46);
        }
    }
    class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String id=params[0];
            try {
                Manage.jieDan(id);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return id;
        }
        protected void onPostExecute(String dingdanid){
            for(DingDan dingDan:mItems){
                if(dingDan.getId()== Integer.parseInt(dingdanid)){
                    dingDan.setType("已接单");
                    notifyDataSetChanged();
                    break;
                }
            }
        }
        @Override
        protected void onPreExecute(){
        }
    }

}
