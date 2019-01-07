package com.cssl.dao;

import com.cssl.pojo.Options;

import java.util.List;
import java.util.Map;

public interface Optionsdao {
    //点击添加新投票
    public int oins(Options options);
    //删除options
    public int odel(Integer osid);
    //查找
    public List<Map<String,Object>> xiangxi(Integer sid);
    //修改options
    public int oup(Options options);

    //根据oid删除option
    public int delbyoid(Integer oid);
}
