package com.example.de.taomi2.obj;
// default package



/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String qqloginToken;
     private String password;
     private Integer dingdanNum;
     private String allCounts;
     private Integer shenfen;
     private String tel;
     private String registtime;
     private String onlineCounts;
     private int onlinePay;

    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String name, String qqloginToken, String password, Integer dingdanNum, String allCounts, Integer shenfen, String tel, String registtime,String onlineCounts,int onlinePay) {
        this.name = name;
        this.qqloginToken = qqloginToken;
        this.password = password;
        this.dingdanNum = dingdanNum;
        this.allCounts = allCounts;
        this.shenfen = shenfen;
        this.tel = tel;
        this.registtime = registtime;
        this.onlineCounts=onlineCounts;
        this.onlinePay=onlinePay;
    }

   
    // Property accessors

    public String getOnlineCounts() {
        return onlineCounts;
    }

    public int getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(int onlinePay) {
        this.onlinePay = onlinePay;
    }

    public void setOnlineCounts(String onlineCounts) {
        this.onlineCounts = onlineCounts;
    }

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

    public String getQqloginToken() {
        return this.qqloginToken;
    }
    
    public void setQqloginToken(String qqloginToken) {
        this.qqloginToken = qqloginToken;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDingdanNum() {
        return this.dingdanNum;
    }
    
    public void setDingdanNum(Integer dingdanNum) {
        this.dingdanNum = dingdanNum;
    }

    public String getAllCounts() {
        return this.allCounts;
    }
    
    public void setAllCounts(String allCounts) {
        this.allCounts = allCounts;
    }

    public Integer getShenfen() {
        return this.shenfen;
    }
    
    public void setShenfen(Integer shenfen) {
        this.shenfen = shenfen;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegisttime() {
        return this.registtime;
    }
    
    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qqloginToken='" + qqloginToken + '\'' +
                ", password='" + password + '\'' +
                ", dingdanNum=" + dingdanNum +
                ", allCounts='" + allCounts + '\'' +
                ", shenfen=" + shenfen +
                ", tel='" + tel + '\'' +
                ", registtime='" + registtime + '\'' +
                ", onlineCounts='" + onlineCounts + '\'' +
                ", onlinePay=" + onlinePay +
                '}';
    }
}