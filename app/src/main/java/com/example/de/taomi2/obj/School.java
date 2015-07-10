package com.example.de.taomi2.obj;
// default package



/**
 * School entity. @author MyEclipse Persistence Tools
 */

public class School  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String schoolname;
     private String loudongname;
     private Integer userid;
     private Integer zhuangtai;
     private Integer buyNum;
     private String tel;


    // Constructors

    /** default constructor */
    public School() {
    }

    
    /** full constructor */
    public School(String schoolname, String loudongname, Integer userid, Integer zhuangtai, Integer buyNum, String tel) {
        this.schoolname = schoolname;
        this.loudongname = loudongname;
        this.userid = userid;
        this.zhuangtai = zhuangtai;
        this.buyNum = buyNum;
        this.tel = tel;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolname() {
        return this.schoolname;
    }
    
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getLoudongname() {
        return this.loudongname;
    }
    
    public void setLoudongname(String loudongname) {
        this.loudongname = loudongname;
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

    public Integer getBuyNum() {
        return this.buyNum;
    }
    
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
   








}