package com.example.de.taomi2.obj;

/**
 * Created by 真爱de仙 on 2015/6/22.
 */
public class Dianpu {
    String id;

    String name;

    String content;

    String zhuangtai;

    String imageUrl;

    int buyNum;

    String userid;

    String dianpuid;

    public Dianpu(String id, String name, String content, String zhuangtai, String imageUrl, int buyNum, String userid) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.zhuangtai = zhuangtai;
        this.imageUrl = imageUrl;
        this.buyNum = buyNum;
        this.userid = userid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
