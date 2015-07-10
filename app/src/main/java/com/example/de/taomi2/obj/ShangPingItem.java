package com.example.de.taomi2.obj;

/**
 * Created by 真爱de仙 on 2015/6/18.
 */
public class ShangPingItem {
    String id;
    String name;
    double jiage;
    String PictureUrl;
    int selectNum;
    int buyNum;
    String style;
    String xiangqing;
    String zhuangtai;
    String username;
    String userid;


    public ShangPingItem() {
    }

    public ShangPingItem(String id, String name, double jiage, String pictureUrl, int selectNum, int buyNum, String style) {
        this.id = id;
        this.name = name;
        this.jiage = jiage;
        PictureUrl = pictureUrl;
        this.selectNum = selectNum;
        this.buyNum = buyNum;
        this.style = style;
    }

    public ShangPingItem(String id, String name, double jiage, String pictureUrl, int selectNum, int buyNum, String style, String xiangqing, String zhuangtai) {
        this.id = id;
        this.name = name;
        this.jiage = jiage;
        PictureUrl = pictureUrl;
        this.selectNum = selectNum;
        this.buyNum = buyNum;
        this.style = style;
        this.xiangqing = xiangqing;
        this.zhuangtai = zhuangtai;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(String xiangqing) {
        this.xiangqing = xiangqing;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getJiage() {
        return jiage;
    }

    public void setJiage(double jiage) {
        this.jiage = jiage;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        PictureUrl = pictureUrl;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "ShangPingItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jiage=" + jiage +
                ", PictureUrl='" + PictureUrl + '\'' +
                ", selectNum=" + selectNum +
                ", buyNum=" + buyNum +
                ", style='" + style + '\'' +
                ", xiangqing='" + xiangqing + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                ", username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
