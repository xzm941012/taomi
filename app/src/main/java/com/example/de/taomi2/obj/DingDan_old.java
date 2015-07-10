package com.example.de.taomi2.obj;

import java.io.Serializable;

/**
 * Created by 真爱de仙 on 2015/6/21.
 */
public class DingDan_old implements Serializable {
    String id;

    String zhuangtai; //1: 销售中 0:暂停销售

    String shangping;

    String address;

    String tel;

    String beizhu;

    double counts;

    String userid;

    String school;

    String payType;

    String loudong;

    String time;

    public DingDan_old() {

    }

    public DingDan_old(String id, String zhuangtai, String shangping, String address, String tel, String beizhu, double counts, String userid, String school, String payType, String time) {
        this.id = id;
        this.zhuangtai = zhuangtai;
        this.shangping = shangping;
        this.address = address;
        this.tel = tel;
        this.beizhu = beizhu;
        this.counts = counts;
        this.userid = userid;
        this.school = school;
        this.payType = payType;
        this.time=time;
    }


    public String getLoudong() {
        return loudong;
    }

    public void setLoudong(String loudong) {
        this.loudong = loudong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getShangping() {
        return shangping;
    }

    public void setShangping(String shangping) {
        this.shangping = shangping;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public double getCounts() {
        return counts;
    }

    public void setCounts(double counts) {
        this.counts = counts;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
