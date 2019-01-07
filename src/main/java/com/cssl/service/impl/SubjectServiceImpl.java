package com.cssl.service.impl;

import com.cssl.dao.Subjectdao;
import com.cssl.pojo.Subject;
import com.cssl.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private Subjectdao subjectdao;
    @Override
    public Map<String,Object> selectone(Integer sid)
    {
        return subjectdao.selectone(sid);
    }
    @Override
    public List<Map<String,Object>> xiang(Integer sid)
    {
        return subjectdao.xiang(sid);
    }

    @Override
    public int sdel(Integer sid) {
        return subjectdao.sdel(sid);
    }

    @Override
    public int sup(Subject subject) {
        return subjectdao.sup(subject);
    }
}
