package com.example.de.taomi2.obj;
// default package



/**
 * ChaoshiItem entity. @author MyEclipse Persistence Tools
 */

public class ChaoshiItem implements java.io.Serializable {


    // Fields

     private Integer id;
     private String name;
     private String jianJie;
     private String jiage;
     private String imageUrl;
     private Integer buyNum;
     private String type;
     private Integer userid;
     private Integer zhuangtai;


    // Constructors

    /** default constructor */
    public ChaoshiItem() {
    }


    /** full constructor */
    public ChaoshiItem(String name, String jianJie, String jiage, String imageUrl, Integer buyNum, String type, Integer userid, Integer zhuangtai) {
        this.name = name;
        this.jianJie = jianJie;
        this.jiage = jiage;
        this.imageUrl = imageUrl;
        this.buyNum = buyNum;
        this.type = type;
        this.userid = userid;
        this.zhuangtai = zhuangtai;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getJianJie() {
        return this.jianJie;
    }
    
    public void setJianJie(String jianJie) {
        this.jianJie = jianJie;
    }

    public String getJiage() {
        return this.jiage;
    }
    
    public void setJiage(String jiage) {
        this.jiage = jiage;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getBuyNum() {
        return this.buyNum;
    }
    
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getZhuangtai() {
        return this.zhuangtai;
    }
    
    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }
   








}