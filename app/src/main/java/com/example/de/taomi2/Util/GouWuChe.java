package com.example.de.taomi2.Util;


import com.example.de.taomi2.obj.ShangPingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class GouWuChe {
    public static double allCounts=0;
    public static int nums=0;
    public static List<ShangPingItem> shangPingItemList=new ArrayList<>();

    public static void addItems(ShangPingItem item){
        nums++;
        ShangPingItem items=new ShangPingItem(item.getId(),item.getName(),item.getJiage(),item.getPictureUrl(),item.getSelectNum(),item.getBuyNum(),item.getStyle());
        int n=0;
        for(ShangPingItem shangPingItem:shangPingItemList){
            if(shangPingItem.getId().equals(items.getId())){
                shangPingItem.setSelectNum(shangPingItem.getSelectNum()+1);
                n=1;
                break;
            }
        }
        if(n==0){
            items=new ShangPingItem(item.getId(),item.getName(),item.getJiage(),item.getPictureUrl(),1,item.getBuyNum(),item.getStyle());
            shangPingItemList.add(items);
        }
        allCounts=Arith.add(allCounts,items.getJiage());
        System.out.println("你总共选择了"+shangPingItemList.size()+"种商品,总价格为:"+allCounts);
    }

    public static void clearGouWuChe(){
        allCounts=0;
        nums=0;
        shangPingItemList.clear();
    }
    public static void reduceItems(ShangPingItem item){
        nums--;
        int n=0;
        ShangPingItem items=new ShangPingItem(item.getId(),item.getName(),item.getJiage(),item.getPictureUrl(),item.getSelectNum(),item.getBuyNum(),item.getStyle());

        for(int i=0;i<shangPingItemList.size();i++){
            ShangPingItem shangPingItem=shangPingItemList.get(i);
            if(shangPingItem.getId().equals(items.getId())){
                int selectNum=shangPingItem.getSelectNum();
                shangPingItem.setSelectNum(shangPingItem.getSelectNum()-1);
                if(selectNum==1){
                   n=1;
                }
            }
        }
        /*
        if(n==1){
            List<ShangPingItem> shangPingItemList2=new ArrayList<>();
            for(ShangPingItem shangPingItem:shangPingItemList){
                if(!shangPingItem.getId().equals(items.getId())){
                    shangPingItemList2.add(shangPingItem);
                }
            }
            shangPingItemList=shangPingItemList2;
        }
        */
        allCounts=Arith.sub(allCounts,items.getJiage());
        System.out.println("你总共选择了"+shangPingItemList.size()+"种商品,总价格为:"+allCounts);
    }
}
