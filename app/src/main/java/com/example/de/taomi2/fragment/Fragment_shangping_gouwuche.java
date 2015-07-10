package com.example.de.taomi2.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.GouWuChe_shangping;
import com.example.de.taomi2.activity.Activity_login;
import com.example.de.taomi2.activity.Activity_shangping_xiadan;
import com.example.de.taomi2.activity.MainActivity;
import com.example.de.taomi2.adapter.Gouwuche_shangping_adapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.DingDan;
import com.example.de.taomi2.obj.ShangPingItem;
import com.example.de.taomi2.obj.User;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Fragment_shangping_gouwuche extends Fragment {
    public static Fragment_shangping_gouwuche fragment_chaoshi;
    View view;
    Button allCounts;
    ListView listview_right;
    User user;
    List<ShangPingItem> shangPingItemList=new ArrayList<>();
    Gouwuche_shangping_adapter listView_right_item_adapter;
    private ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        System.out.println("ExampleFragment--onCreate");
        fragment_chaoshi=this;
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
        view = inflater.inflate(R.layout.fragment_shangping_gouwuche, container, false);
        dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initLayout();
        initListenner();
        initParam();
        return view;
    }
    public void itemAdd(ShangPingItem item){
        String name=item.getStyle();

        GouWuChe_shangping.addItems(item);

        allCounts.setText("合计￥"+GouWuChe_shangping.allCounts+"去结算");
       // MainActivity.setAllCounds();
    }
    public void itemReduce(ShangPingItem item){
        String name=item.getStyle();

        GouWuChe_shangping.reduceItems(item);

        allCounts.setText("合计￥"+GouWuChe_shangping.allCounts+"去结算");
        MainActivity.setAllCounds();
    }
    private void initParam(){
        /*
        shangPingItemList= GouWuChe_shangping.shangPingItemList;
        listView_right_item_adapter=new Gouwuche_shangping_adapter(getActivity(),shangPingItemList,fragment_chaoshi);
        listview_right.setAdapter(listView_right_item_adapter);
        */
        MyTask myTask=new MyTask();
        myTask.execute();
        allCounts.setText("合计￥"+GouWuChe_shangping.allCounts+"去结算");

    }
    private void initLayout(){
        listview_right=(ListView)view.findViewById(R.id.plv_home_right);
        allCounts=(Button)view.findViewById(R.id.button2);
        allCounts.setText("合计￥"+GouWuChe_shangping.allCounts+"去结算");
    }
    private void initListenner(){
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.user==null){
                    startActivity(new Intent(getActivity(), Activity_login.class));
                }else{
                    List<ShangPingItem> shangPingItemList2=new ArrayList<>();
                    for(ShangPingItem shangPingItem1:shangPingItemList){
                        if(shangPingItem1.getSelectNum()>0){
                            shangPingItemList2.add(shangPingItem1);
                        }
                    }
                    if(shangPingItemList2.size()==0){
                        Toast.makeText(getActivity(), "一件商品都没有哦!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent=new Intent(getActivity(), Activity_shangping_xiadan.class);
                    DingDan dingDan=new DingDan();
                    dingDan.setSchool(MyApplication.myApplication.school);
                    dingDan.setLoudong(MyApplication.myApplication.loudong);
                    dingDan.setQinshizhangtel("");
                    Gson gson=new Gson();
                    dingDan.setShangPing(gson.toJson(shangPingItemList2));
                    dingDan.setType("接单中");
                    dingDan.setCounts(GouWuChe_shangping.allCounts+"");
                    dingDan.setBuyid(MyApplication.user.getId());
                    dingDan.setMaiid(Integer.parseInt(MyApplication.dianzhangId));
                    Date date=new Date();
                    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time=format.format(date);
                    dingDan.setTime(time);
                    intent.putExtra("dingdan",dingDan);
                    intent.putExtra("onlinePay",user.getOnlinePay());
                    getActivity().startActivityForResult(intent, 1);
                }

            }
        });
    }
    class MyTask extends AsyncTask<String,Void,List<ShangPingItem>> {

        @Override
        protected List<ShangPingItem> doInBackground(String... params) {
            ShangPingItem shangPingItem=GouWuChe_shangping.shangPingItemList.get(0);
            try {
                user=Manage.viewUserById(MyApplication.dianzhangId);
                System.out.println("user:"+user.toString());
                shangPingItemList= Manage.viewShangpinlistByUseridAndType(MyApplication.school, MyApplication.loudong, MyApplication.dianzhangId, MyApplication.type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(ShangPingItem shangPingItem1:shangPingItemList){
                if(!shangPingItem1.getId().equals(shangPingItem.getId())){
                    GouWuChe_shangping.adds(shangPingItem1);
                }
            }
            shangPingItemList=GouWuChe_shangping.shangPingItemList;
            return shangPingItemList;
        }
        protected void onPostExecute(List<ShangPingItem> shangPingItemList2){
            listView_right_item_adapter=new Gouwuche_shangping_adapter(getActivity(),shangPingItemList2,fragment_chaoshi);
            listview_right.setAdapter(listView_right_item_adapter);
        }
        @Override
        protected void onPreExecute(){
        }
    }
}
