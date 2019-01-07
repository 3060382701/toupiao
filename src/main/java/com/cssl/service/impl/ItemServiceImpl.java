package com.cssl.service.impl;

import com.cssl.dao.Itemdao;
import com.cssl.pojo.Item;
import com.cssl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private Itemdao itemdao;

    @Override
    public int toupiao(Item item) {
        return itemdao.insertin(item);
    }

    @Override
    public Map<String, Object> selectid(Integer sid) {
        return itemdao.selectid(sid);
    }

    @Override
    public int idel(Integer sid1) {
        return itemdao.idel(sid1);
    }

    @Override
    public int delbyoid(Integer oid) {
        return itemdao.delbyoid(oid);
    }

    @Override
    public List<Map<String, Object>> xiangxi(Integer sid) {
        return itemdao.xiangxi(sid);
    }
}
