package com.cssl.service;

import com.cssl.pojo.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    //进行我要参与
    public Map<String,Object> selectid(Integer sid);

    public List<Map<String,Object>> xiangxi(Integer sid);
    //进行投票service
    public int toupiao(Item item);
    //删除item
    public int idel(Integer sid1);
    //根据oid1删除item
    public int  delbyoid(Integer oid);
}
