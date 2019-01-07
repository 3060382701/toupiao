package com.cssl.service;

import com.cssl.pojo.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    //进行点击进入
    public Map<String,Object> selectone(Integer sid);
    public List<Map<String,Object>> xiang(Integer sid);
    //删除subject
    public int sdel(Integer sid);
    //修改subject
    public int sup(Subject subject);
}
