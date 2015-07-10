package com.example.de.taomi2.obj;
// default package



/**
 * Dingdan entity. @author MyEclipse Persistence Tools
 */

public class DingDan implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String shangPing;
     private Integer buyid;
     private String school;
     private String loudong;
     private String address;
     private String buytel;
     private String qinshizhangtel;
     private String type;
     private String beizhu;
     private Integer paytype;
     private Integer maiid;
     private String counts;
     private String time;


    // Constructors

    /** default constructor */
    public DingDan() {
    }

    
    /** full constructor */
    public DingDan(String shangPing, Integer buyid, String school, String loudong, String address, String buytel, String qinshizhangtel, String type, String beizhu, Integer paytype, Integer maiid,String counts,String time) {
        this.shangPing = shangPing;
        this.buyid = buyid;
        this.school = school;
        this.loudong = loudong;
        this.address = address;
        this.buytel = buytel;
        this.qinshizhangtel = qinshizhangtel;
        this.type = type;
        this.beizhu = beizhu;
        this.paytype = paytype;
        this.maiid=maiid;
        this.counts=counts;
        this.time=time;
    }

   
    // Property accessors

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getShangPing() {
        return this.shangPing;
    }
    
    public void setShangPing(String shangPing) {
        this.shangPing = shangPing;
    }

    public Integer getBuyid() {
        return this.buyid;
    }
    
    public void setBuyid(Integer buyid) {
        this.buyid = buyid;
    }

    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }

    public String getLoudong() {
        return this.loudong;
    }
    
    public void setLoudong(String loudong) {
        this.loudong = loudong;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuytel() {
        return this.buytel;
    }
    
    public void setBuytel(String buytel) {
        this.buytel = buytel;
    }

    public String getQinshizhangtel() {
        return this.qinshizhangtel;
    }
    
    public void setQinshizhangtel(String qinshizhangtel) {
        this.qinshizhangtel = qinshizhangtel;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getBeizhu() {
        return this.beizhu;
    }
    
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Integer getPaytype() {
        return this.paytype;
    }
    
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }


	public Integer getMaiid() {
		return maiid;
	}


	public void setMaiid(Integer maiid) {
		this.maiid = maiid;
	}

    @Override
    public String toString() {
        return "DingDan{" +
                "id=" + id +
                ", shangPing='" + shangPing + '\'' +
                ", buyid=" + buyid +
                ", school='" + school + '\'' +
                ", loudong='" + loudong + '\'' +
                ", address='" + address + '\'' +
                ", buytel='" + buytel + '\'' +
                ", qinshizhangtel='" + qinshizhangtel + '\'' +
                ", type='" + type + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", paytype=" + paytype +
                ", maiid=" + maiid +
                ", counts='" + counts + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}