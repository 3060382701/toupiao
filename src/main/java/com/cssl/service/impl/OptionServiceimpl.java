package com.cssl.service.impl;

import com.cssl.dao.Optionsdao;
import com.cssl.pojo.Options;
import com.cssl.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OptionServiceimpl implements OptionService {
    @Autowired
    private Optionsdao optiondao;
    @Override
    public int oins(Options options) {
        return optiondao.oins(options);
    }

    @Override
    public int odel(Integer osid) {
        return optiondao.odel(osid);
    }

    @Override
    public List<Map<String, Object>> xiangxi(Integer sid) {
        return optiondao.xiangxi(sid);
    }

    @Override
    public int delbyoid(Integer oid) {
        return optiondao.delbyoid(oid);
    }

    @Override
    public int oup(Options options) {
        return optiondao.oup(options);
    }
}
