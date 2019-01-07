package com.cssl.dao;

import com.cssl.pojo.Item;

import java.util.List;
import java.util.Map;

public interface Itemdao {
    //进行我要参与
    public Map<String,Object> selectid(Integer sid);

    public List<Map<String,Object>> xiangxi(Integer sid);
    //进行投票
    public int insertin(Item item);
    //删除item
    public int idel(Integer sid1);
    //根据oid1删除item
    public int  delbyoid(Integer oid);
}
