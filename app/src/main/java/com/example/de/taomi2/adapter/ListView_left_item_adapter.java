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
import com.example.de.taomi2.obj.Listview_left_item;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class ListView_left_item_adapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public List<Listview_left_item> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    public List<ImageView> imageViews;

    private Context context;

    public ListView_left_item_adapter(Context context, List<Listview_left_item> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context=context;
        imageViews=new ArrayList<ImageView>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Listview_left_item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Listview_left_item item = getItem(position);
        final ViewHolder holder ;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_left_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.numView.setVisibility(View.INVISIBLE);
        convertView.setSelected(false);
        if(item.getSelect()==1){
            System.out.println("这个曾经被选中");
            convertView.setSelected(true);
        }
        holder.num.setText(item.getNum()+"");
        if(item.getNum()!=0){
            holder.numView.setVisibility(View.VISIBLE);
        }
        holder.name.setText(item.getName());

        return convertView;
    }
    public final class ViewHolder {

        TextView name;
        TextView num;
        View itemView;
        View numView;

        public ViewHolder(View view){
            numView=view.findViewById(R.id.tv_goods_count);
            itemView=view.findViewById(R.id.itemLayout);
            num=(TextView) view.findViewById(R.id.tv_goods_count);
            name=(TextView) view.findViewById(R.id.tv_goods_type_name);
        }
    }
}
