package com.example.de.taomi2.http;

/**
 * Created by 真爱de仙 on 2015/1/26.
 */
public class PostUrl {
    public static final String Url="192.168.1.102";
    //public static final String Url="59.52.131.248";
    //public static final String Media="120.26.77.102";
    public static final String Media="192.168.1.102";
    public static final String BAOMINGIMAGE="image";
    //获得头像地址
    public static String getTouxiangPath(String id){
        return "http://" + Url + ":8080"+"/TaoMi/image/touxiang"+id+".jpg";
    }
    //获得小图片地址
    public static String getSmallFengmian(String url){
        String pathString="http://" + Url + ":8080"+"/TaoMi/image/"+url;
        return pathString.substring(0,pathString.lastIndexOf("."))+"small.JPEG";
    }
    //获得大图片地址
    public static String getImageUrl(String url){
        return "http://" + Url + ":8080"+"/TaoMi/image/"+url;
    }
    //获得上传图片地址
    public static String getUploadImageUrl(){
        return "http://" + Url + ":8080"+"/TaoMi/uploadImage.action";
    }
}
