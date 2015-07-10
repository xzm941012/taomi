package com.example.de.taomi2.http;

import com.example.de.taomi2.obj.ChaoshiItem;
import com.example.de.taomi2.obj.Dianpu;
import com.example.de.taomi2.obj.DingDan;
import com.example.de.taomi2.obj.School;
import com.example.de.taomi2.obj.ShangPing;
import com.example.de.taomi2.obj.ShangPingItem;
import com.example.de.taomi2.obj.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 真爱de仙 on 2015/6/28.
 */
public class Manage {
    private static Gson gson=new Gson();

    /**
     * 根据学校名和楼栋名还有分类查找这个地方的所有商品
     * @param school 学校名
     * @param qinshi 楼栋名
     * @param type 分类
     * @return
     * @throws Exception
     */
    public static List<ShangPingItem> viewChaoshiBySchool (String school,String qinshi,String type) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        map.put("loudong",qinshi);
        map.put("type",type);
        String url = HttpUtil.BASE_URL + "GetChaoshiList.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        List<ChaoshiItem> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ChaoshiItem>>(){}.getType());
        List<ShangPingItem> shangPingItemList1=new ArrayList<>();
        for(ChaoshiItem cs:shangPingItemList){
            ShangPingItem sp=new ShangPingItem();
            sp.setSelectNum(0);
            sp.setBuyNum(cs.getBuyNum());
            sp.setId(cs.getId()+"");
            sp.setJiage(Double.parseDouble(cs.getJiage()));
            sp.setZhuangtai(cs.getZhuangtai()+"");
            sp.setName(cs.getName());
            sp.setPictureUrl(cs.getImageUrl());
            sp.setStyle(cs.getType());
            sp.setXiangqing("");
            shangPingItemList1.add(sp);
        }

        return shangPingItemList1;
    }

    /**
     * 获得所有的学校列表
     * @return
     * @throws Exception
     */
    public static List<String> viewSchoolList () throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String url = HttpUtil.BASE_URL + "getAllSchool.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<String> school=new ArrayList<>();
        school=gson.fromJson(body, new TypeToken<List<String>>(){}.getType());
        return school;
    }

    /**
     * 更具学校名获得所有的楼栋列表
     * @param school 学校名字
     * @return
     * @throws Exception
     */
    public static List<String> viewQinshiList (String school) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        String url = HttpUtil.BASE_URL + "getAllLoudong.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<String> qinshi=new ArrayList<>();
        qinshi=gson.fromJson(body, new TypeToken<List<String>>(){}.getType());
        return qinshi;
    }

    /**
     * 获得寝室长id
     * @param school 学校
     * @param loudong 楼栋
     * @return
     * @throws Exception
     */
    public static School viewSchoolUserIdList (String school,String loudong) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        map.put("loudong",loudong);
        String url = HttpUtil.BASE_URL + "getQinshizhangId.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        School school1=new School();
        school1=gson.fromJson(body, School.class);
        System.out.println(school1);
        return school1;
    }
    /**
     * 买家确认一个订单，发送给卖家
     * @param dingdan 订单对象 ，先建立一个对象表，然后直接DingDan dindan=gson.fromJson(dingdan, new TypeToken<List<DingDan>>(){}.getType());可得到
     * @return
     * @throws Exception
     */
    public static String pushDingdan (DingDan dingdan) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String dingdanGson=gson.toJson(dingdan);
        map.put("dingdan",dingdanGson);
        String url = HttpUtil.BASE_URL + "AddDingDan.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }
    public static String pushShangpingDingdan (DingDan dingdan) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String dingdanGson=gson.toJson(dingdan);
        map.put("dingdan",dingdanGson);
        String url = HttpUtil.BASE_URL + "AddShangpingDingDan.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }

    public static String jieDan (String id) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id",id);
        String url = HttpUtil.BASE_URL + "changeDingdantype.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }

    /**
     * 根据学校和分类查看店铺列表
     * @param type 店铺分类
     * @param school 所在学校
     * @return
     * @throws Exception
     */
    public static List<Dianpu> viewDianpuByType (String type,String school) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type",type);
        map.put("school",school);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<Dianpu> dianpuList=new ArrayList<>();
        dianpuList=gson.fromJson(body, new TypeToken<List<Dianpu>>(){}.getType());
        return dianpuList;
    }

    /**
     * 根据学校和店铺id查看店铺所有商品
     * @param dianpu 店铺的id
     * @param school 所在学校
     * @return
     * @throws Exception
     */
    public static List<ShangPingItem> viewShangpinlistByDianpu (String school,String dianpu) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dianpu",dianpu);
        map.put("school",school);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<ShangPingItem> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ShangPingItem>>(){}.getType());
        return shangPingItemList;
    }

    /**
     * 根据分类查看学校所有商品
     * @param school 学校
     * @param loudong 楼栋
     * @param type 分类
     * @return
     * @throws Exception
     */
    public static List<ShangPingItem> viewShangpinlistByType (String school,String loudong,String type) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        map.put("loudong",loudong);
        map.put("type",type);
        String url = HttpUtil.BASE_URL + "GetShangPingListByType.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return new ArrayList<ShangPingItem>();
        }
        List<ShangPing> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ShangPing>>(){}.getType());
        List<ShangPingItem> shangPingItemList1=new ArrayList<>();
        for(ShangPing cs:shangPingItemList){
            ShangPingItem sp=new ShangPingItem();
            sp.setSelectNum(0);
            sp.setBuyNum(cs.getBuyNum());
            sp.setId(cs.getId()+"");
            sp.setJiage(Double.parseDouble(cs.getJiage()));
            sp.setZhuangtai(cs.getZhuangtai()+"");
            sp.setName(cs.getName());
            sp.setPictureUrl(cs.getImageUrl());
            sp.setStyle(cs.getType());
            sp.setXiangqing(cs.getJianJie());
            shangPingItemList1.add(sp);
        }
        return shangPingItemList1;
    }
    public static List<ShangPingItem> viewShangpinlistByUserid (String school,String loudong,String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        map.put("loudong",loudong);
        map.put("userid",userid);
        String url = HttpUtil.BASE_URL + "GetShangPingByUserId.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return new ArrayList<ShangPingItem>();
        }
        List<ShangPing> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ShangPing>>(){}.getType());
        List<ShangPingItem> shangPingItemList1=new ArrayList<>();
        for(ShangPing cs:shangPingItemList){
            ShangPingItem sp=new ShangPingItem();
            sp.setSelectNum(0);
            sp.setBuyNum(cs.getBuyNum());
            sp.setId(cs.getId()+"");
            sp.setJiage(Double.parseDouble(cs.getJiage()));
            sp.setZhuangtai(cs.getZhuangtai()+"");
            sp.setName(cs.getName());
            sp.setPictureUrl(cs.getImageUrl());
            sp.setStyle(cs.getType());
            sp.setXiangqing(cs.getJianJie());
            shangPingItemList1.add(sp);
        }
        return shangPingItemList1;
    }
    public static List<ShangPingItem> viewShangpinlistByUseridAndType (String school,String loudong,String userid,String type) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("school",school);
        map.put("loudong",loudong);
        map.put("userid",userid);
        map.put("type",type);
        String url = HttpUtil.BASE_URL + "GetShangPingByUserIdAndType.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return new ArrayList<ShangPingItem>();
        }
        List<ShangPing> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ShangPing>>(){}.getType());
        List<ShangPingItem> shangPingItemList1=new ArrayList<>();
        for(ShangPing cs:shangPingItemList){
            ShangPingItem sp=new ShangPingItem();
            sp.setSelectNum(0);
            sp.setBuyNum(cs.getBuyNum());
            sp.setId(cs.getId()+"");
            sp.setJiage(Double.parseDouble(cs.getJiage()));
            sp.setZhuangtai(cs.getZhuangtai()+"");
            sp.setName(cs.getName());
            sp.setPictureUrl(cs.getImageUrl());
            sp.setStyle(cs.getType());
            sp.setXiangqing(cs.getJianJie());
            shangPingItemList1.add(sp);
        }
        return shangPingItemList1;
    }
    public static List<ShangPingItem> viewChaoshilistByUserid (String school,String loudong,String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        System.out.println("school:"+school+" "+"loudong:"+loudong+" "+"userid:"+userid);
        map.put("school",school);
        map.put("loudong",loudong);
        map.put("userid",userid);
        String url = HttpUtil.BASE_URL + "getChaoshiListByUserId.action";
        System.out.println("url:"+url);
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return new ArrayList<ShangPingItem>();
        }
        List<ChaoshiItem> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ChaoshiItem>>(){}.getType());
        List<ShangPingItem> shangPingItemList1=new ArrayList<>();
        for(ChaoshiItem cs:shangPingItemList){
            ShangPingItem sp=new ShangPingItem();
            sp.setSelectNum(0);
            sp.setBuyNum(cs.getBuyNum());
            sp.setId(cs.getId()+"");
            sp.setJiage(Double.parseDouble(cs.getJiage()));
            sp.setZhuangtai(cs.getZhuangtai()+"");
            sp.setName(cs.getName());
            sp.setPictureUrl(cs.getImageUrl());
            sp.setStyle(cs.getType());
            sp.setXiangqing(cs.getJianJie());
            shangPingItemList1.add(sp);
        }
        return shangPingItemList1;
    }

    /**
     * 根据学校、楼栋、用户id获得这个用户的所有发布的商品，先根据id在user表中查看这个用户的shengfen属性，
     * 如果是2就代表是店长，就去对应的学校和楼栋表中查看这个学校的超市的商品列表
     * 如果是1就代表是店长，就去相应的学校表里面找到他发布的所有商品
     * 如果是0就返回null
     * @param school 学校名
     * @param loudong 楼栋名
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    public static List<ShangPingItem> viewShangpinlistByUserId (String school,String loudong,String userId) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId",userId);
        map.put("school",school);
        map.put("loudong",loudong);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<ShangPingItem> shangPingItemList=new ArrayList<>();
        shangPingItemList=gson.fromJson(body, new TypeToken<List<ShangPingItem>>(){}.getType());
        return shangPingItemList;
    }

    /**
     * 根据商品id查看详情
     * @param id 商品id
     * @return
     * @throws Exception
     */
    public static ShangPingItem viewShangpinById (String id) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        System.out.println("id:"+id);
        map.put("id",id);
        String url = HttpUtil.BASE_URL + "GetShangPingById.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        HashMap<String,String>hashMap=gson.fromJson(body,HashMap.class);
        String username=hashMap.get("username");
        String body2=hashMap.get("shangping");
        String userid=hashMap.get("userid");
        ShangPing cs=gson.fromJson(body2,ShangPing.class);
        ShangPingItem sp=new ShangPingItem();
        sp.setSelectNum(0);
        sp.setBuyNum(cs.getBuyNum());
        sp.setId(cs.getId()+"");
        sp.setJiage(Double.parseDouble(cs.getJiage()));
        sp.setZhuangtai(cs.getZhuangtai()+"");
        sp.setName(cs.getName());
        sp.setPictureUrl(cs.getImageUrl());
        sp.setStyle(cs.getType());
        sp.setXiangqing(cs.getJianJie());
        sp.setUsername(username);
        sp.setUserid(userid);
        return sp;
    }

    /**
     * 新建一个店铺，不过一个用户只能有一个店铺
     * @param dianpu 店铺id
     * @return
     * @throws Exception
     */
    public static String newDianpu (Dianpu dianpu) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String dianpuGson=gson.toJson(dianpu);
        map.put("dianpu",dianpuGson);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }
    public static String addShangping (ShangPing sp) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String dianpuGson=gson.toJson(sp);
        map.put("shangPing",dianpuGson);
        String url = HttpUtil.BASE_URL + "addShangping.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        return body;
    }

    /**
     * 查看未接的订单
     * @param userid 用户id
     * @param school 用户所在学校
     * @param qinshi 用户所在寝室
     * @param pageNum 页数
     * @return
     * @throws Exception
     */
    public static List<DingDan> viewWeijieDingdan (String userid,String school,String qinshi,String pageNum) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid",userid);
        map.put("school",school);
        map.put("qinshi",qinshi);

        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<DingDan> dingDanList=new ArrayList<>();
        dingDanList=gson.fromJson(body, new TypeToken<List<DingDan>>(){}.getType());
        return dingDanList;
    }
    public static List<DingDan> viewAllDingdanByUserid (String userid,String school,String qinshi) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid",userid);
        map.put("school",school);
        map.put("loudong",qinshi);

        String url = HttpUtil.BASE_URL + "GetDingdanByUserid.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        List<DingDan> dingDanList=new ArrayList<>();
        dingDanList=gson.fromJson(body, new TypeToken<List<DingDan>>(){}.getType());
        return dingDanList;
    }
    /**
     * 查看已接的订单
     * @param userid 用户id
     * @param school 用户所在学校
     * @param qinshi 用户所在寝室
     * @param pageNum 页数
     * @return
     * @throws Exception
     */
    public static List<DingDan> viewYijieDingdan (String userid,String school,String qinshi,String pageNum) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid",userid);
        map.put("school",school);
        map.put("qinshi",qinshi);
        map.put("pageNum",pageNum);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        List<DingDan> dingDanList=new ArrayList<>();
        dingDanList=gson.fromJson(body, new TypeToken<List<DingDan>>(){}.getType());
        return dingDanList;
    }

    /**
     * 修改店铺的状态 ,判断用户身份，寝室长和店长操作不一样，参照viewShangpinlistByUserId
     * @param userid 用户id
     * @param zhuangtai 1:营业中 0:打烊中
     * @param school 学校名
     * @param loudong 楼栋名
     * @return
     * @throws Exception
     */
    public static String chanceDianpuZhuangtai (String school,String loudong,String userid,String zhuangtai) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid",userid);
        map.put("zhuangtai",zhuangtai);
        map.put("school",school);
        map.put("loudong",loudong);
        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }

    /**
     * 修改商品的状态，如果身份是寝室长就修改对应超市列表里面商品的状态，店长就修改对应商品状态
     * @param school
     * @param loudong
     * @param userid
     * @param shangpinid
     * @param zhuangtai 1:销售中 0:暂停销售
     * @return
     * @throws Exception
     */
    public static String chanceShangpinZhuangtai (String school,String loudong,String userid,String shangpinid,String zhuangtai,String type) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id",shangpinid);
        map.put("zhuangtai",zhuangtai);
        map.put("school",school);
        map.put("loudong",loudong);
        map.put("changetype",type);
        String url = HttpUtil.BASE_URL + "ChangeShangPing.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        return body;
    }

    /**
     * 用户登录
     * @param tel 用户名，电话
     * @param mima 密码
     * @return
     * @throws Exception
     */
    public static User login (String tel,String mima) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("telephone",tel);
        map.put("password",mima);

        String url = HttpUtil.BASE_URL + "user/login.action";
        String body = HttpUtil.postRequest(url, map);
        if(body.equals("")){
            return null;
        }
        User user=gson.fromJson(body,User.class);;
        System.out.println("user:"+user);
        return user;
    }

    /**
     * qq登录
     * @param username 用户昵称
     * @param email 用户唯一识别码
     * @param headSculpture 头像地址
     * @param sex 性别
     * @return
     * @throws Exception
     */
    public static User qqlogin (String username,String email,String headSculpture
            ,String sex) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("email",email);
        map.put("headSculpture",headSculpture);
        map.put("sex",sex);

        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        User user=gson.fromJson(body,User.class);;
        return user;
    }

    /**
     * 用户注册
     * @param tel 用户名，手机号
     * @param mima 密码
     * @param username 用户昵称
     * @return
     * @throws Exception
     */
    public static User register (String tel,String mima,String username) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("tel",tel);
        map.put("mima",mima);

        String url = HttpUtil.BASE_URL + "ViewUserByChannel.jsp";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        User user=gson.fromJson(body,User.class);;
        return user;
    }

    /**
     * 根据用户id查看用户资料
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    public static User viewUserById (String userId) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id",userId);

        String url = HttpUtil.BASE_URL + "user/findUserById.action";
        String body = HttpUtil.postRequest(url, map);
        if(body==null){
            return null;
        }
        User user=gson.fromJson(body,User.class);;
        return user;
    }
}
