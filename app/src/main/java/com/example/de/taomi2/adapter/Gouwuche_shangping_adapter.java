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
import com.example.de.taomi2.fragment.Fragment_shangping_gouwuche;
import com.example.de.taomi2.http.PostUrl;
import com.example.de.taomi2.obj.ShangPingItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class Gouwuche_shangping_adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    Fragment_shangping_gouwuche fragment_chaoshi;
    public List<ShangPingItem> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().showImageOnFail(R.drawable.qiang_btn_focus).build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;

    public Gouwuche_shangping_adapter(Context context, List<ShangPingItem> data, Fragment_shangping_gouwuche fragment_chaoshi) {
        this.fragment_chaoshi=fragment_chaoshi;
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
        imageLoader.displayImage(PostUrl.getSmallFengmian(item.getPictureUrl()), holder.fenmian, defaultOptions);
        holder.reduce.setVisibility(View.INVISIBLE);
        if(item.getSelectNum()!=0){
            holder.reduce.setVisibility(View.VISIBLE);
        }
        holder.name.setText(item.getName());
        holder.buyNum.setText("已售"+item.getBuyNum()+"件");
        holder.selectNum.setText(item.getSelectNum()+"");
        holder.jiage.setText("￥"+item.getJiage());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.reduce.setVisibility(View.VISIBLE);
                System.out.println("添加1个,总数:"+(item.getSelectNum()+1));

                fragment_chaoshi.itemAdd(item);

                holder.selectNum.setText(item.getSelectNum()+"");
            }
        });
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectNum=item.getSelectNum();

                fragment_chaoshi.itemReduce(item);
                if(selectNum==1){
                    holder.reduce.setVisibility(View.INVISIBLE);
                }
                holder.selectNum.setText(item.getSelectNum()+"");
            }
        });

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
