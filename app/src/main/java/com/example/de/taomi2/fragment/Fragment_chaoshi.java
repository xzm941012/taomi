package com.example.de.taomi2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.GouWuChe;
import com.example.de.taomi2.activity.Activity_choose_school;
import com.example.de.taomi2.activity.MainActivity;
import com.example.de.taomi2.adapter.ListView_chaoshi_item_adapter;
import com.example.de.taomi2.adapter.ListView_left_item_adapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.obj.Listview_left_item;
import com.example.de.taomi2.obj.ShangPingItem;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class Fragment_chaoshi extends Fragment {
    public static Fragment_chaoshi fragment_chaoshi;
    TextView bt1,bt2,bt3;
    String school;
    String loudong;
    View view;
    String selectLeftString;
    EditText findEdittext;
    ListView listview_left;
    TextView allCounts;
    TextView schoolTextview;
    String title="饿了";
    PullToRefreshListView listview_right;
    ListView_left_item_adapter listview_left_adapter;
    ListView_chaoshi_item_adapter listView_right_item_adapter;
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
        view = inflater.inflate(R.layout.activity_home, container, false);
        school= MyApplication.myApplication.school;
        loudong=MyApplication.myApplication.loudong;
        initLayout();
        initListenner();
        initParam();
        return view;
    }
    public void itemAdd(ShangPingItem item){
        String name=item.getStyle();
        for(int i=0;i<listview_left_adapter.mItems.size();i++){
            if(listview_left_adapter.mItems.get(i).getName().equals(name)){
                listview_left_adapter.mItems.get(i).setNum( listview_left_adapter.mItems.get(i).getNum()+1);
            }
        }
        GouWuChe.addItems(item);
        listview_left_adapter.notifyDataSetChanged();
        allCounts.setText(GouWuChe.allCounts+"");
        MainActivity.setAllCounds();
    }
    public void itemReduce(ShangPingItem item){
        String name=item.getStyle();
        for(int i=0;i<listview_left_adapter.mItems.size();i++){
            if(listview_left_adapter.mItems.get(i).getName().equals(name)){
                listview_left_adapter.mItems.get(i).setNum( listview_left_adapter.mItems.get(i).getNum()-1);
            }
        }
        GouWuChe.reduceItems(item);
        listview_left_adapter.notifyDataSetChanged();
        allCounts.setText(GouWuChe.allCounts+"");
        MainActivity.setAllCounds();
    }
    private void initParam(){
        List<Listview_left_item> mItems=new ArrayList<>();
        Listview_left_item listview_left_item1=new Listview_left_item("零食",0,0);
        mItems.add(listview_left_item1);
        listview_left_item1=new Listview_left_item("冰饮",0,0);
        mItems.add(listview_left_item1);
        listview_left_item1=new Listview_left_item("饮料",0,0);
        mItems.add(listview_left_item1);
        listview_left_item1=new Listview_left_item("日用百货",0,0);
        mItems.add(listview_left_item1);

        listview_left_adapter=new ListView_left_item_adapter(getActivity(),mItems);
        listview_left.setAdapter(listview_left_adapter);


    }
    private void initLayout(){
        bt1=(TextView)view.findViewById(R.id.textView95);
        bt2=(TextView)view.findViewById(R.id.textView96);
        bt3=(TextView)view.findViewById(R.id.textView97);
        schoolTextview=(TextView)view.findViewById(R.id.textView34);
        schoolTextview.setText(school);
        System.out.println("school:"+school);
        findEdittext=(EditText)view.findViewById(R.id.editText4);
        allCounts=(TextView)view.findViewById(R.id.textView);
        listview_left=(ListView)view.findViewById(R.id.lv_home_left);
        listview_right=(PullToRefreshListView)view.findViewById(R.id.plv_home_right);
    }
    private void initListenner(){
        view.findViewById(R.id.relativeLayout37).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title="饿了";
                bt1.setTextColor(getResources().getColor(R.color.bt_pressed));
                bt2.setTextColor(getResources().getColor(R.color.bt_normal));
                bt3.setTextColor(getResources().getColor(R.color.bt_normal));
                MyTask myTask=new MyTask();
                myTask.execute();
            }
        });
        view.findViewById(R.id.relativeLayout38).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title="渴了";
                bt1.setTextColor(getResources().getColor(R.color.bt_normal));
                bt2.setTextColor(getResources().getColor(R.color.bt_pressed));
                bt3.setTextColor(getResources().getColor(R.color.bt_normal));
                MyTask myTask=new MyTask();
                myTask.execute();
            }
        });
        view.findViewById(R.id.relativeLayout39).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title="寂寞了";
                bt1.setTextColor(getResources().getColor(R.color.bt_normal));
                bt2.setTextColor(getResources().getColor(R.color.bt_normal));
                bt3.setTextColor(getResources().getColor(R.color.bt_pressed));
                MyTask myTask=new MyTask();
                myTask.execute();
            }
        });
        listview_left.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
             // TODO Auto-generated method stub
                System.out.println("你选择的是:" + arg2);
                arg1.setSelected(true);
                for(int i=0;i<listview_left_adapter.mItems.size();i++){
                    listview_left_adapter.mItems.get(i).setSelect(0);
                }
                listview_left_adapter.mItems.get(arg2).setSelect(1);
                selectLeftString=listview_left_adapter.mItems.get(arg2).getName();
                MyTask myTask=new MyTask();
                myTask.execute();
            }
        });
        view.findViewById(R.id.imageView31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.view1).setVisibility(View.GONE);
                view.findViewById(R.id.imageView31).setVisibility(View.GONE);
                view.findViewById(R.id.view2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.textView35).setVisibility(View.VISIBLE);
                findEdittext.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) findEdittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        view.findViewById(R.id.imageView35).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.view1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.imageView31).setVisibility(View.VISIBLE);
                view.findViewById(R.id.view2).setVisibility(View.GONE);
                view.findViewById(R.id.textView35).setVisibility(View.GONE);
            }
        });
        view.findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), Activity_choose_school.class),1);
            }
        });
    }
    class MyTask extends AsyncTask<String,Void,List<ShangPingItem>> {

        @Override
        protected List<ShangPingItem> doInBackground(String... params) {
            System.out.println(title);
            List<ShangPingItem> shangPingItemList=null;
            try {
                shangPingItemList= Manage.viewChaoshiBySchool(school, loudong, title);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return shangPingItemList;
        }
        protected void onPostExecute(List<ShangPingItem> shangPingItemList){
            if(shangPingItemList==null){
                shangPingItemList=new ArrayList<>();
            }
            listView_right_item_adapter=new ListView_chaoshi_item_adapter(getActivity(),shangPingItemList,fragment_chaoshi);
            listview_right.setAdapter(listView_right_item_adapter);
        }
        @Override
        protected void onPreExecute(){
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication myApplication=(MyApplication)(getActivity().getApplication());
        school=myApplication.school;
        loudong=myApplication.loudong;

        System.out.println("loudong:"+loudong);
    }
}
